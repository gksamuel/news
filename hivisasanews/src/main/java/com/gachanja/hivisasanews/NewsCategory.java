/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gachanja
 */
public class NewsCategory implements Serializable {
 
    private String title;
     
    private List<NewsArticle> entries;
     
    public NewsCategory() {
         
    }
 
    public NewsCategory(String title, List<NewsArticle> entries) {
        this.title = title;
        this.entries = entries;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public List<NewsArticle> getEntries() {
        return entries;
    }
 
    public void setEntries(List<NewsArticle> entries) {
        this.entries = entries;
    }
}
