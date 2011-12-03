/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.opencyc;

import java.io.IOException;
import java.net.UnknownHostException;

import org.ofbiz.base.container.Container;
import org.ofbiz.base.container.ContainerException;
import org.ofbiz.base.util.Debug;
import org.opencyc.api.CycAccess;
import org.opencyc.api.CycApiException;
import org.opencyc.cycobject.CycFort;

public class OpenCycContainer implements Container {
    
    public final static String module = OpenCycContainer.class.getName();
    
    protected CycAccess cycAccess = null;

    @Override
    public void init(String[] args, String configFile)
            throws ContainerException {
        
        try {
            cycAccess = new CycAccess();
            cycAccess.traceOn();
        } catch (UnknownHostException e) {
            Debug.logError(e, module);
            throw new ContainerException(e);
        } catch (IOException e) {
            Debug.logError(e, module);
            throw new ContainerException(e);
        }
    }

    @Override
    public boolean start() throws ContainerException {
        try {
            //cycAccess.makeCycConstant("Billy");
            CycFort billy = cycAccess.getKnownConstantByName("Billy");
        } catch (CycApiException e) {
            Debug.logWarning(e, module);
        } catch (UnknownHostException e) {
            Debug.logError(e, module);
            throw new ContainerException(e);
        } catch (IOException e) {
            Debug.logError(e, module);
            throw new ContainerException(e);
        }
    
        return false;
    }

    @Override
    public void stop() throws ContainerException {
        
    }

}
