package com.oumous.skyface.bean;

import java.util.Locale;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LocaleBean {

    private Locale locale = Locale.FRENCH;

    public Locale getLocale() {
        return locale;
    }

    public boolean isRtl() {
        return locale.getLanguage().equals("ar");
    }

    public String getDir() {
        return isRtl() ? "rtl" : "ltr";
    }

    public void switchToFrench() {
        setLocale(Locale.FRENCH);
    }

    public void switchToArabic() {
        setLocale(new Locale("ar"));
    }

    public void switchToEnglish() {
        setLocale(Locale.ENGLISH);
    }

    private void setLocale(Locale newLocale) {
        locale = newLocale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
    }
}
