package com.db;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
@ManagedBean
public class EmailValidator implements Validator{

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String email = (String) o;
        if(email.indexOf('@') == -1) {
            ((UIInput)uiComponent).setValid(false);
            facesContext.addMessage(uiComponent.getClientId(facesContext), new FacesMessage("Invalid Email!"));
        }
    }
}
