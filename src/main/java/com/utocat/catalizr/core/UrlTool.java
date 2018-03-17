package com.utocat.catalizr.core;

import com.utocat.catalizr.CatalizrApi;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Helper class to manage URLs.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class UrlTool {

    /**
     * Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    private CatalizrApi root;

    /**
     * Instantiates new UrlTool object.
     *
     * @param root Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    public UrlTool(CatalizrApi root) {
        this.root = root;
    }

    private String getHost() throws Exception {

        if (root.getConfig().getBaseUrl() == null || root.getConfig().getBaseUrl().length() == 0)
            throw new Exception("CatalizrApi.config.BaseUrl setting is not defined.");

        URL baseUrl = new URL(root.getConfig().getBaseUrl());

        return baseUrl.getHost();
    }

    /**
     * Gets REST url.
     *
     * @param urlKey      Url key.
     * @return Final REST url.
     * @throws UnsupportedEncodingException
     */
    public String getRestUrl(String urlKey) throws UnsupportedEncodingException {
        return getRestUrl(urlKey, null, null);
    }

    /**
     * Gets REST url.
     *
     * @param urlKey      Url key.
     * @param pagination  Pagination object.
     * @return Final REST url.
     * @throws UnsupportedEncodingException
     */
    public String getRestUrl(String urlKey, Pagination pagination) throws UnsupportedEncodingException {
        return getRestUrl(urlKey, pagination, null);
    }

    /**
     * Gets REST url.
     *
     * @param urlKey              Url key.
     * @param pagination          Pagination object.
     * @param additionalUrlParams Additional parameters.
     * @return Final REST url.
     * @throws UnsupportedEncodingException
     */
    public String getRestUrl(String urlKey, Pagination pagination, Map<String, String> additionalUrlParams) throws UnsupportedEncodingException {

        String url = "/v1" + urlKey;
        
        Boolean paramsAdded = false;
        if (pagination != null) {
            url += "?page=" + pagination.getPage() + "&per_page=" + pagination.getItemsPerPage();
            paramsAdded = true;
        }

        if (additionalUrlParams != null) {
            for (Entry<String, String> entry : additionalUrlParams.entrySet()) {
                url += paramsAdded ? "&" : "?";
                url += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "ISO-8859-1");
                paramsAdded = true;
            }
        }

        return url;
    }

    /**
     * Gets complete url.
     *
     * @param restUrl Rest url.
     * @return Complete url.
     */
    public String getFullUrl(String restUrl) {

        String result = "";

        try {
            result = (new URL(root.getConfig().getBaseUrl())).getProtocol() + "://" + this.getHost() + restUrl;
        } catch (Exception ex) {
        }

        return result;
    }
}
