/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews.facade;

import com.gachanja.hivisasanews.models.Configs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gachanja
 */
@Stateless
public class ConfigsFacade extends AbstractFacade<Configs> {
    @PersistenceContext(unitName = "com.gachanja_hivisasanews_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfigsFacade() {
        super(Configs.class);
    }
    
}
