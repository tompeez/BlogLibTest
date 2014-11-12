package de.hahn.blog.libtest.common.faces.util;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowContext;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.controller.ViewPortContext;
import oracle.adf.controller.metadata.MetadataService;
import oracle.adf.controller.metadata.model.DataControlScopeType;
import oracle.adf.controller.metadata.model.NamedParameter;
import oracle.adf.controller.metadata.model.TaskFlowDefinition;
import oracle.adf.controller.metadata.model.TaskFlowInputParameter;
import oracle.adf.controller.metadata.model.TransactionType;
import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.context.AdfFacesContext;

/**
 * ADF task flow utilities for use with JDev/ADF 11g+.
 *
 * Available under the Creative Commons Attribution 3.0 Unported License.
 * See: http://one-size-doesnt-fit-all.blogspot.com/p/terms-conditions.html
 *
 * Absolutely no warranty implied, *use*at*your*own*risk*.  This code has been mostly used
 * for checking task flow features and hasn't been used in a production environment.
 *
 * Author: Chris Muir @ Oracle.com
 * Date: 19th April 2012
 */
public class TaskFlowUtils {
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(TaskFlowUtils.class);

    /**
     * Get the current task flow name
     * @return current task flow name
     */
    public String getTaskFlowName() {
        MetadataService metadataService = MetadataService.getInstance();
        TaskFlowDefinition taskFlowDefinition = metadataService.getTaskFlowDefinition(getCurrentTaskFlowId());
        String taskFlowName = null;
        if (taskFlowDefinition != null) {
            TaskFlowId taskFlowId = taskFlowDefinition.getTaskFlowId();

            if (taskFlowId != null) {
                taskFlowName = taskFlowDefinition.getTaskFlowId().getFullyQualifiedName();
            } else {
                taskFlowName = "Null";
            }
        } else {
            taskFlowName = "Null";
        }

        return taskFlowName;
    }

    /**
     * Get the current task flow control scope name
     * @return current task flow control scope name
     */
    public String getTaskFlowControlScopeName() {
        MetadataService metadataService = MetadataService.getInstance();
        String controlScopeTypeString;
        TaskFlowDefinition taskFlowDefinition = metadataService.getTaskFlowDefinition(getCurrentTaskFlowId());
        if (taskFlowDefinition != null) {
            String taskFlowName = taskFlowDefinition.getTaskFlowId().getFullyQualifiedName();
            DataControlScopeType controlScopeType = taskFlowDefinition.getDataControlScopeType();

            if (controlScopeType == null || controlScopeType == DataControlScopeType.SHARED) {
                controlScopeTypeString = "Shared Data Control Scope";
            } else if (controlScopeType == DataControlScopeType.ISOLATED) {
                controlScopeTypeString = "Isolated Data Control Scope";
            } else {
                controlScopeTypeString = "UNKNOWN Data Control Scope";
            }
        } else {
            controlScopeTypeString = "Null";
        }

        return controlScopeTypeString;
    }

    /**
     * Get the current task flow transaction type name
     * @return current task flow transaction type name
     */
    public String getTaskFlowControlTransactionTypeName() {
        MetadataService metadataService = MetadataService.getInstance();
        String transactionTypeString;
        TaskFlowDefinition taskFlowDefinition = metadataService.getTaskFlowDefinition(getCurrentTaskFlowId());
        if (taskFlowDefinition != null) {
            String taskFlowName = taskFlowDefinition.getTaskFlowId().getFullyQualifiedName();

            TransactionType transactionType = taskFlowDefinition.getTransactionType();

            if (transactionType == null) {
                transactionTypeString = "-No Controller Transaction-";
            } else if (transactionType == TransactionType.NEW_TRANSACTION) {
                transactionTypeString = "Always Begin New Transaction";
            } else if (transactionType == TransactionType.REQUIRES_TRANSACTION) {
                transactionTypeString = "Use Existing Transaction if Possible";
            } else if (transactionType == TransactionType.REQUIRES_EXISTING_TRANSACTION) {
                transactionTypeString = "Always Use Existing Transaction";
            } else {
                transactionTypeString = "UNKNOWN Transaction Type";
            }
        } else {
            transactionTypeString = "Null";
        }

        return transactionTypeString;
    }

    /**
     * Formats a given map of task flow parameters for printout
     * @param btfParameters
     * @return map of formatted parameters
     */
    public HashMap<String, String> formatTaskFlowParameters(Map btfParameters) {
        HashMap<String, String> taskFlowParameterValues = new HashMap<String, String>();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        Map<String, Object> pageFlowScope = adfFacesContext.getPageFlowScope();

        for (Object parameter : btfParameters.values()) {
            NamedParameter namedParameter = (NamedParameter) parameter;
            String parameterName = namedParameter.getName();
            String parameterExpression = namedParameter.getValueExpression();
            Object parameterValue;
            String stringValue;

            if (parameterExpression == null) {
                parameterValue = pageFlowScope.get(parameterName);
            } else {
                parameterValue = application.evaluateExpressionGet(facesContext, parameterExpression, Object.class);
            }

            if (parameterValue != null) {
                try {
                    stringValue = parameterValue.toString();
                } catch (Exception e) {
                    LOGGER.finest("Information: This is not a stack trace!!", e);
                    stringValue = "<complex>";
                }
            } else {
                stringValue = "<null>";
            }

            taskFlowParameterValues.put(parameterName, stringValue);
        }
        return taskFlowParameterValues;
    }

