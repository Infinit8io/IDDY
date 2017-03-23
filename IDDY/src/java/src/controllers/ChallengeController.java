/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import src.entities.Categories;
import src.entities.Difficulties;
import src.facades.CategoriesFacade;
import src.facades.DifficultiesFacade;

/**
 *
 * @author Bryan
 */
@Named(value = "challengeController")
@Dependent
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
    
}
