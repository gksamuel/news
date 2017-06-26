/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import com.gachanja.hivisasanews.controller.util.JsfUtil;
import com.gachanja.hivisasanews.controller.util.PagingInfo;
import com.gachanja.hivisasanews.facade.StatusFacade;
import com.gachanja.hivisasanews.models.Status;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author gachanja
 */
public class StatusController {

    public StatusController() {
        pagingInfo = new PagingInfo();
        converter = new StatusConverter();
    }
    private Status status = null;
    private List<Status> statusItems = null;
    private StatusFacade jpaController = null;
    private StatusConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "com.gachanja_hivisasanews_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public StatusFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (StatusFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "statusJpa");
        }
        return jpaController;
    }

    public SelectItem[] getStatusItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getStatusItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Status getStatus() {
        if (status == null) {
            status = (Status) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentStatus", converter, null);
        }
        if (status == null) {
            status = new Status();
        }
        return status;
    }

    public String listSetup() {
        reset(true);
        return "status_list";
    }

    public String createSetup() {
        reset(false);
        status = new Status();
        return "status_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(status);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Status was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("status_detail");
    }

    public String editSetup() {
        return scalarSetup("status_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        status = (Status) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentStatus", converter, null);
        if (status == null) {
            String requestStatusString = JsfUtil.getRequestParameter("jsfcrud.currentStatus");
            JsfUtil.addErrorMessage("The status with id " + requestStatusString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String statusString = converter.getAsString(FacesContext.getCurrentInstance(), null, status);
        String currentStatusString = JsfUtil.getRequestParameter("jsfcrud.currentStatus");
        if (statusString == null || statusString.length() == 0 || !statusString.equals(currentStatusString)) {
            String outcome = editSetup();
            if ("status_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit status. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(status);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Status was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentStatus");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Status was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();

        return listSetup();
    }

    public List<Status> getStatusItems() {
        if (statusItems == null) {
            getPagingInfo();
            statusItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return statusItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "status_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "status_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        status = null;
        statusItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Status newStatus = new Status();
        String newStatusString = converter.getAsString(FacesContext.getCurrentInstance(), null, newStatus);
        String statusString = converter.getAsString(FacesContext.getCurrentInstance(), null, status);
        if (!newStatusString.equals(statusString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
