package com.utocat.catalizr.core;

import com.utocat.catalizr.core.interfaces.StorageStrategy;

import java.io.*;
import java.util.logging.*;

/**
 * File token storage strategy implementation.
 * @author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class FileStorageStrategy implements StorageStrategy {

    private String tempDir = null;
    
    /**
     * Instantiates FileStorageStrategy object.
     * @param tempDir Temporary directory path.
     */
    public FileStorageStrategy(String tempDir) {
        this.tempDir = tempDir;
    }
    
    /**
     * Gets the currently stored token.
     * @return Currently stored token instance or null.
     */
    @Override
    public AuthorizationToken get() {
        try
        {
           FileInputStream fileIn = new FileInputStream(getFilePath());
           ObjectInputStream in = new ObjectInputStream(fileIn);
           AuthorizationToken token = (AuthorizationToken) in.readObject();
           in.close();
           fileIn.close();
           return token;
        } catch (Exception ex)
        {
            return null; // it's not an error: e.g. file not found cause not stored yet
        }
    }

    /**
     * Stores authorization token passed as an argument.
     * @param token Token instance to be stored.
     */
    @Override
    public void store(AuthorizationToken token) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(getFilePath());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(token);
            out.close();
            fileOut.close();
        } catch (Exception ex) {
            Logger.getLogger(FileStorageStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getFilePath() { 
        return tempDir + getClass().getName() + "AuthorizationToken.tmp";
    }
}
