package com.utocat.catalizr.entities;

import com.google.gson.annotations.SerializedName;
import com.utocat.catalizr.core.EntityBase;
import com.utocat.catalizr.core.enumerations.DocumentOrigin;
import com.utocat.catalizr.core.enumerations.DocumentReferenceType;

/**
 * Document entity.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class Document extends EntityBase {
	
	/**
     * Type de document, sert de repertoire principal aux document dans le NAS.
     */
    @SerializedName("origin")
    private DocumentOrigin origin;
	
    /**
     * Reference d'entité, utile aussi au lieu de stockage dans le NAS.
     */
    @SerializedName("reference_type")
    private DocumentReferenceType referenceType;
	
    /**
     * file name in file system.
     */
    @SerializedName("name")
    private String name;
    
    /**
     * Document name in file system.
     */
    @SerializedName("name_stored")
    private String nameStored;
    
    /**
     * Type of the document ex: STATUS / KBIS.
     */
    @SerializedName("type_document")
    private String typeDocument;
    
    /**
     * Extension type of the document ex: image/png.
     */
    @SerializedName("type_mime")
    private String typeMime;
    
    /**
     * Date the document has been signed.
     */
    @SerializedName("signature_date")
    private String signatureDate;
    
    /**
     * Store in file system.
     */
    @SerializedName("stored")
    private String stored;

	public DocumentOrigin getOrigin() {
		return origin;
	}

	public void setOrigin(DocumentOrigin origin) {
		this.origin = origin;
	}

	public DocumentReferenceType getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(DocumentReferenceType referenceType) {
		this.referenceType = referenceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameStored() {
		return nameStored;
	}

	public void setNameStored(String nameStored) {
		this.nameStored = nameStored;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getTypeMime() {
		return typeMime;
	}

	public void setTypeMime(String typeMime) {
		this.typeMime = typeMime;
	}

	public String getSignatureDate() {
		return signatureDate;
	}

	public void setSignatureDate(String signatureDate) {
		this.signatureDate = signatureDate;
	}

	public String getStored() {
		return stored;
	}

	public void setStored(String stored) {
		this.stored = stored;
	}
}
