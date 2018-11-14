package com.models;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userData", eager = true)
@SessionScoped
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    public UrlData data;

    public UrlData getData() {
        return data;
    }

    public void setData(UrlData data) {
        this.data = data;
    }
}
