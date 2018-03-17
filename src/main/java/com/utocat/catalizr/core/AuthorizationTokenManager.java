package com.utocat.catalizr.core;

import com.utocat.catalizr.core.interfaces.StorageStrategy;
import com.utocat.catalizr.core.APIs.ApiBase;
import com.utocat.catalizr.CatalizrApi;


/**
 * Authorization token manager. This class cannot be inherited.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public final class AuthorizationTokenManager extends ApiBase {
    
    private StorageStrategy storageStrategy;
    
    /**
     * Instantiates new AuthorizationTokenManager object.
     * @param root Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instances.
     */
    public AuthorizationTokenManager(CatalizrApi root) {
        super(root);
        
        this.registerCustomStorageStrategy(new DefaultStorageStrategy());
    }
    
    /**
     * Gets the current authorization token.
     * <p>
     * In the very first call, this method creates a new token before returning.
     * 
     * @return Valid AuthorizationToken instance.
     * @throws Exception
     */
    public AuthorizationToken getToken() throws Exception {
    	AuthorizationToken token = storageStrategy.get();
        
        if (token == null || token.getAuthorizationToken() == null || token.getAuthorizationToken().length() == 0) {
            storeToken(this.root.getAuthenticationManager().createToken());
        }
        
        return storageStrategy.get();
    }
    
    /**
     * Stores authorization token passed as an argument in the underlying storage strategy implementation.
     * @param token AuthorizationToken instance to be stored.
     */
    public void storeToken(AuthorizationToken token) {
        storageStrategy.store(token);
    }
    
    /**
     * Registers custom storage strategy implementation.
     * <p>
     * By default, the <code>DefaultStorageStrategy</code> instance is used. 
     * There is no need to explicitly call this method until some more complex 
     * storage implementation is needed.
     * @param customStorageStrategy StorageStrategy interface implementation.
     */
    public void registerCustomStorageStrategy(StorageStrategy customStorageStrategy) {
        storageStrategy = customStorageStrategy;
    }
    
}
