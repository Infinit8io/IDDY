/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import src.entities.Categories;
import src.entities.Difficulties;
import src.facades.CategoriesFacade;
import src.facades.DifficultiesFacade;

/**
 *
 * @author Bryan
 */
@Named(value = "challengeController")
@RequestScoped
public class ChallengeController {

    @EJB
    private DifficultiesFacade difficultiesFacade;
    @EJB
    private CategoriesFacade categoriesFacade;
    
    /**
     * Creates a new instance of ChallengeController
     */
    public ChallengeController() {
    }
    
    public List<Difficulties> getAllDifficulties(){
        return difficultiesFacade.getAllDifficulties();
    }
    
    public List<Categories> getAllCategories(){
        return categoriesFacade.getAllCategories();
    }
    
    public List<String> getAllCategoriesStr(){
        ArrayList<String> tmpList = new ArrayList<>();
        for(Categories c : categoriesFacade.getAllCategories()){
            tmpList.add(c.getTitle());
        }
        return tmpList;
    }
    
    public List<String> getAllDifficultiesStr(){
        ArrayList<String> tmpList = new ArrayList<>();
        
        for(Difficulties d : difficultiesFacade.getAllDifficulties()){
            tmpList.add(d.getTitle());
        }
        
        return tmpList;
    }
    
    
     /**********************************************************
     *                                                          *
     *                 NEW CHALLENGE STUFF                      * 
     *                     ¯\_(ツ)_/¯                           *
      **********************************************************/
    
    //FIELDS
    private String nc_UsrName;
    private String nc_CatName;
    private String nc_DifName;
    private String nc_Title;
    private String nc_Desc;
    
    //METHODS
    public void nc_Submit(){
        System.out.println("GOT A NEW CHALLENGE !!!!1!!111");
        System.out.println("UZR: "+nc_UsrName);
        System.out.println("CAT: "+nc_CatName+" (meow)");
        System.out.println("DIF: "+nc_DifName);
        System.out.println("TTL: "+nc_Title);
        System.out.println("DSC: "+nc_Desc);
    }
    
    //GET N SET

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
    
    
    
    
    
    
}
