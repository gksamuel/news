/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews.facade;

import com.gachanja.hivisasanews.models.Pageviews;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gachanja
 */
@Stateless
public class PageviewsFacade extends AbstractFacade<Pageviews> {
    @PersistenceContext(unitName = "com.gachanja_hivisasanews_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PageviewsFacade() {
        super(Pageviews.class);
    }
    
}
