package com.utocat.catalizr.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Ignore;

import com.utocat.catalizr.CatalizrApi;
import com.utocat.catalizr.entities.Company;
import com.utocat.catalizr.entities.Document;
/**
 * @author Cedric PAUCHET cedric.pauchet@utocat.com@author cedricpauchet
 */
@Ignore("Just a base class for tests: nothing to test here")
public abstract class BaseTest {

    protected CatalizrApi api;

    public BaseTest() {
        this.api = buildNewCatalizrApi();
    }

    protected CatalizrApi getApi() {
        return api;
    }

    protected final CatalizrApi buildNewCatalizrApi() {
    	CatalizrApi newApi = new CatalizrApi();

        // use test Api keys
        newApi.getConfig().setApiPublicKey("test");
        newApi.getConfig().setApiPrivateKey("test");
        newApi.getConfig().setDebugMode(true);

        // register storage strategy for tests
        newApi.getauthorizationTokenManager().registerCustomStorageStrategy(new DefaultStorageStrategyForTests());

        return newApi;
    }

    /**
     * Makes the current thread to sleep for given period of time in seconds.
     *
     * @param sleepTimeInSeconds How long to sleep.
     */
    protected void holdOn(int sleepTimeInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepTimeInSeconds);
        } catch (InterruptedException ex) {
            /* intentionally suppressed InterruptedException here */
        }
    }

    protected Company getUtocatCompany() {
    	Company company = new Company();
		
		company.setName("utocat");
		company.setLegalForm("SAS");
		company.setPhone("0123456789");
		company.setAddress("Doge B, 4 Avenue des Saules");
		company.setZip("59000");
		company.setCity("Lille");
		company.setCountry("France");
		company.setInProgress(false);
		company.setSiret("MySiret");
		company.setEmail("suport@utocat.com");
		company.setIid(Long.toString(Calendar.getInstance().getTimeInMillis()));
		
		return company;
		
    }
    
    protected <T> void assertEqualInputProps(T entity1, T entity2) throws Exception {
    	
    	if (entity1 instanceof EntityBase){
    		assertEquals(((EntityBase)entity1).getId(), ((EntityBase)entity2).getId());
    	}
    	
    	if(entity1 instanceof Company) {
    		Company company1 = (Company) entity1;
    		Company company2 = (Company) entity2;
    		
    		// Check the equality on simple value
    		assertEquals(company1.getName(), company2.getName());
    		assertEquals(company1.getLegalForm(), company2.getLegalForm());
    		assertEquals(company1.getPhone(), company2.getPhone());
    		assertEquals(company1.getAddress(), company2.getAddress());
    		assertEquals(company1.getZip(), company2.getZip());
    		assertEquals(company1.getCity(), company2.getCity());
    		assertEquals(company1.getCountry(), company2.getCountry());
    		assertEquals(company1.getEmail(), company2.getEmail());
    		assertEquals(company1.getInProgress(), company2.getInProgress());
    		assertEquals(company1.getSiret(), company2.getSiret());
    		assertEquals(company1.getIid(), company2.getIid());
    		
    		// Check the equality on documents list
    		if (company1.getDocuments() == null) {
                assertNull(company2.getDocuments());
    		}else {
    			Assert.assertEquals(company1.getDocuments().size(), company2.getDocuments().size());
    			if(company1.getDocuments().size() > 0) {
    				for (int i = 0; i < company1.getDocuments().size(); i++) {
    					assertEqualInputProps(company1.getDocuments().get(i), company2.getDocuments().get(i));
    				}
    			}
    		}
    	} else if (entity1 instanceof Document) {
    		//TODO Add case for document equality
        } else {
            throw new Exception("Unsupported type");
        }

    }

}
