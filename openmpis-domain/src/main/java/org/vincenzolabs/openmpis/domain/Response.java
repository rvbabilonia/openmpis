/*
 * This file is part of Command of the Sea.
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
package org.vincenzolabs.openmpis.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The response.
 *
 * @author Rey Vincent Babilonia
 */
public class Response {

    public static final String ERROR_MESSAGE = "{\"errorType\":\"Exception\",\"errorMessage\":\"%s\"}";

    private int statusCode;

    private Map<String, String> headers = new HashMap<>();

    private String body;

    private boolean isBase64Encoded;

    /**
     * Returns the status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the {@link Map} of headers.
     *
     * @return the {@link Map} of headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the {@link Map} of headers.
     *
     * @param headers the {@link Map} of headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Returns the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param body the body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the base-64 encoded flag.
     *
     * @return {@code true} if body is base-64 encoded; {@link false} otherwise
     */
    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    /**
     * Sets the base-64 encoded flag.
     *
     * @param isBase64Encoded {@code true} if body is base-64 encoded; {@link false} otherwise
     */
    public void setBase64Encoded(boolean isBase64Encoded) {
        this.isBase64Encoded = isBase64Encoded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Response response = (Response) o;

        return new EqualsBuilder()
            .append(statusCode, response.statusCode)
            .append(isBase64Encoded, response.isBase64Encoded)
            .append(headers, response.headers)
            .append(body, response.body)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(statusCode)
            .append(headers)
            .append(body)
            .append(isBase64Encoded)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("statusCode", statusCode)
            .append("headers", headers)
            .append("body", body)
            .append("isBase64Encoded", isBase64Encoded)
            .toString();
    }
}
