package com.utocat.catalizr.core.APIs.implementation;

import com.utocat.catalizr.CatalizrApi;
import com.utocat.catalizr.core.APIs.ApiBase;
import com.utocat.catalizr.core.APIs.CompanyApi;
import com.utocat.catalizr.core.enumerations.RequestType;
import com.utocat.catalizr.core.AuthorizationToken;
import com.utocat.catalizr.core.Configuration;
import com.utocat.catalizr.core.EntityBase;
import com.utocat.catalizr.core.Pagination;
import com.utocat.catalizr.core.Sorting;
import com.utocat.catalizr.entities.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * API for Compagny.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class CompanyApiImpl extends ApiBase implements CompanyApi {
	
	 /**
     * Instantiates new ClientApiImpl object.
     *
     * @param root Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    public CompanyApiImpl(CatalizrApi root) {
        super(root);
    }
    
    @Override
	public Company create(Company company) throws Exception {
		return this.createObject(Company.class, "companies_create", company);
	}
    
	@Override
	public List<Id> getAll(Pagination pagination, Sorting sort) throws Exception {
		return this.getList(Id[].class, Id.class, "companies_get_all", pagination, null, null, null);
	}

	@Override
	public Company getById(String id) throws Exception {
		return this.getObject(Company.class, "companies_get_id", id);
	}
	
	@Override
	public Company getByIid(String iid) throws Exception {
		Id companyId = this.getObject(Id.class, "companies_get_iid", iid);
		return this.getObject(Company.class, "companies_get_id", companyId.getId());
	}
    
	@Override
	public Id getIdByIid(String iid) throws Exception {
		return this.getObject(Id.class, "companies_get_iid", iid);
	}
	
	@Override
	public Document createDocument(Document document) throws Exception {
		return this.createObject(Document.class, "companies_create_documents", document);
	}
	
}
