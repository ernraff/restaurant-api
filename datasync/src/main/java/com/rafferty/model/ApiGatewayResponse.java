package com.rafferty.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class ApiGatewayResponse {

    private String body;
    private Integer statusCode;
    private Map<String, String> headers;
    private boolean isBase64Encoded;

    public ApiGatewayResponse(
            String body,
            Integer statusCode,
            Map<String, String> headers,
            boolean isBase64Encoded
        ) {

        this.body = body;
        this.statusCode = statusCode;
        this.headers = headers;
        this.isBase64Encoded = isBase64Encoded;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }
}
