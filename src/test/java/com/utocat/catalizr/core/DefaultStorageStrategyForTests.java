package com.utocat.catalizr.core;

import com.utocat.catalizr.core.interfaces.StorageStrategy;

/**
 * Default token storage strategy implementation for tests.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class DefaultStorageStrategyForTests implements StorageStrategy {

    private static AuthorizationToken authorizationToken = null;
    
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
     * @param envKey Environment key for token.
     */
    @Override
    public void store(AuthorizationToken token) {
    	authorizationToken = token;
    }
    
}