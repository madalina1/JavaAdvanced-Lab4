package com;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name="sessionCount")
@SessionScoped
public class SessionCounterListener implements HttpSessionListener, Serializable {

    private LogHelper logHelper = new LogHelper();
    private int totalActiveSession;

    public SessionCounterListener() throws IOException {
    }

    public int getTotalActiveSession(){
        return totalActiveSession;
    }

    public void setTotalActiveSession(int totalActiveSession) {
        this.totalActiveSession = totalActiveSession;
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        totalActiveSession++;
        int a = getTotalActiveSession();
        logHelper.tryWriteLine(Integer.toString(a));
        System.out.println("sessionCreated - add one session into counter");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        totalActiveSession--;
        int a = getTotalActiveSession();
        logHelper.tryWriteLine(Integer.toString(a));
        System.out.println("sessionDestroyed - delete one session from counter");
    }

}
