package com.utocat.catalizr.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.utocat.catalizr.CatalizrApi;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Helper class to manage Json conversion.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class JsonTool {

	public static String mapToJson(Map<String,String> map) {
		Gson gson = new GsonBuilder().create();
        Type typeOfSrc = new TypeToken<Map<String, String>>(){}.getType();
        
        return gson.toJson(map,typeOfSrc);
    }
}
