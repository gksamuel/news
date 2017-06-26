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
import com.gachanja.hivisasanews.facade.PageviewsFacade;
import com.gachanja.hivisasanews.models.Pageviews;
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
public class PageviewsController {

    public PageviewsController() {
        pagingInfo = new PagingInfo();
        converter = new PageviewsConverter();
    }
    private Pageviews pageviews = null;
    private List<Pageviews> pageviewsItems = null;
    private PageviewsFacade jpaController = null;
    private PageviewsConverter converter = null;
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

    public PageviewsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PageviewsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "pageviewsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPageviewsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPageviewsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Pageviews getPageviews() {
        if (pageviews == null) {
            pageviews = (Pageviews) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPageviews", converter, null);
        }
        if (pageviews == null) {
            pageviews = new Pageviews();
        }
        return pageviews;
    }

    public String listSetup() {
        reset(true);
        return "pageviews_list";
    }

    public String createSetup() {
        reset(false);
        pageviews = new Pageviews();
        return "pageviews_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(pageviews);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pageviews was successfully created.");
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
        return scalarSetup("pageviews_detail");
    }

    public String editSetup() {
        return scalarSetup("pageviews_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        pageviews = (Pageviews) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPageviews", converter, null);
        if (pageviews == null) {
            String requestPageviewsString = JsfUtil.getRequestParameter("jsfcrud.currentPageviews");
            JsfUtil.addErrorMessage("The pageviews with id " + requestPageviewsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String pageviewsString = converter.getAsString(FacesContext.getCurrentInstance(), null, pageviews);
        String currentPageviewsString = JsfUtil.getRequestParameter("jsfcrud.currentPageviews");
        if (pageviewsString == null || pageviewsString.length() == 0 || !pageviewsString.equals(currentPageviewsString)) {
            String outcome = editSetup();
            if ("pageviews_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit pageviews. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(pageviews);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Pageviews was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPageviews");
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
                JsfUtil.addSuccessMessage("Pageviews was successfully deleted.");
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

    public List<Pageviews> getPageviewsItems() {
        if (pageviewsItems == null) {
            getPagingInfo();
            pageviewsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return pageviewsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "pageviews_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "pageviews_list";
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
        pageviews = null;
        pageviewsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Pageviews newPageviews = new Pageviews();
        String newPageviewsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPageviews);
        String pageviewsString = converter.getAsString(FacesContext.getCurrentInstance(), null, pageviews);
        if (!newPageviewsString.equals(pageviewsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
