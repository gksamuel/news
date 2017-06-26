/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.pagerank.model;

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
@Table(name = "pageviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pageviews.findAll", query = "SELECT p FROM Pageviews p"),
    @NamedQuery(name = "Pageviews.findByPageviewid", query = "SELECT p FROM Pageviews p WHERE p.pageviewid = :pageviewid"),
    @NamedQuery(name = "Pageviews.findByIpaddress", query = "SELECT p FROM Pageviews p WHERE p.ipaddress = :ipaddress"),
    @NamedQuery(name = "Pageviews.findByDatecreated", query = "SELECT p FROM Pageviews p WHERE p.datecreated = :datecreated")})
public class Pageviews implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pageviewid")
    private Integer pageviewid;
    @Column(name = "ipaddress")
    private String ipaddress;
    @Basic(optional = false)
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @JoinColumn(name = "articleid", referencedColumnName = "articleid")
    @ManyToOne(optional = false)
    private Articles articleid;

    public Pageviews() {
    }

    public Pageviews(Integer pageviewid) {
        this.pageviewid = pageviewid;
    }

    public Pageviews(Integer pageviewid, Date datecreated) {
        this.pageviewid = pageviewid;
        this.datecreated = datecreated;
    }

    public Integer getPageviewid() {
        return pageviewid;
    }

    public void setPageviewid(Integer pageviewid) {
        this.pageviewid = pageviewid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Articles getArticleid() {
        return articleid;
    }

    public void setArticleid(Articles articleid) {
        this.articleid = articleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pageviewid != null ? pageviewid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pageviews)) {
            return false;
        }
        Pageviews other = (Pageviews) object;
        if ((this.pageviewid == null && other.pageviewid != null) || (this.pageviewid != null && !this.pageviewid.equals(other.pageviewid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gachanja.pagerank.model.Pageviews[ pageviewid=" + pageviewid + " ]";
    }
    
}
