package controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public abstract class BaseBean implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	protected void redirect(String path) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
		    String contextPath = origRequest.getContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + path);
		}
		catch(Exception e) {}
	}
	
	protected void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
            "Aviso",  summary);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
}
