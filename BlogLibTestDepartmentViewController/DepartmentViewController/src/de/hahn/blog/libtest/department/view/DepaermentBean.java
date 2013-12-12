package de.hahn.blog.libtest.department.view;

import oracle.adf.controller.binding.BindingUtils;
import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;

public class DepaermentBean {
    public DepaermentBean() {
    }

    public String commitBTF() {
        BindingContext bindingContext = BindingUtils.getBindingContext();
        String controlFrame = bindingContext.getCurrentDataControlFrame();
        DataControlFrame dataControlFrame = bindingContext.findDataControlFrame(controlFrame);
        dataControlFrame.commit();
        
        return null;
    }

    public String rollbackBTF() {
        BindingContext bindingContext = BindingUtils.getBindingContext();
        String controlFrame = bindingContext.getCurrentDataControlFrame();
        DataControlFrame dataControlFrame = bindingContext.findDataControlFrame(controlFrame);
        dataControlFrame.rollback();

        return null;
    }
}
