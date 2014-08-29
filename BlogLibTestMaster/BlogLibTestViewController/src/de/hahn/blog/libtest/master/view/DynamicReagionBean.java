package de.hahn.blog.libtest.master.view;

import oracle.adf.controller.TaskFlowId;

public class DynamicReagionBean {
    private String taskFlowId = "/WEB-INF/de/hahn/blog/libtest/department/department-btf.xml#department-btf";
    private String dummyParameter = "0";

    public DynamicReagionBean() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public String departmentbtf() {
        incrementDummy();
        taskFlowId = "/WEB-INF/de/hahn/blog/libtest/department/department-btf.xml#department-btf";
        return null;
    }

    private void incrementDummy() {
        int count = Integer.parseInt(dummyParameter);
        count++;
        setDummyParameter(String.valueOf(count));
    }

    public String employeebtf() {
        incrementDummy();
        taskFlowId = "/WEB-INF/de/hahn/blog/libtest/employee/employee-btf.xml#employee-btf";
        return null;
    }

    public String regionbtf() {
        incrementDummy();
        taskFlowId = "/WEB-INF/de/hahn/blog/libtest/region/region-btf.xml#region-btf";
        return null;
    }

    public void setDummyParameter(String dummyParameter) {
        this.dummyParameter = dummyParameter;
    }

    public String getDummyParameter() {
        return dummyParameter;
    }
}
