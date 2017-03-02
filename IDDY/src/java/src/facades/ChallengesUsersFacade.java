/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import src.entities.ChallengesUsers;

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
    
}
