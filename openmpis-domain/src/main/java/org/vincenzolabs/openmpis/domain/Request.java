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
package org.vincenzolabs.openmpis.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The request.
 *
 * @author Rey Vincent Babilonia
 */
public class Request {

    private Map<String, String> pathParameters = new HashMap<>();

    private Map<String, String> queryStringParameters = new HashMap<>();

    private Map<String, String> headers = new HashMap<>();

    private String body;

    /**
     * Returns the {@link Map} of path parameters.
     *
     * @return the {@link Map} of path parameters
     */
    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    /**
     * Sets the {@link Map} of path parameters.
     *
     * @param pathParameters the {@link Map} of path parameters
     */
    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    /**
     * Returns the {@link Map} of query parameters.
     *
     * @return the {@link Map} of query parameters
     */
    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    /**
     * Sets the {@link Map} of query parameters.
     *
     * @param queryStringParameters the {@link Map} of query parameters
     */
    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Request request = (Request) o;

        return new EqualsBuilder()
            .append(pathParameters, request.pathParameters)
            .append(queryStringParameters, request.queryStringParameters)
            .append(headers, request.headers)
            .append(body, request.body)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(pathParameters)
            .append(queryStringParameters)
            .append(headers)
            .append(body)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("pathParameters", pathParameters)
            .append("queryStringParameters", queryStringParameters)
            .append("headers", headers)
            .append("body", body)
            .toString();
    }
}
