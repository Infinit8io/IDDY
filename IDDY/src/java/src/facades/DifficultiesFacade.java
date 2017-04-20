/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
