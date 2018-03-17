package com.utocat.catalizr.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Configuration settings.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class Configuration {

    /**
     * Catalizr api public key.
     */
    private String apiPublicKey = "";
    
    /**
     * Catalizr api private key.
     */
    private String apiPrivateKey = "";

    /**
     * Base URL to Catalizr API.
     */
    private String baseUrl = "https://qualif.api.catalizr.io";

    /**
     * [INTERNAL USAGE ONLY] Switch debug mode: log all request and response data.
     */
    private boolean debugMode = false;

    /**
     * Connection Timeout.
     */
    private int connectTimeout = 60000;

    /**
     * Connection Read Timeout.
     */
    private int readTimeout = 60000;
    
    /**
     * Catalizr Version
     */
    private String version;
    

    public String getApiPublicKey() {
        return apiPublicKey;
    }

    public void setApiPublicKey(String apiPublicKey) {
        this.apiPublicKey = apiPublicKey;
    }
    
    public String getApiPrivateKey() {
		return apiPrivateKey;
	}

	public void setApiPrivateKey(String apiPrivateKey) {
		this.apiPrivateKey = apiPrivateKey;
	}

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * Get Connection Timeout
     *
     * @return int Connection Timeout in millis.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Set Connection Timeout
     *
     * @param connectTimeout connection timeout in millis.
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * Get Connection Read Timeout
     *
     * @return int Connection Timeout in millis.
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Set Connection Read Timeout
     *
     * @param readTimeout connection read timeout in millis.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    
    /**
     * Get Catalizr Version
     * @return String Catalizr Version
     */
    public String getVersion() {
        if (ObjectTool.isNull(version)) {
            version = readCatalizrVersion();
        }
        return version;
    }

    /**
     * Read Catalizr version from catalizr properties
     * @return String Catalizr Version
     */
    private String readCatalizrVersion() {
        try {
            Properties prop = new Properties();
            InputStream input = getClass().getResourceAsStream("catalizr.properties");
            prop.load(input);
            return prop.getProperty("version");
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "unknown";
    }

	
}
