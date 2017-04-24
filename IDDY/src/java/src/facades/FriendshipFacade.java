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
import src.entities.Friendship;
import src.entities.Users;

/**
 *
 * @author Bryan
 */
@Stateless
public class FriendshipFacade extends AbstractFacade<Friendship> {

    @PersistenceContext(unitName = "IDDYPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FriendshipFacade() {
        super(Friendship.class);
    }
    
    public List<Friendship> getFolloweds(Users follower){
        Query fs = em.createNamedQuery("Friendship.findByFollower").setParameter("other", follower);
        
        return fs.getResultList();
    }
    
    public Friendship getByBothParts(Users follower, Users followed){
        Query fs = em.createNamedQuery("Friendship.findByBothParts").setParameter("user1", follower).setParameter("user2", followed);
        return (Friendship)fs.getSingleResult();
    }
    
}
