package de.hahn.blog.libtest.master.model.adfbc.service;

import de.hahn.blog.libtest.master.model.adfbc.service.common.BlogLibTestMasterAppModule;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.server.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Nov 18 18:30:54 CET 2013
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BlogLibTestMasterAppModuleImpl extends ApplicationModuleImpl implements BlogLibTestMasterAppModule {
    private static ADFLogger logger = ADFLogger.createADFLogger(BlogLibTestMasterAppModuleImpl.class);
    /**
     * This is the default constructor (do not remove).
     */
    public BlogLibTestMasterAppModuleImpl() {
    }

    /**
     * Container's getter for DepAppModule.
     * @return DepAppModule
     */
    public ApplicationModuleImpl getDepAppModule() {
        return (ApplicationModuleImpl)findApplicationModule("DepAppModule");
    }

    /**
     * Container's getter for EmpAppModule.
     * @return EmpAppModule
     */
    public ApplicationModuleImpl getEmpAppModule() {
        return (ApplicationModuleImpl)findApplicationModule("EmpAppModule");
    }
    public String helloMasterModule(String param) {
        logger.info("Param = " + param);
        if (param != null)
            return "Hello " + param;
        return "Can't say hello to nobody";
    }

    /**
     * Container's getter for RegionModelAppModule.
     * @return RegionModelAppModule
     */
    public ApplicationModuleImpl getRegionAppModule() {
        return (ApplicationModuleImpl)findApplicationModule("RegionAppModule");
    }
}
