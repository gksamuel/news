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
import com.gachanja.hivisasanews.facade.AuthorsFacade;
import com.gachanja.hivisasanews.models.Authors;
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
public class AuthorsController {

    public AuthorsController() {
        pagingInfo = new PagingInfo();
        converter = new AuthorsConverter();
    }
    private Authors authors = null;
    private List<Authors> authorsItems = null;
    private AuthorsFacade jpaController = null;
    private AuthorsConverter converter = null;
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

    public AuthorsFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (AuthorsFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "authorsJpa");
        }
        return jpaController;
    }

    public SelectItem[] getAuthorsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getAuthorsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Authors getAuthors() {
        if (authors == null) {
            authors = (Authors) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAuthors", converter, null);
        }
        if (authors == null) {
            authors = new Authors();
        }
        return authors;
    }

    public String listSetup() {
        reset(true);
        return "authors_list";
    }

    public String createSetup() {
        reset(false);
        authors = new Authors();
        return "authors_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(authors);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Authors was successfully created.");
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
        return scalarSetup("authors_detail");
    }

    public String editSetup() {
        return scalarSetup("authors_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        authors = (Authors) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAuthors", converter, null);
        if (authors == null) {
            String requestAuthorsString = JsfUtil.getRequestParameter("jsfcrud.currentAuthors");
            JsfUtil.addErrorMessage("The authors with id " + requestAuthorsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String authorsString = converter.getAsString(FacesContext.getCurrentInstance(), null, authors);
        String currentAuthorsString = JsfUtil.getRequestParameter("jsfcrud.currentAuthors");
        if (authorsString == null || authorsString.length() == 0 || !authorsString.equals(currentAuthorsString)) {
            String outcome = editSetup();
            if ("authors_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit authors. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(authors);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Authors was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAuthors");
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
                JsfUtil.addSuccessMessage("Authors was successfully deleted.");
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

    public List<Authors> getAuthorsItems() {
        if (authorsItems == null) {
            getPagingInfo();
            authorsItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return authorsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "authors_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "authors_list";
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
        authors = null;
        authorsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Authors newAuthors = new Authors();
        String newAuthorsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAuthors);
        String authorsString = converter.getAsString(FacesContext.getCurrentInstance(), null, authors);
        if (!newAuthorsString.equals(authorsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
