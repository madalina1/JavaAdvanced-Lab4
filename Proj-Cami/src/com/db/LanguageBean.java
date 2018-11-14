package com.db;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name="language")
@SessionScoped
public class LanguageBean{
    private String localeCode;
    private Locale locale;

    private static Map<String, Object> countries;

    static {
            countries = new HashMap<>();
            countries.put("English", Locale.ENGLISH);
            countries.put("Romanian", Locale.GERMAN);
        }

    @PostConstruct
    public void init(){
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public String getLanguage(){
        System.out.println(locale.getLanguage());
        return locale.getLanguage();
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e){
        String newLocaleValue = e.getNewValue().toString();

        countries.entrySet()
                .stream()
                .filter(entry -> entry.getValue().toString().equals(newLocaleValue))
                .forEach(entry -> {
                    this.locale = (Locale) entry.getValue();
                    FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
                });
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
}