    /**
     * Gets the current task flow id
     * @return the current task flow id
     */
    public TaskFlowId getCurrentTaskFlowId() {
        ControllerContext controllerContext = ControllerContext.getInstance();
        ViewPortContext currentViewPort = controllerContext.getCurrentViewPort();
        TaskFlowContext taskFlowContext = currentViewPort.getTaskFlowContext();
        TaskFlowId taskFlowId = taskFlowContext.getTaskFlowId();

        return taskFlowId;
    }

    /**
     * Gets the task flow definition of the given task flow id
     * @param taskFlowId to get the task flow definition for
     * @return taks flow definition
     */
    public TaskFlowDefinition getTaskFlowDefinition(TaskFlowId taskFlowId) {
        MetadataService metadataService = MetadataService.getInstance();
        TaskFlowDefinition taskFlowDefinition = metadataService.getTaskFlowDefinition(taskFlowId);

        return taskFlowDefinition;
    }

    /**
     * Get the current task flow input parameters
     * @return map with the current tsk flow input parameters
     */
    public Map<String, TaskFlowInputParameter> getCurrentTaskFlowInputParameters() {
        return getInputParameters(getCurrentTaskFlowId());
    }

    /**
     * Get the task flow input parametes of the given task flow
     * @param taskFlowId
     * @return Map of task flow input parameters
     */
    public Map<String, TaskFlowInputParameter> getInputParameters(TaskFlowId taskFlowId) {
        TaskFlowDefinition taskFlowDefinition = getTaskFlowDefinition(taskFlowId);
        Map<String, TaskFlowInputParameter> taskFlowInputParameters = taskFlowDefinition.getInputParameters();

        return taskFlowInputParameters;
    }

    /**
     * Gets the task flow return parameters of the current task flow
     * @return map of the return parameters of the current tas kflow
     */
    public Map<String, NamedParameter> getCurrentTaskFlowReturnParameters() {
        return getReturnParameters(getCurrentTaskFlowId());
    }

    /**
     * Gets the return parameters of the task flow identifies by hte given task flow id
     * @param taskFlowId Id of the task flow
     * @returnmap of return parameters of the task flow with the given id
     */
    public Map<String, NamedParameter> getReturnParameters(TaskFlowId taskFlowId) {
        TaskFlowDefinition taskFlowDefinition = getTaskFlowDefinition(taskFlowId);
        Map<String, NamedParameter> namedParameters = taskFlowDefinition.getReturnValues();

        return namedParameters;
    }


    /**
     * Get the current data control frame name
     * @return current data control frame name
     */
    public String getDataControlFrameName() {
        BindingContext bindingContext = oracle.adf.controller.binding.BindingUtils.getBindingContext();
        String dataControlFrameName = bindingContext.getCurrentDataControlFrame();

        return dataControlFrameName;
    }

    /**
     * Get the current data control frame
     * @return current data control frame
     */
    public DataControlFrame getDataControlFrame() {
        BindingContext bindingContext = oracle.adf.controller.binding.BindingUtils.getBindingContext();
        String dataControlFrameName = bindingContext.getCurrentDataControlFrame();
        DataControlFrame dataControlFrame = bindingContext.findDataControlFrame(dataControlFrameName);

        return dataControlFrame;
    }

    /**
     * commit the current taskflow transaction
     */
    public void commitTaskFlow() {
        getDataControlFrame().commit();
    }

    /**
     * rollback the current taskflow transaction
     */
    public void rollbackTaskFlow() {
        getDataControlFrame().rollback();
    }

    /**
     * Check if hte current task flow transaction is dirty
     * @return true if the transaction is dirty, false otherwise
     */
    public boolean isTaskFlowTransactionDirty() {
        return getDataControlFrame().isTransactionDirty();
    }

    /**
     * Gets the name of the current open transaction
     * @return name of the current open transaction
     */
    public String getOpenTransactionName() {
        return getDataControlFrame().getOpenTransactionName();
    }

    /**
     * Gets the name of hte current data control names
     * as a comma separated list
     * @return String of hte datacontrol names as comma sepatrated list
     */
    public String getDataControlNames() {
        BindingContext bindingContext = oracle.adf.controller.binding.BindingUtils.getBindingContext();
        String dataControlFrameName = bindingContext.getCurrentDataControlFrame();
        DataControlFrame dataControlFrame = bindingContext.findDataControlFrame(dataControlFrameName);
        Collection<DCDataControl> dataControls = dataControlFrame.datacontrols();
        String names = "";
        for (DCDataControl dc : dataControls) {
            names = names + "," + dc.getName() + dc.hashCode();
        }

        return names;
    }
}
