/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.newsReader.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gachanja
 */
@Entity
@Table(name = "articles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articles.findAll", query = "SELECT a FROM Articles a"),
    @NamedQuery(name = "Articles.findByArticleid", query = "SELECT a FROM Articles a WHERE a.articleid = :articleid"),
    @NamedQuery(name = "Articles.findByTitle", query = "SELECT a FROM Articles a WHERE a.title = :title"),
    @NamedQuery(name = "Articles.findBySummary", query = "SELECT a FROM Articles a WHERE a.summary = :summary"),
    @NamedQuery(name = "Articles.findByArticle", query = "SELECT a FROM Articles a WHERE a.article = :article"),
    @NamedQuery(name = "Articles.findByImage1", query = "SELECT a FROM Articles a WHERE a.image1 = :image1"),
    @NamedQuery(name = "Articles.findByImage2", query = "SELECT a FROM Articles a WHERE a.image2 = :image2"),
    @NamedQuery(name = "Articles.findBySticky", query = "SELECT a FROM Articles a WHERE a.sticky = :sticky"),
    @NamedQuery(name = "Articles.findByDatecreated", query = "SELECT a FROM Articles a WHERE a.datecreated = :datecreated"),
    @NamedQuery(name = "Articles.findByDatemodified", query = "SELECT a FROM Articles a WHERE a.datemodified = :datemodified")})
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "articleid")
    private Integer articleid;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "summary")
    private String summary;
    @Column(name = "article")
    private String article;
    @Column(name = "image1")
    private String image1;
    @Column(name = "image2")
    private String image2;
    @Column(name = "sticky")
    private Integer sticky;
    @Basic(optional = false)
    @Column(name = "pagerank")
    private Integer pagerank;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "datemodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemodified;
    @JoinColumn(name = "authorid", referencedColumnName = "authorid")
    @ManyToOne(optional = false)
    private Authors authorid;
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
    @ManyToOne(optional = false)
    private Categories categoryid;
    @JoinColumn(name = "statusid", referencedColumnName = "statusid")
    @ManyToOne(optional = false)
    private Status statusid;

    public Articles() {
    }

    public Articles(Integer articleid) {
        this.articleid = articleid;
    }

    public Articles(Integer articleid, String title, String summary, Date datecreated, Date datemodified) {
        this.articleid = articleid;
        this.title = title;
        this.summary = summary;
        this.datecreated = datecreated;
        this.datemodified = datemodified;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public Integer getSticky() {
        return sticky;
    }

    public void setSticky(Integer sticky) {
        this.sticky = sticky;
    }

    public Integer getPagerank() {
        return pagerank;
    }

    public void setPagerank(Integer pagerank) {
        this.pagerank = pagerank;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getDatemodified() {
        return datemodified;
    }

    public void setDatemodified(Date datemodified) {
        this.datemodified = datemodified;
    }

    public Authors getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Authors authorid) {
        this.authorid = authorid;
    }

    public Categories getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Categories categoryid) {
        this.categoryid = categoryid;
    }

    public Status getStatusid() {
        return statusid;
    }

    public void setStatusid(Status statusid) {
        this.statusid = statusid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleid != null ? articleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articles)) {
            return false;
        }
        Articles other = (Articles) object;
        if ((this.articleid == null && other.articleid != null) || (this.articleid != null && !this.articleid.equals(other.articleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gachanja.mavenproject3.models.Articles[ articleid=" + articleid + " ]";
    }

}
