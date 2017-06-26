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
@Table(name = "configs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configs.findAll", query = "SELECT c FROM Configs c"),
    @NamedQuery(name = "Configs.findByConfigid", query = "SELECT c FROM Configs c WHERE c.configid = :configid"),
    @NamedQuery(name = "Configs.findByName", query = "SELECT c FROM Configs c WHERE c.name = :name"),
    @NamedQuery(name = "Configs.findByValue", query = "SELECT c FROM Configs c WHERE c.value = :value"),
    @NamedQuery(name = "Configs.findByDescription", query = "SELECT c FROM Configs c WHERE c.description = :description"),
    @NamedQuery(name = "Configs.findByDatecreated", query = "SELECT c FROM Configs c WHERE c.datecreated = :datecreated"),
    @NamedQuery(name = "Configs.findByDatemodified", query = "SELECT c FROM Configs c WHERE c.datemodified = :datemodified")})
public class Configs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "configid")
    private Integer configid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "datemodified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemodified;

    public Configs() {
    }

    public Configs(Integer configid) {
        this.configid = configid;
    }

    public Configs(Integer configid, String name, String value, Date datecreated, Date datemodified) {
        this.configid = configid;
        this.name = name;
        this.value = value;
        this.datecreated = datecreated;
        this.datemodified = datemodified;
    }

    public Integer getConfigid() {
        return configid;
    }

    public void setConfigid(Integer configid) {
        this.configid = configid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configid != null ? configid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configs)) {
            return false;
        }
        Configs other = (Configs) object;
        if ((this.configid == null && other.configid != null) || (this.configid != null && !this.configid.equals(other.configid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gachanja.pagerank.model.Configs[ configid=" + configid + " ]";
    }
    
}
