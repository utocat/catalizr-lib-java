package com.utocat.catalizr.core.interfaces;

import com.utocat.catalizr.core.AuthorizationToken;

/**
 * Storage strategy interface.
 */
public interface StorageStrategy {
    
    public AuthorizationToken get();
    
    public void store(AuthorizationToken token);
    
}
