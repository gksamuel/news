/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews;

import com.gachanja.hivisasanews.models.Articles;
import com.gachanja.hivisasanews.models.Categories;
import com.gachanja.hivisasanews.models.Pageviews;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author gachanja
 */
@ManagedBean
@SessionScoped
public class News implements Serializable {

    private List<NewsCategory> groups;
    private NewsArticle selectedEntry;
    private NewsCategory selectedGroup;
    private Map<Integer, Categories> feeds;

    @PersistenceContext(unitName = "com.gachanja_hivisasanews_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        List<Categories> resultList = (List<Categories>) em.createNamedQuery("Categories.findAll").getResultList();
        Iterator i = resultList.iterator();

        feeds = new HashMap<Integer, Categories>();
        while (i.hasNext()) {
            Categories category = (Categories) i.next();
            feeds.put(category.getCategoryid(), category);
        }
        groups = fetchNews();
    }

    public List<NewsCategory> getGroups() {
        return groups;
    }

    public NewsArticle getSelectedEntry() {
        if (!(selectedEntry == null)) {
            try {
                UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                transaction.begin();
                
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "updated entry {0} ", new Object[]{selectedEntry.getIndex()});
                Articles article = (Articles) em.createNamedQuery("Articles.findByArticleid").setParameter("articleid", selectedEntry.getIndex()).getSingleResult();
                Pageviews pageView = new Pageviews();
                pageView.setArticleid(article);
                pageView.setDatecreated(new Date());
                em.persist(pageView);
                transaction.commit();
            } catch (NamingException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotSupportedException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicMixedException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (HeuristicRollbackException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return selectedEntry;
    }

    public void setSelectedEntry(NewsArticle selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    public NewsCategory getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(NewsCategory selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public List<NewsCategory> fetchNews() {
        List<NewsCategory> news = new ArrayList<NewsCategory>();
        try {
            for (Integer key : feeds.keySet()) {

                Categories newsCategory = feeds.get(key);
                List<Articles> articles = (List<Articles>) em.createNamedQuery("Articles.findByCategoryid").setParameter("categoryid", newsCategory).getResultList();
                List<NewsArticle> entries = new ArrayList<NewsArticle>();

                Iterator<Articles> iterator = articles.iterator();
                while (iterator.hasNext()) {
                    Articles article = iterator.next();
                    entries.add(new NewsArticle(article.getArticleid(), article.getTitle() + "...", article.getSummary()));
                }

                news.add(new NewsCategory(newsCategory.getCategory(), entries));
            }

        } catch (IllegalArgumentException exception) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please try again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return news;
    }

}
