package com.betteru.jsfclassespackage;

import com.betteru.sourcepackage.UsersProgress;
import com.betteru.jsfclassespackage.util.JsfUtil;
import com.betteru.jsfclassespackage.util.JsfUtil.PersistAction;
import com.betteru.sessionbeanpackage.UsersProgressFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usersProgressController")
@SessionScoped
public class UsersProgressController implements Serializable {

    @EJB
    private com.betteru.sessionbeanpackage.UsersProgressFacade ejbFacade;
    private List<UsersProgress> items = null;
    private UsersProgress selected;

    public UsersProgressController() {
    }

    public UsersProgress getSelected() {
        return selected;
    }

    public void setSelected(UsersProgress selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setUsersProgressPK(new com.betteru.sourcepackage.UsersProgressPK());
    }

    private UsersProgressFacade getFacade() {
        return ejbFacade;
    }

    public UsersProgress prepareCreate() {
        selected = new UsersProgress();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsersProgressCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsersProgressUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsersProgressDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsersProgress> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UsersProgress getUsersProgress(com.betteru.sourcepackage.UsersProgressPK id) {
        return getFacade().find(id);
    }

    public List<UsersProgress> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsersProgress> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsersProgress.class)
    public static class UsersProgressControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsersProgressController controller = (UsersProgressController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usersProgressController");
            return controller.getUsersProgress(getKey(value));
        }

        com.betteru.sourcepackage.UsersProgressPK getKey(String value) {
            com.betteru.sourcepackage.UsersProgressPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.betteru.sourcepackage.UsersProgressPK();
            key.setUid(Integer.parseInt(values[0]));
            key.setPid(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.betteru.sourcepackage.UsersProgressPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUid());
            sb.append(SEPARATOR);
            sb.append(value.getPid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsersProgress) {
                UsersProgress o = (UsersProgress) object;
                return getStringKey(o.getUsersProgressPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsersProgress.class.getName()});
                return null;
            }
        }

    }

}
