package com.utocat.catalizr.core;

import com.utocat.catalizr.core.interfaces.StorageStrategy;

/**
 * Default token storage strategy implementation.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class DefaultStorageStrategy implements StorageStrategy {

    private static AuthorizationToken authorizationToken = new AuthorizationToken();
    
    /**
     * Gets the currently stored token.
     * @return Currently stored token instance or null.
     */
    @Override
    public AuthorizationToken get() {
        return authorizationToken;
    }

    /**
     * Stores authorization token passed as an argument.
     * @param token Token instance to be stored.
     */
    @Override
    public void store(AuthorizationToken token) {
    	authorizationToken = token;
    }
    
}
