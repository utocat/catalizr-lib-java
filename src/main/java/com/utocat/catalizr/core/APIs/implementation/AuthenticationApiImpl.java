package com.utocat.catalizr.core.APIs.implementation;

import java.util.HashMap;
import java.util.Map;

import com.utocat.catalizr.CatalizrApi;
import com.utocat.catalizr.core.AuthenticationHelper;
import com.utocat.catalizr.core.AuthorizationToken;
import com.utocat.catalizr.core.Configuration;
import com.utocat.catalizr.core.JsonTool;
import com.utocat.catalizr.core.RestTool;
import com.utocat.catalizr.core.UrlTool;
import com.utocat.catalizr.core.APIs.ApiBase;
import com.utocat.catalizr.core.APIs.AuthenticationApi;

/**
 * API for Authentication.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class AuthenticationApiImpl extends ApiBase implements AuthenticationApi {

    /**
     * Instantiates new AuthenticationApiImpl object.
     * 
     * @param root Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    public AuthenticationApiImpl(CatalizrApi root) {
        super(root);
    }

    @Override
    public AuthorizationToken createToken() throws Exception {

        String urlMethod = this.getRequestUrl("authentication_post");
        String requestType = this.getRequestType("authentication_post");
        Map<String, String> requestData = new HashMap<String, String>() {{
            put("apiPublicKey", root.getConfig().getApiPublicKey());
        }};

        RestTool rest = new RestTool(this.root, false);
        AuthenticationHelper authHlp = new AuthenticationHelper(root);
        
        UrlTool urlTool = new UrlTool(root);
        String fullRestUrl = urlTool.getFullUrl(urlTool.getRestUrl(urlMethod));
        String requestBody = JsonTool.mapToJson(requestData);
        
        rest.addRequestHttpHeader(authHlp.getHttpHeaderAuthentication(fullRestUrl, requestBody));
		AuthorizationToken response = rest.request(AuthorizationToken.class, urlMethod, requestType, requestData, null,null,true);

        return response;
    }

}
