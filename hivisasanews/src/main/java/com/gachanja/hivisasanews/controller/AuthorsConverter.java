/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gachanja.hivisasanews.controller;

import com.gachanja.hivisasanews.models.Authors;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author gachanja
 */
public class AuthorsConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        AuthorsController controller = (AuthorsController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "authors");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Authors) {
            Authors o = (Authors) object;
            return o.getAuthorid() == null ? "" : o.getAuthorid().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.gachanja.hivisasanews.models.Authors");
        }
    }
    
}
