package com.utocat.catalizr.core.APIs;

import com.utocat.catalizr.core.AuthorizationToken;

/**
 * 
 *  @author Cedric PAUCHET cedric.pauchet@utocat.com
 *
 */
public interface AuthenticationApi {

    /**
     * Gets the new token used for requests authentication.
     *
     * @return {@link AuthorizationToken} object with token information.
     */
    AuthorizationToken createToken() throws Exception;
}
