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
import com.gachanja.hivisasanews.facade.ArticlesFacade;
import com.gachanja.hivisasanews.models.Articles;
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
public class ArticlesController {

    public ArticlesController() {
        pagingInfo = new PagingInfo();
        converter = new ArticlesConverter();
    }
    private Articles articles = null;
    private List<Articles> articlesItems = null;
    private ArticlesFacade jpaController = null;
    private ArticlesConverter converter = null;
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

    public ArticlesFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (ArticlesFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "articlesJpa");
        }
        return jpaController;
    }

    public SelectItem[] getArticlesItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getArticlesItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Articles getArticles() {
        if (articles == null) {
            articles = (Articles) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentArticles", converter, null);
        }
        if (articles == null) {
            articles = new Articles();
        }
        return articles;
    }

    public String listSetup() {
        reset(true);
        return "articles_list";
    }

    public String createSetup() {
        reset(false);
        articles = new Articles();
        return "articles_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(articles);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Articles was successfully created.");
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
        return scalarSetup("articles_detail");
    }

    public String editSetup() {
        return scalarSetup("articles_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        articles = (Articles) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentArticles", converter, null);
        if (articles == null) {
            String requestArticlesString = JsfUtil.getRequestParameter("jsfcrud.currentArticles");
            JsfUtil.addErrorMessage("The articles with id " + requestArticlesString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String articlesString = converter.getAsString(FacesContext.getCurrentInstance(), null, articles);
        String currentArticlesString = JsfUtil.getRequestParameter("jsfcrud.currentArticles");
        if (articlesString == null || articlesString.length() == 0 || !articlesString.equals(currentArticlesString)) {
            String outcome = editSetup();
            if ("articles_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit articles. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(articles);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Articles was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentArticles");
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
                JsfUtil.addSuccessMessage("Articles was successfully deleted.");
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

    public List<Articles> getArticlesItems() {
        if (articlesItems == null) {
            getPagingInfo();
            articlesItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return articlesItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "articles_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "articles_list";
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
        articles = null;
        articlesItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Articles newArticles = new Articles();
        String newArticlesString = converter.getAsString(FacesContext.getCurrentInstance(), null, newArticles);
        String articlesString = converter.getAsString(FacesContext.getCurrentInstance(), null, articles);
        if (!newArticlesString.equals(articlesString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
