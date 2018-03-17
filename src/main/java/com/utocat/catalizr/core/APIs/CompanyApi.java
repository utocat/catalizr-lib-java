package com.utocat.catalizr.core.APIs;

import com.utocat.catalizr.core.EntityBase;
import com.utocat.catalizr.core.Pagination;
import com.utocat.catalizr.core.Sorting;
import com.utocat.catalizr.entities.Company;
import com.utocat.catalizr.entities.Document;
import com.utocat.catalizr.entities.Id;

import java.util.List;

/**
 * 
 *  @author Cedric PAUCHET cedric.pauchet@utocat.com
 *
 */
public interface CompanyApi {

	/**
     * Creates new company.
     * @param company           Company instance to be created.
     * @return                  Company instance returned from API.
     * @throws Exception
     */
    Company create(Company company) throws Exception;
	
    /**
     * View all companies
     *
     * @param pagination Pagination object
     * @param sort       Sorting object
     * @return All companies
     * @throws Exception
     */
    List<Id> getAll(Pagination pagination, Sorting sort) throws Exception;

    /**
     * Get a company with his id
     *
     * @return Company instance returned from API.
     * @throws Exception
     */
    Company getById(String id) throws Exception;
    
    /**
     * Get a company with his iid
     *
     * @return Company instance returned from API.
     * @throws Exception
     */
    Company getByIid(String iid) throws Exception;
    
    /**
     * Get a Id with the external IID
     *
     * @return Id instance returned from API.
     * @throws Exception
     */
    Id getIdByIid(String iid) throws Exception;
    
    /**
     * Create metadatas for a document, you can added directly the file under 2Mb, for bigger: you'll receive an callback url for uploaded the file
     * @param document  Document instance to be created.
     * @return The document instance with the new id
     */
    Document createDocument(Document document) throws Exception;
}
