/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import src.entities.Difficulties;

/**
 *
 * @author Bryan
 */
@Stateless
public class DifficultiesFacade extends AbstractFacade<Difficulties> {

    @PersistenceContext(unitName = "IDDYPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DifficultiesFacade() {
        super(Difficulties.class);
    }
    
    public Difficulties getByTitle(String title){
        Query qd = em.createNamedQuery("Difficulties.findByTitle").setParameter("title", title);
        return (Difficulties)qd.getSingleResult();
    }
    
}
