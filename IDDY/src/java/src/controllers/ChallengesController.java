package src.controllers;

import src.entities.Challenges;
import src.controllers.util.JsfUtil;
import src.controllers.util.PaginationHelper;
import src.facades.ChallengesFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import src.entities.Categories;
import src.entities.ChallengesUsers;
import src.entities.Difficulties;
import src.entities.Users;

@Named("challengesController")
@SessionScoped
public class ChallengesController implements Serializable {

    private Challenges current;
    private DataModel items = null;
    @EJB
    private src.facades.ChallengesFacade ejbFacade;
    @EJB
    private src.facades.CategoriesFacade catFacade;
    @EJB
    private src.facades.DifficultiesFacade difFacade;
    @EJB
    private src.facades.UsersFacade usrFacade;
    @EJB
    private src.facades.ChallengesUsersFacade culFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    
    private String nc_UsrName;
    private String nc_CatName;
    private String nc_DifName;
    private String nc_Title;
    private String nc_Desc;
    
    
    public void nc_Submit(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Users giver = usrFacade.findUserByLoginName(ctx.getExternalContext().getRemoteUser());
        Users getter = usrFacade.findUserByLoginName(nc_UsrName);
        Challenges newChal = new Challenges();
        newChal.setTitle(nc_Title);
        newChal.setDescription(nc_Desc);
        newChal.setFkUser(giver);
        newChal.setFkDiff(difFacade.getByTitle(nc_DifName));
        newChal.setFkCat(catFacade.getByCatName(nc_CatName));
        
        ejbFacade.create(newChal);
        
        ChallengesUsers cul = new ChallengesUsers();
        cul.setFkChall(newChal);
        cul.setFkGetter(getter);
        cul.setFkGiver(giver);
        cul.setState(0);
        cul.setDatetimeCreate(new Date());
        culFacade.create(cul);
        
    }

    public String getNc_UsrName() {
        return nc_UsrName;
    }

    public void setNc_UsrName(String nc_UsrName) {
        this.nc_UsrName = nc_UsrName;
    }

    public String getNc_CatName() {
        return nc_CatName;
    }

    public void setNc_CatName(String nc_CatName) {
        this.nc_CatName = nc_CatName;
    }

    public String getNc_DifName() {
        return nc_DifName;
    }

    public void setNc_DifName(String nc_DifName) {
        this.nc_DifName = nc_DifName;
    }

    public String getNc_Title() {
        return nc_Title;
    }

    public void setNc_Title(String nc_Title) {
        this.nc_Title = nc_Title;
    }

    public String getNc_Desc() {
        return nc_Desc;
    }

    public void setNc_Desc(String nc_Desc) {
        this.nc_Desc = nc_Desc;
    }
    
    

    public ChallengesController() {
    }

    public Challenges getSelected() {
        if (current == null) {
            current = new Challenges();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ChallengesFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Challenges) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Challenges();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ChallengesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Challenges) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ChallengesUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Challenges) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ChallengesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Challenges getChallenges(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public List<String> getAllCategoriesStr(){
        ArrayList<String> strs = new ArrayList<>();
        for(Categories c : catFacade.findAll()){
            strs.add(c.getTitle());
        }
        return strs;
    }
    
    public List<String> getAllDifficultiesStr(){
        ArrayList<String> strs = new ArrayList<>();
        for(Difficulties c : difFacade.findAll()){
            strs.add(c.getTitle());
        }
        return strs;
    }

    @FacesConverter(forClass = Challenges.class)
    public static class ChallengesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChallengesController controller = (ChallengesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "challengesController");
            return controller.getChallenges(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Challenges) {
                Challenges o = (Challenges) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Challenges.class.getName());
            }
        }

    }

}
