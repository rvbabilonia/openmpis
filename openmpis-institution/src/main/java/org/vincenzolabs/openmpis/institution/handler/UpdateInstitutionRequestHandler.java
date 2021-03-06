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
package org.vincenzolabs.openmpis.institution.handler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vincenzolabs.openmpis.adapter.InstitutionAdapter;
import org.vincenzolabs.openmpis.domain.Institution;
import org.vincenzolabs.openmpis.institution.service.InstitutionService;
import org.vincenzolabs.openmpis.representation.InstitutionJson;
import org.vincenzolabs.openmpis.representation.Request;
import org.vincenzolabs.openmpis.representation.Response;
import software.amazon.awssdk.awscore.exception.AwsServiceException;

/**
 * The {@link RequestHandler} for updating an {@link Institution}.
 *
 * @author Rey Vincent Babilonia
 */
@Component
public class UpdateInstitutionRequestHandler
    implements RequestHandler<Request, Response> {

    private final InstitutionService institutionService;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param institutionService the {@link InstitutionService}
     * @param gson               the {@link Gson}
     */
    @Autowired
    public UpdateInstitutionRequestHandler(InstitutionService institutionService, Gson gson) {
        this.institutionService = institutionService;
        this.gson = gson;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(request.toString());

        Response response = new Response();
        // enable CORS
        response.setHeaders(Map.of("Access-Control-Allow-Origin", "*vincenzolabs.org"));
        response.setHeaders(Map.of("Access-Control-Allow-Credentials", "true"));
        response.setHeaders(Map.of("Access-Control-Allow-Headers", "Set-Cookie"));
        response.setHeaders(Map.of("Access-Control-Allow-Methods", "OPTIONS,POST,GET"));

        try {
            InstitutionJson institutionJson = gson.fromJson(request.getBody(), InstitutionJson.class);

            Institution institution = institutionService.updateInstitution(InstitutionAdapter.adapt(institutionJson));

            response.setStatusCode(200);
            response.setBody(gson.toJson(InstitutionAdapter.adapt(institution)));
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
}
