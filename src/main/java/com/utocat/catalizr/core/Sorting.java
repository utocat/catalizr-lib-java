package com.utocat.catalizr.core;

import com.utocat.catalizr.core.enumerations.SortDirection;

import java.util.HashMap;
import java.util.Map;

/**
 * Base sorting object.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class Sorting {

    /**
     * Fields separator in sort parameters for URL query.
     */
    private final String sortFieldSeparator = "_";

    /**
     * Sort query parameter name.
     */
    private final String sortUrlParameterName = "Sort";

    /**
     * Collection of fields to sort by and sort directions.
     */
    private Map<String, SortDirection> sortFields;

    /**
     * Adds field to sort by.
     *
     * @param fieldName     Property name to sort by.
     * @param sortDirection Sort direction.
     */
    public void addField(String fieldName, SortDirection sortDirection) {
        if (sortFields == null) sortFields = new HashMap<String, SortDirection>();

        sortFields.put(fieldName, sortDirection);
    }

    /**
     * Gets sort parameters.
     *
     * @return
     */
    public Map<String, String> getSortParameter() {

        return new HashMap<String, String>() {{
            put(sortUrlParameterName, getFields());
        }};
    }

    public String getFields() {
        String sortValues = "";
        for (Map.Entry<String, SortDirection> entry : sortFields.entrySet()) {
            if (!sortValues.equals(""))
                sortValues += sortFieldSeparator;

            sortValues += entry.getKey() + ":" + entry.getValue();
        }

        return sortValues;
    }
}