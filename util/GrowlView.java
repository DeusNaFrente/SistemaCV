/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Iris
 */
@ManagedBean(name = "Msg")

public class GrowlView implements Serializable{

  
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Procedimento executado com Sucesso", "");
    }
    
    
    public void showError() {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Houve uma falha ao tentar usar este procedimento!!");
    }

    //public void showSticky() {
    //  FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", "Message Content"));
    //}
    // public void showMultiple() {
    //    addMessage(FacesMessage.SEVERITY_INFO, "Message 1", "Message Content");
    //   addMessage(FacesMessage.SEVERITY_INFO, "Message 2", "Message Content");
    //  addMessage(FacesMessage.SEVERITY_INFO, "Message 3", "Message Content");
    //}
}
