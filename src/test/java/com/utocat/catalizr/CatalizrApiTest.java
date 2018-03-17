package com.utocat.catalizr;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.utocat.catalizr.core.BaseTest;



/**
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class CatalizrApiTest extends BaseTest {
    
    @Test
    public void ApiConstructionTest() {
        CatalizrApi catalizrApi = new CatalizrApi();
        assertNotNull(catalizrApi);
        assertNotNull(catalizrApi.getCompanyApi());
    }
}