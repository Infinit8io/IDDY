/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import src.entities.Friendship;
import src.entities.Users;

/**
 *
 * @author Bryan
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "IDDYPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    
    public List<Users> getAllUsers(){
        Query allUsers = em.createNamedQuery("Users.findAll");
        
        return (List<Users>)allUsers.getResultList();
    }
    
    public List<Users> findByName(String search){
        
        Query usersSearched = em.createNamedQuery("Users.findByName");
        usersSearched.setParameter("search", search);
        
        
        return (List<Users>)usersSearched.getResultList();
    }
    
    
    
    public Users findUserByLoginName(String loginName) {
        Query users = em.createNamedQuery("Users.findByLoginName").setParameter("loginName", loginName);
        
        return (Users) users.getSingleResult();
    }
    
}
