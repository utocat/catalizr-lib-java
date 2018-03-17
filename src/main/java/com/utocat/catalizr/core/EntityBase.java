package com.utocat.catalizr.core;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Base abstract class for entities. Provides common properties.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public abstract class EntityBase extends Dto {

    /**
     * Unique identifier.
     */
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the collection of read-only fields names.
     *
     * @return List of field names.
     */
    @Override
    public ArrayList<String> getReadOnlyProperties() {

        ArrayList<String> result = new ArrayList<String>();

        result.add("id");

        return result;
    }
}
