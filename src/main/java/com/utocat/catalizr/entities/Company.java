package com.utocat.catalizr.entities;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.utocat.catalizr.core.EntityBase;

/**
 * Company entity.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class Company extends EntityBase {
	
	/**
     * Company name.
     */
    @SerializedName("name")
    private String name;
	
    /**
     * Legal form of company.
     */
    @SerializedName("legal_form")
    private String legalForm;
	
    /**
     * Company phone.
     */
    @SerializedName("phone")
    private String phone;
    
    /**
     * Company address.
     */
    @SerializedName("address")
    private String address;
    
    /**
     * Company zip.
     */
    @SerializedName("zip")
    private String zip;
    /**
     * Company city.
     */
    @SerializedName("city")
    private String city;
    
    /**
     * Company country.
     */
    @SerializedName("country")
    private String country;
    
    /**
     * Company email.
     */
    @SerializedName("email")
    private String email;
    
    /**
     * in_progress.
     */
    @SerializedName("in_progress")
    private Boolean inProgress;
	
    /**
     * Company siret.
     */
    @SerializedName("siret")
    private String siret;
    /**
     * Company iid.
     */
    @SerializedName("iid")
    private String iid;
    
    /**
     * Company documents.
     */
    @SerializedName("documents")
    private List<Document> documents;
    
    
    
    public String getLegalForm() {
		return legalForm;
	}

	public void setLegalForm(String legalForm) {
		this.legalForm = legalForm;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getInProgress() {
		return inProgress;
	}

	public void setInProgress(Boolean inProgress) {
		this.inProgress = inProgress;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
