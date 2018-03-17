package com.utocat.catalizr;

import com.utocat.catalizr.core.APIs.*;
import com.utocat.catalizr.core.APIs.implementation.*;
import com.utocat.catalizr.core.AuthorizationTokenManager;
import com.utocat.catalizr.core.Configuration;
import com.utocat.catalizr.core.APIs.AuthenticationApi;

import java.util.List;

/**
 * Catalizr API main entry point.
 * Provides managers to connect, send and read data from Catalizr API
 * as well as holds configuration/authorization data.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class CatalizrApi {

    /**
     * Instantiates new CatalizrApi object.
     */
    public CatalizrApi() {

        // default config setup
        setConfig(new Configuration());
        setAuthorizationTokenManager(new AuthorizationTokenManager(this));

        // API managers
        setAuthenticationManager(new AuthenticationApiImpl(this));
        setCompanyApi(new CompanyApiImpl(this));
        
    }

    ////////////////////////////////////////
    // Config/authorization related fields
    ////////////////////////////////////////

    /**
     * Provides Authorization token methods.
     */
    private AuthorizationTokenManager authorizationTokenManager;

    /**
     * Configuration instance with default settings (to be reset if required).
     */
    private Configuration config;

    ////////////////////////////////////////
    // API managers fields
    ////////////////////////////////////////

    /**
     * Provides authentication methods.
     */
    private AuthenticationApi authenticationManager;

    /**
     * Provides Companies methods.
     */
    private CompanyApi companies;

    public AuthorizationTokenManager getauthorizationTokenManager() {
        return authorizationTokenManager;
    }

    public void setAuthorizationTokenManager(AuthorizationTokenManager authorizationTokenManager) {
        this.authorizationTokenManager = authorizationTokenManager;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public AuthenticationApi getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationApi authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public CompanyApi getCompanyApi() {
        return companies;
    }

    public void setCompanyApi(CompanyApi companies) {
        this.companies = companies;
    }
}
