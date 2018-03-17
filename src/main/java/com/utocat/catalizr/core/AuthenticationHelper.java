package com.utocat.catalizr.core;

import com.utocat.catalizr.CatalizrApi;
import com.utocat.catalizr.core.enumerations.HmacAlgorithm;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication helper class.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class AuthenticationHelper {

	/**
	 * slf4j logger facade
	 */
    private Logger logger;
    
    /**
     * Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    private CatalizrApi root;
    
    /**
     * Instantiates new AuthenticationHelper object.
     * @param root Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    public AuthenticationHelper(CatalizrApi root) throws Exception {
        this.root = root;
        logger = LoggerFactory.getLogger(AuthenticationHelper.class);
    }
    
    /**
     * Gets key value for authentication HTTP header.
     * @param restUrl url for generate hmac
     * @param requestBody body for generate hmac
     * @return
     * @throws Exception
     */
    public Map<String, String> getHttpHeaderAuthentication(String restUrl, String requestBody) throws Exception {
    	if (root.getConfig().getBaseUrl() == null || root.getConfig().getBaseUrl().length() == 0)
    		throw new Exception ("Catalizr.config.BaseUrl is not set.");
    	if (root.getConfig().getApiPublicKey() == null || root.getConfig().getApiPublicKey().length() == 0)
    		throw new Exception ("Catalizr.config.ApiPublicKey is not set.");
    	if (root.getConfig().getApiPrivateKey() == null || root.getConfig().getApiPrivateKey().length() == 0)
    		throw new Exception ("Catalizr.config.ApiPrivateKey is not set.");
    	
    	final String nonce = String.valueOf(Calendar.getInstance().getTimeInMillis());
    	
    	if (this.root.getConfig().isDebugMode()) {
        	logger.info("Hmac url : {}", restUrl);
        	logger.info("Hmac nonce : {}", nonce);
        	logger.info("Hmac body : {}", requestBody);
        }
    	
    	String message = nonce+restUrl;
    	if (requestBody != null) {
    		message += requestBody;
    	}
    	
    	final HmacTool hmacTool = new HmacTool();	
    	final String apiSignature = hmacTool.hmacDigest(message, root.getConfig().getApiPrivateKey(), HmacAlgorithm.HmacSHA512.toString());
    	
        return new HashMap<String, String>(){{
        	put("x-api-nonce", nonce);
        	put("x-api-signature", apiSignature);
        	put("Content-Type", "application/json");
        }};
    }
    
    /**
     * Gets HTTP header value with authentication values.
     * @return
     * @throws Exception
     */
    public Map<String, String> getHttpHeaderAuthorization(String restUrl, String requestBody) throws Exception {
        return getHttpHeaderStrong(restUrl, requestBody);
    }
    
    // gets HTTP header value with authorization string for strong authentication
    private Map<String, String> getHttpHeaderStrong(final String restUrl, final String requestBody) throws Exception {
        
        final AuthorizationToken token = root.getauthorizationTokenManager().getToken();
        
        if (token == null || token.getAuthorizationToken() == null || token.getAuthorizationToken().length() == 0)
            throw new Exception ("AuthorizationToken is not created (or is invalid) for strong authentication");
        
        return new HashMap<String, String>(){{
            put("Authorization", "bearer " + token.getAuthorizationToken());
            putAll(getHttpHeaderAuthentication(restUrl, requestBody));
        }};
    }
    
}
