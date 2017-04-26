/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import src.entities.ChallengesUsers;
import src.entities.Users;

/**
 *
 * @author Bryan
 */
@Stateless
public class ChallengesUsersFacade extends AbstractFacade<ChallengesUsers> {

    @PersistenceContext(unitName = "IDDYPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChallengesUsersFacade() {
        super(ChallengesUsers.class);
    }
    public List<ChallengesUsers> getChallengesByState(int state, Users user){
        Query q = em.createNamedQuery("ChallengesUsers.findByStateAndUser");
        q.setParameter("state", state);
        q.setParameter("user", user);
        
        
        return (List<ChallengesUsers>)q.getResultList();
    }
    
}
