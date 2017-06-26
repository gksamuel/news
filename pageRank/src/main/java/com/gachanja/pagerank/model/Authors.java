/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.pagerank.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author gachanja
 */
@Entity
@Table(name = "authors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authors.findAll", query = "SELECT a FROM Authors a"),
    @NamedQuery(name = "Authors.findByAuthorid", query = "SELECT a FROM Authors a WHERE a.authorid = :authorid"),
    @NamedQuery(name = "Authors.findByUsername", query = "SELECT a FROM Authors a WHERE a.username = :username"),
    @NamedQuery(name = "Authors.findByPassword", query = "SELECT a FROM Authors a WHERE a.password = :password"),
    @NamedQuery(name = "Authors.findByFirstname", query = "SELECT a FROM Authors a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "Authors.findByLastname", query = "SELECT a FROM Authors a WHERE a.lastname = :lastname"),
    @NamedQuery(name = "Authors.findByMobilenumber", query = "SELECT a FROM Authors a WHERE a.mobilenumber = :mobilenumber"),
    @NamedQuery(name = "Authors.findByDatecreated", query = "SELECT a FROM Authors a WHERE a.datecreated = :datecreated"),
    @NamedQuery(name = "Authors.findByDatemodified", query = "SELECT a FROM Authors a WHERE a.datemodified = :datemodified")})
public class Authors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "authorid")
    private Integer authorid;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "mobilenumber")
    private long mobilenumber;
    @Basic(optional = false)
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "datemodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemodified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorid")
    private Collection<Articles> articlesCollection;
    @JoinColumn(name = "statusid", referencedColumnName = "statusid")
    @ManyToOne(optional = false)
    private Status statusid;

    public Authors() {
    }

    public Authors(Integer authorid) {
        this.authorid = authorid;
    }

    public Authors(Integer authorid, String username, String password, String firstname, String lastname, long mobilenumber, Date datecreated, Date datemodified) {
        this.authorid = authorid;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobilenumber = mobilenumber;
        this.datecreated = datecreated;
        this.datemodified = datemodified;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(long mobilenumber) {
        this.mobilenumber = mobilenumber;
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

    @XmlTransient
    public Collection<Articles> getArticlesCollection() {
        return articlesCollection;
    }

    public void setArticlesCollection(Collection<Articles> articlesCollection) {
        this.articlesCollection = articlesCollection;
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
        hash += (authorid != null ? authorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authors)) {
            return false;
        }
        Authors other = (Authors) object;
        if ((this.authorid == null && other.authorid != null) || (this.authorid != null && !this.authorid.equals(other.authorid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gachanja.pagerank.model.Authors[ authorid=" + authorid + " ]";
    }
    
}
