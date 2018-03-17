package com.utocat.catalizr.core;

import com.utocat.catalizr.CatalizrApi;

import static org.testng.Assert.*;
import org.testng.annotations.*;

/**
 * Tests for holding authentication token in instance
 */
public class TokensTest extends BaseTest {

    @Test
    public void forceToken() throws Exception {

    	AuthorizationToken oldToken = this.api.getauthorizationTokenManager().getToken();
        AuthorizationToken newToken = this.api.getAuthenticationManager().createToken();

        // by GCH : mise en standby vue avec CP
        //assertTrue(oldToken.getAuthorizationToken().equals(newToken.getAuthorizationToken()));

        this.api.getauthorizationTokenManager().storeToken(newToken);
        AuthorizationToken storedToken = this.api.getauthorizationTokenManager().getToken();

        assertEquals(newToken.getAuthorizationToken(), storedToken.getAuthorizationToken());
    }

    @Test
    public void standardUseOfToken() throws Exception {
        this.api.getCompanyApi().getAll(null, null);
        AuthorizationToken token = this.api.getauthorizationTokenManager().getToken();
        this.api.getCompanyApi().getAll(null, null);

        assertEquals(token.getAuthorizationToken(), this.api.getauthorizationTokenManager().getToken().getAuthorizationToken());
    }

    @Test
    public void shareTokenBetweenInstances() throws Exception {
        CatalizrApi api = this.buildNewCatalizrApi();

        AuthorizationToken token1 = this.api.getauthorizationTokenManager().getToken();
        AuthorizationToken token2 = api.getauthorizationTokenManager().getToken();

        assertEquals(token1.getAuthorizationToken(), token2.getAuthorizationToken());
    }

    @Test
    public void isolateTokensBetweenEnvironments() throws Exception {
        CatalizrApi api = new CatalizrApi();
        api.getConfig().setApiPublicKey("test");
        api.getConfig().setApiPrivateKey("test");
        api.getConfig().setBaseUrl("https://qualif.api.catalizr.io");

        AuthorizationToken token1 = api.getauthorizationTokenManager().getToken();
       
        // TODO Create a new pair of key for complete this test
        // api.getConfig().setApiPublicKey("sdk_example");
        // api.getConfig().setApiPrivateKey("Vfp9eMKSzGkxivCwt15wE082pTTKsx90vBenc9hjLsf5K46ciF");
        // api.getConfig().setBaseUrl("https://qualif.api.catalizr.io");
        // 
        // AuthorizationToken token2 = api.getauthorizationTokenManager().getToken();
        // 
        // assertNotEquals(token1.getAuthorizationToken(), token2.getAuthorizationToken());

        api.getConfig().setApiPublicKey("sdk-unit-tests");
        api.getConfig().setApiPrivateKey("cqFfFrWfCcb7UadHNxx2C9Lo6Djw8ZduLi7J9USTmu8bhxxpju");
        api.getConfig().setBaseUrl("https://qualif.api.catalizr.io");

        AuthorizationToken token3 = api.getauthorizationTokenManager().getToken();

        assertEquals(token1.getAuthorizationToken(), token3.getAuthorizationToken());
    }
}
