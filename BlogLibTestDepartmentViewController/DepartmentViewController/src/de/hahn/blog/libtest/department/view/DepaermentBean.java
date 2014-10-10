package de.hahn.blog.libtest.department.view;

import de.hahn.blog.libtest.common.faces.util.TaskFlowUtils;

import oracle.adf.controller.binding.BindingUtils;
import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.share.logging.ADFLogger;

public class DepaermentBean {
    private static ADFLogger logger = ADFLogger.createADFLogger(DepaermentBean.class);

    public DepaermentBean() {
    }

    public String commitBTF() {
        logger.info("Commit Departments BTF");
        TaskFlowUtils tfu = new TaskFlowUtils();
        tfu.commitTaskFlow();
        return null;
    }

    public String rollbackBTF() {
        logger.info("Rollback Departments BTF");
        TaskFlowUtils tfu = new TaskFlowUtils();
        tfu.rollbackTaskFlow();
        return null;
    }
}
