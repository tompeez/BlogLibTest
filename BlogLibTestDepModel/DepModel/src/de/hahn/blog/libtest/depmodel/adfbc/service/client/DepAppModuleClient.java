package de.hahn.blog.libtest.depmodel.adfbc.service.client;

import de.hahn.blog.libtest.depmodel.adfbc.service.common.DepAppModule;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Nov 18 18:26:06 CET 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepAppModuleClient extends ApplicationModuleImpl implements DepAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public DepAppModuleClient() {
    }

    public String helloDepModule(String param) {
        Object _ret = this.riInvokeExportedMethod(this,"helloDepModule",new String [] {"java.lang.String"},new Object[] {param});
        return (String)_ret;
    }
}
