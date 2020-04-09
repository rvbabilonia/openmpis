/*
 * This file is part of OpenMPIS.
 *
 * Copyright (c) 2019 VincenzoLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.vincenzolabs.openmpis.user.handler;

import java.time.ZoneId;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vincenzolabs.openmpis.domain.Request;
import org.vincenzolabs.openmpis.domain.Response;
import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.user.service.UserService;
import software.amazon.awssdk.awscore.exception.AwsServiceException;

/**
 * The {@link RequestHandler} for authenticating a {@link User}.
 *
 * @author Rey Vincent Babilonia
 */
@Component
public class LoginRequestHandler
    implements RequestHandler<Request, Response> {

    private final UserService userService;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param userService the {@link UserService}
     * @param gson        the {@link Gson}
     */
    @Autowired
    public LoginRequestHandler(UserService userService, Gson gson) {
        this.userService = userService;
        this.gson = gson;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();

        Response response = new Response();
        // enable CORS
        response.setHeaders(Map.of("Access-Control-Allow-Origin", "*vincenzolabs.org"));
        response.setHeaders(Map.of("Access-Control-Allow-Credentials", "true"));
        response.setHeaders(Map.of("Access-Control-Allow-Headers", "Set-Cookie"));
        response.setHeaders(Map.of("Access-Control-Allow-Methods", "OPTIONS,POST,GET"));

        try {
            Credentials credentials = gson.fromJson(request.getBody(), Credentials.class);

            String timeZone = request.getHeaders().get("X-Time-Zone");
            ZoneId zoneId = timeZone != null ? ZoneId.of(timeZone) : ZoneId.of("Pacific/Auckland");

            User user = userService.login(credentials.getEmailAddress(), credentials.getPassword(), zoneId,
                request.getHeaders().get("X-Forwarded-For"));

            response.setStatusCode(200);
            response.setBody(gson.toJson(user));
        } catch (AwsServiceException e) {
            logger.log(e.getMessage());

            response.setStatusCode(e.statusCode());
            response.setBody(String.format(Response.ERROR_MESSAGE, e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.log(e.getMessage());

            response.setStatusCode(400);
            response.setBody(String.format(Response.ERROR_MESSAGE, e.getMessage()));
        } catch (Exception e) {
            logger.log(e.getMessage());

            response.setStatusCode(500);
            response.setBody(String.format(Response.ERROR_MESSAGE, e.getMessage()));
        }

        return response;
    }

    /**
     * The credentials.
     */
    private static class Credentials {

        private String emailAddress;

        private String password;

        /**
         * Returns the email address.
         *
         * @return the email address
         */
        public String getEmailAddress() {
            return emailAddress;
        }

        /**
         * Sets the email address.
         *
         * @param emailAddress the email address
         */
        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        /**
         * Returns the password.
         *
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * Sets the password.
         *
         * @param password the password
         */
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
