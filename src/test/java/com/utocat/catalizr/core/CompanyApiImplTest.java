package com.utocat.catalizr.core;

import static org.testng.Assert.*;

import java.net.ResponseCache;

import org.testng.annotations.*;


import com.utocat.catalizr.core.APIs.implementation.CompanyApiImpl;
import com.utocat.catalizr.entities.Company;
import com.utocat.catalizr.entities.Id;

/**
 * {@link CompanyApiImpl} test methods
 */
public class CompanyApiImplTest extends BaseTest {
	
	private Company companyCreated = null;

	@Test
	public void createCompanyWithError() {
		
		try {
			this.api.getCompanyApi().create(new Company());
		} catch (ResponseException e) {
			assertEquals(e.getResponseHttpCode(), 400, "Should respond 400 when name is not set");
			assertEquals(e.getApiMessage(), "\"name\" is required", "Should return the right message");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void createCompany() {
		
		Company companyToCreate = new Company();
		
		companyToCreate.setName(this.getUtocatCompany().getName());
		companyToCreate.setLegalForm(this.getUtocatCompany().getLegalForm());
		companyToCreate.setPhone(this.getUtocatCompany().getPhone());
		companyToCreate.setAddress(this.getUtocatCompany().getAddress());
		companyToCreate.setZip(this.getUtocatCompany().getZip());
		companyToCreate.setCity(this.getUtocatCompany().getCity());
		companyToCreate.setCountry(this.getUtocatCompany().getCountry());
		companyToCreate.setInProgress(this.getUtocatCompany().getInProgress());
		companyToCreate.setSiret(this.getUtocatCompany().getSiret());
		
		try {
			companyToCreate = this.api.getCompanyApi().create(companyToCreate);
		} catch (ResponseException e) {
			fail(e.getApiMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertNotNull(companyToCreate, "Company should be created");
		assertNotNull(companyToCreate.getId(), "Id of company sould be created");
		assertEquals(companyToCreate.getId().length(), 24, "The length of company id should be 24");
	}
	
	@Test
	public void createCompanyFull() {
		Company companyToCreate = this.getUtocatCompany();
		
		try {
			// Store the created company for use in getCompany test
			companyCreated = this.api.getCompanyApi().create(companyToCreate);
		} catch (ResponseException e) {
			fail(e.getApiMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertNotNull(companyCreated, "Company should be created");
		assertNotNull(companyCreated.getId(), "Id of company sould be created");
		assertEquals(companyCreated.getId().length(), 24, "The length of company id should be 24");
		assertNotNull(companyCreated.getIid(), "Iid of company sould be created");
		assertEquals(companyCreated.getIid(), companyToCreate.getIid(), "Iid of company sould be the right");
		
	}
	
	@Test()
	public void getCompanyWithError() {
		try {
			this.api.getCompanyApi().getById("rrrrrrrrrrrrrrrrrrrrrrrr");
		} catch (ResponseException e) {
			assertEquals(e.getResponseHttpCode(), 400, "Should respond 400 when id is incorrect");
			assertEquals(e.getApiMessage(), "\"company_id\" must only contain hexadecimal characters", "Should return the right message");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test(dependsOnMethods={"createCompanyFull"})
	public void getCompany() {
		
		Company companyById = null;
		Company companyByIid = null;
		Id companyIdByIid = null;
		
		// Get by id
		try {
			companyById = this.api.getCompanyApi().getById(companyCreated.getId());
		} catch (ResponseException e) {
			fail(e.getApiMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// Get by iid
		try {
			companyByIid = this.api.getCompanyApi().getByIid(companyCreated.getIid());
		} catch (ResponseException e) {
			fail(e.getApiMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// Get id by Iid
		try {
			companyIdByIid = this.api.getCompanyApi().getIdByIid(companyCreated.getIid());
		} catch (ResponseException e) {
			fail(e.getApiMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertNotNull(companyById, "Company should be retrived by his id");
		assertNotNull(companyByIid, "Company should be retrived by his iid");
		assertNotNull(companyIdByIid, "Company id should be retrived by his iid");
		
		// companyById and companyByIid should be equals
		try {
			assertEqualInputProps(companyById, companyByIid);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		// All id should be equals
		assertEquals(companyById.getId(), companyByIid.getId());
		assertEquals(companyById.getId(), companyIdByIid.getId());
	}
}
