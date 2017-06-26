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
import com.gachanja.hivisasanews.facade.ConfigsFacade;
import com.gachanja.hivisasanews.models.Configs;
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
public class ConfigsController {

    public ConfigsController() {
        pagingInfo = new PagingInfo();
        converter = new ConfigsConverter();
    }
    private Configs configs = null;
    private List<Configs> configsItems = null;
    private ConfigsFacade jpaController = null;
    private ConfigsConverter converter = null;
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

    public ConfigsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (ConfigsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "configsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getConfigsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getConfigsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Configs getConfigs() {
        if (configs == null) {
            configs = (Configs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentConfigs", converter, null);
        }
        if (configs == null) {
            configs = new Configs();
        }
        return configs;
    }

    public String listSetup() {
        reset(true);
        return "configs_list";
    }

    public String createSetup() {
        reset(false);
        configs = new Configs();
        return "configs_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(configs);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Configs was successfully created.");
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
        return scalarSetup("configs_detail");
    }

    public String editSetup() {
        return scalarSetup("configs_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        configs = (Configs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentConfigs", converter, null);
        if (configs == null) {
            String requestConfigsString = JsfUtil.getRequestParameter("jsfcrud.currentConfigs");
            JsfUtil.addErrorMessage("The configs with id " + requestConfigsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String configsString = converter.getAsString(FacesContext.getCurrentInstance(), null, configs);
        String currentConfigsString = JsfUtil.getRequestParameter("jsfcrud.currentConfigs");
        if (configsString == null || configsString.length() == 0 || !configsString.equals(currentConfigsString)) {
            String outcome = editSetup();
            if ("configs_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit configs. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(configs);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Configs was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentConfigs");
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
                JsfUtil.addSuccessMessage("Configs was successfully deleted.");
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

    public List<Configs> getConfigsItems() {
        if (configsItems == null) {
            getPagingInfo();
            configsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return configsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "configs_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "configs_list";
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
        configs = null;
        configsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Configs newConfigs = new Configs();
        String newConfigsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newConfigs);
        String configsString = converter.getAsString(FacesContext.getCurrentInstance(), null, configs);
        if (!newConfigsString.equals(configsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
