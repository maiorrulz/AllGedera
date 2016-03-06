package com.test;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import allgedera.com.allgederaapp.App;
import allgedera.com.allgederaapp.Splash;

/**
 * Created by elash on 19/01/2016.
 */
public class SplashTest extends TestCase {

    @SmallTest
    public void testLoadGenericEvents(){
        App.genericEvents=null;
        Splash.loadGenericEvents();
        assertNotNull(App.genericEvents);
    }
}
