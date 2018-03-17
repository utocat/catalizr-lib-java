package com.utocat.catalizr.core.APIs;

import com.utocat.catalizr.core.*;
import com.utocat.catalizr.core.enumerations.RequestType;
import com.utocat.catalizr.CatalizrApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base class for all API classes.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public abstract class ApiBase {

    /**
     * Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    protected CatalizrApi root;

    protected CatalizrApi getRoot() {
        return root;
    }

    /**
     * Array with REST URL and request type.
     */
    private Map<String, String[]> methods = new HashMap<String, String[]>(){{

    	put("authentication_post", new String[] { "/authorize", RequestType.POST.toString() });
    	
    	put("companies_create", new String[] { "/companies", RequestType.POST.toString() });
        put("companies_get_all", new String[] { "/companies", RequestType.GET.toString() });
        put("companies_get_id", new String[] { "/companies/%s", RequestType.GET.toString() });
        put("companies_get_iid", new String[] { "/companies/iid/%s", RequestType.GET.toString() });
        put("companies_create_documents", new String[] { "/companies/%s/documents", RequestType.GET.toString() });
        
    }};

    /**
     * Creates new API instance.
     * @param root Root/parent instance that holds the {@link AuthorizationToken} and {@link Configuration} instance.
     */
    public ApiBase(CatalizrApi root) {
        this.root = root;
    }

    /**
     * Gets the URL of REST Catalizr API.
     * @param key   The method key to get URL of.
     * @return      The URL string of given method.
     */
    protected String getRequestUrl(String key) throws Exception {
        String result = "";
        try {
            result = this.methods.get(key)[0];
        } catch (Exception ex) {
            throw new Exception("Unknown method key: " + key);
        }
        return result;
    }

    /**
     * Gets the HTTP request verb.
     * @param key   The method key.
     * @return      One of the HTTP verbs: GET, PUT or POST.
     */
    protected String getRequestType(String key) {
        return this.methods.get(key)[1];
    }

    /**
     * Creates the Dto instance.
     * @param <T>               Type on behalf of which the request is being called.
     * @param classOfT          Type on behalf of which the request is being called.
     * @param methodKey         Relevant method key.
     * @param entity            Dto instance that is going to be sent.
     * @param entityId          Entity identifier.
     * @param secondEntityId    Second entity identifier.
     * @return                  The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T createObject(Class<T> classOfT, String methodKey, T entity, String entityId, String secondEntityId) throws Exception {

        String urlMethod;

        if (entityId.length() == 0)
            urlMethod = this.getRequestUrl(methodKey);
        else if (secondEntityId.length() == 0)
            urlMethod = String.format(this.getRequestUrl(methodKey), entityId);
        else
            urlMethod = String.format(this.getRequestUrl(methodKey), entityId, secondEntityId);

        RestTool rest = new RestTool(this.root, true);
        T result = rest.request(classOfT, urlMethod, this.getRequestType(methodKey), null, null, entity, null);

        return result;

    }

    /**
     * Creates the Dto instance.
     * @param <T>               Type on behalf of which the request is being called.
     * @param classOfT          Type on behalf of which the request is being called.
     * @param methodKey         Relevant method key.
     * @param entity            Dto instance that is going to be sent.
     * @param entityId          Entity identifier.
     * @return                  The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T createObject(Class<T> classOfT, String methodKey, T entity, String entityId) throws Exception {
        return createObject(classOfT, methodKey, entity, entityId, "");
    }

    /**
     * Creates the Dto instance.
     * @param <T>               Type on behalf of which the request is being called.
     * @param classOfT          Type on behalf of which the request is being called.
     * @param methodKey         Relevant method key.
     * @param entity            Dto instance that is going to be sent.
     * @return                  The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T createObject(Class<T> classOfT, String methodKey, T entity) throws Exception {
        return createObject(classOfT, methodKey, entity, "");
    }

    /**
     * Gets the Dto instance from API.
     * @param <T>               Type on behalf of which the request is being called.
     * @param classOfT          Type on behalf of which the request is being called.
     * @param methodKey         Relevant method key.
     * @param entityId          Entity identifier.
     * @param secondEntityId    Entity identifier for the second entity.
     * @return                  The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T getObject(Class<T> classOfT, String methodKey, String entityId, String secondEntityId) throws Exception {

        String urlMethod = String.format(this.getRequestUrl(methodKey), entityId, secondEntityId);

        RestTool rest = new RestTool(this.root, true);
        T response = rest.request(classOfT, urlMethod, this.getRequestType(methodKey));

        return response;
    }

    /**
     * Gets the Dto instance from API.
     * @param <T>           Type on behalf of which the request is being called.
     * @param classOfT      Type on behalf of which the request is being called.
     * @param methodKey     Relevant method key.
     * @param entityId      Entity identifier.
     * @return              The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T getObject(Class<T> classOfT, String methodKey, String entityId) throws Exception {
        return getObject(classOfT, methodKey, entityId, "");
    }

    /**
     * Gets the array of Dto instances from API.
     * @param <T>                   Type on behalf of which the request is being called.
     * @param classOfT              Type on behalf of which the request is being called.
     * @param methodKey             Relevant method key.
     * @param pagination            Pagination object.
     * @param entityId              Entity identifier.
     * @param filter                Collection of key-value pairs of filter parameters.
     * @param sorting               Sorting instance.
     * @return                      The array of Dto instances returned from API.
     * @throws Exception
     */
    protected <T extends Dto> List<T> getList(Class<T[]> classOfT, Class<T> classOfTItem, String methodKey, Pagination pagination, String entityId, String secondEntityId, Map<String, String> filter, Sorting sorting) throws Exception {

        String urlMethod = "";

        if (entityId != null && entityId.length() > 0 && secondEntityId != null && secondEntityId.length() > 0)
            urlMethod = String.format(this.getRequestUrl(methodKey), entityId, secondEntityId);
        else if (entityId != null && entityId.length() > 0)
            urlMethod = String.format(this.getRequestUrl(methodKey), entityId);
        else
            urlMethod = this.getRequestUrl(methodKey);

        if (pagination == null) {
            pagination = new Pagination();
        }

        Map<String, String> additionalUrlParams = new HashMap<String, String>();

        if (filter != null) {
            additionalUrlParams.putAll(filter);
        }

        if (sorting != null) {
            additionalUrlParams.putAll(sorting.getSortParameter());
        }

        RestTool rest = new RestTool(this.root, true);

        return rest.requestList(classOfT, classOfTItem, urlMethod, this.getRequestType(methodKey), null, pagination, additionalUrlParams);

    }

    protected <T extends Dto> List<T> getList(Class<T[]> classOfT, Class<T> classOfTItem, String methodKey, Pagination pagination, String entityId, Map<String, String> filter, Sorting sorting) throws Exception {
        return getList(classOfT, classOfTItem, methodKey, pagination, entityId, null, filter, sorting);
    }

    /**
     * Gets the array of Dto instances from API.
     * @param <T>           Type on behalf of which the request is being called.
     * @param classOfT      Type on behalf of which the request is being called.
     * @param methodKey     Relevant method key.
     * @param pagination    Pagination object.
     * @param entityId      Entity identifier.
     * @param sorting       Sorting object.
     * @return              The array of Dto instances returned from API.
     * @throws Exception
     */
    protected <T extends Dto> List<T> getList(Class<T[]> classOfT, Class<T> classOfTItem, String methodKey, Pagination pagination, String entityId, Sorting sorting) throws Exception {
        return getList(classOfT, classOfTItem, methodKey, pagination, entityId, null, null, sorting);
    }

    /**
     * Gets the array of Dto instances from API.
     * @param <T>           Type on behalf of which the request is being called.
     * @param classOfT      Type on behalf of which the request is being called.
     * @param methodKey     Relevant method key.
     * @param pagination    Pagination object.
     * @param entityId      Entity identifier.
     * @return              The array of Dto instances returned from API.
     * @throws Exception
     */
    protected <T extends Dto> List<T> getList(Class<T[]> classOfT, Class<T> classOfTItem, String methodKey, Pagination pagination, String entityId) throws Exception {
        return getList(classOfT, classOfTItem, methodKey, pagination, entityId, null, null, null);
    }

    /**
     * Gets the array of Dto instances from API.
     * @param <T>           Type on behalf of which the request is being called.
     * @param classOfT      Type on behalf of which the request is being called.
     * @param methodKey     Relevant method key.
     * @param pagination    Pagination object.
     * @return              The array of Dto instances returned from API.
     * @throws Exception
     */
    protected <T extends Dto> List<T> getList(Class<T[]> classOfT, Class<T> classOfTItem, String methodKey, Pagination pagination) throws Exception {
        return getList(classOfT, classOfTItem, methodKey, pagination, "", null);
    }

    /**
     * Gets the array of Dto instances from API.
     * @param <T>           Type on behalf of which the request is being called.
     * @param classOfT      Type on behalf of which the request is being called.
     * @param methodKey     Relevant method key.
     * @param pagination    Pagination object.
     * @param sorting       Sorting object.
     * @return              The array of Dto instances returned from API.
     * @throws Exception
     */
    protected <T extends Dto> List<T> getList(Class<T[]> classOfT, Class<T> classOfTItem, String methodKey, Pagination pagination, Sorting sorting) throws Exception {
        return getList(classOfT, classOfTItem, methodKey, pagination, "", sorting);
    }

    /**
     * Saves the Dto instance.
     * @param <T>           Type on behalf of which the request is being called.
     * @param classOfT      Type on behalf of which the request is being called.
     * @param methodKey     Relevant method key.
     * @param entity        Dto instance that is going to be sent.
     * @return              The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T updateObject(Class<T> classOfT, String methodKey, T entity) throws Exception {
        return updateObject(classOfT, methodKey, entity, "", "");
    }

    /**
     * Saves the Dto instance.
     * @param <T>               Type on behalf of which the request is being called.
     * @param classOfT          Type on behalf of which the request is being called.
     * @param methodKey         Relevant method key.
     * @param entity            Dto instance that is going to be sent.
     * @param entityId          Entity identifier.
     * @return                  The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T updateObject(Class<T> classOfT, String methodKey, T entity, String entityId) throws Exception {
        return updateObject(classOfT, methodKey, entity, entityId, "");
    }

    /**
     * Saves the Dto instance.
     * @param <T>               Type on behalf of which the request is being called.
     * @param classOfT          Type on behalf of which the request is being called.
     * @param methodKey         Relevant method key.
     * @param entity            Dto instance that is going to be sent.
     * @param entityId          Entity identifier.
     * @param secondEntityId    Second entity identifier.
     * @return                  The Dto instance returned from API.
     * @throws Exception
     */
    protected <T extends Dto> T updateObject(Class<T> classOfT, String methodKey, T entity, String entityId, String secondEntityId) throws Exception {

        if (entity instanceof EntityBase) {

            String urlMethod;

            if (secondEntityId.length() > 0) {
                urlMethod = String.format(this.getRequestUrl(methodKey), entityId, secondEntityId);
            } else if (entityId.length() > 0) {
                urlMethod = String.format(this.getRequestUrl(methodKey), entityId, ((EntityBase)entity).getId());
            } else {
                urlMethod = String.format(this.getRequestUrl(methodKey), ((EntityBase)entity).getId());
            }

            RestTool rest = new RestTool(this.root, true);
            return rest.request(classOfT, urlMethod, this.getRequestType(methodKey), null, null, entity, null);
        } else {
            return null;
        }
    }
}
