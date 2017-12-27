package com.frame.boot.frame.security.auth;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private String validCode;

    public String getValidCode() {
        return validCode;
    }

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CustomWebAuthenticationDetails(HttpServletRequest request, String validCodeName) {
        super(request);
        this.validCode = request.getParameter(validCodeName);
    }
}
