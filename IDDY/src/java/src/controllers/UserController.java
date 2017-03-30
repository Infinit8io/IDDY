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
import src.entities.Users;
import src.facades.UsersFacade;

/**
 *
 * @author Bryan
 */
@Named(value = "userController")
@Dependent
public class UserController {
    
    @EJB
    private UsersFacade usersFacade;

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
    public List<String> getAllUsers(){
        ArrayList<String> tmpList = new ArrayList<>();
        for(Users u : usersFacade.findAll()){
            tmpList.add(u.getPseudo());
        }
        return tmpList;
    }
    
}
