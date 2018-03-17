package com.utocat.catalizr.core;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * AuthorizationToken entity.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class AuthorizationToken extends Dto implements Serializable {

	/**
     * JWT.
     */
    @SerializedName("authorizationToken")
	private String authorizationToken;
	
    /**
     * Instantiates new AuthorizationToken object.
     */
    public AuthorizationToken() {
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    // for debug purposes
    @Override
    public String toString() {

        return "authorizationToken = " + this.authorizationToken;

    }
}
