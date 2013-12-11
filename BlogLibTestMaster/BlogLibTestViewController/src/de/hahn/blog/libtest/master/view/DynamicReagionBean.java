package de.hahn.blog.libtest.master.view;

import oracle.adf.controller.TaskFlowId;

public class DynamicReagionBean {
    private String taskFlowId = "/WEB-INF/de/hahn/blog/libtest/department/department-btf.xml#department-btf";

    public DynamicReagionBean() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public String departmentbtf() {
        taskFlowId = "/WEB-INF/de/hahn/blog/libtest/department/department-btf.xml#department-btf";
        return null;
    }

    public String employeebtf() {
        taskFlowId = "/WEB-INF/de/hahn/blog/libtest/employee/employee-btf.xml#employee-btf";
        return null;
    }

    public String regionbtf() {
        taskFlowId = "/WEB-INF/de/hahn/blog/libtest/region/region-btf.xml#region-btf";
        return null;
    }
}
