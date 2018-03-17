package com.utocat.catalizr.core;

import static org.testng.Assert.*;
import org.testng.annotations.*;

/**
 *@author Cedric PAUCHET cedric.pauchet@utocat.com
 */
public class ObjectToolTest {
    
    public ObjectToolTest() {
    }

    /**
     * Test of nonNull method, of class ObjectTool.
     */
    @Test
    public void testNonNull() {
        Object object = null;
        assertFalse(ObjectTool.nonNull(object));
        object = new Object();
        assertTrue(ObjectTool.nonNull(object));
    }

    /**
     * Test of isNull method, of class ObjectTool.
     */
    @Test
    public void testIsNull() {
        Object object = null;
        assertTrue(ObjectTool.isNull(object));
        object = new Object();
        assertFalse(ObjectTool.isNull(object));
    }
    
}
