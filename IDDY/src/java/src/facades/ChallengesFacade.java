/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import src.entities.Challenges;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Bryan
 */
@Stateless
public class ChallengesFacade extends AbstractFacade<Challenges> {

    @PersistenceContext(unitName = "IDDYPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChallengesFacade() {
        super(Challenges.class);
    }
    
    public Challenges getRandomBySystem(){
        Query q = em.createNamedQuery("Challenges.findByUserSystem");
        
        List<Challenges> challenges = (List<Challenges>) q.getResultList();
        int size = challenges.size();
        int random = ThreadLocalRandom.current().nextInt(0, size);
        Challenges c = challenges.get(random);
        return c;
    }
}
