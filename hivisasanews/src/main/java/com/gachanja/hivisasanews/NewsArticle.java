  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews;

import java.io.Serializable;

/**
 *
 * @author gachanja
 */
public class NewsArticle implements Serializable {
     
    private String title;
    private String content;
    private int index;
 
    public NewsArticle() {
         
    }
     
    public NewsArticle(int index, String title, String content) {
        this.index = index;
        this.content = content;
        this.title = title;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
     
    public int getIndex() {
        return index;
    }
 
    public void setIndex(int index) {
        this.index = index;
    }
}
