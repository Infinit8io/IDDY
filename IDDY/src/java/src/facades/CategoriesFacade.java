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
import src.entities.Categories;
import src.entities.Difficulties;

/**
 *
 * @author Bryan
 */
@Stateless
public class CategoriesFacade extends AbstractFacade<Categories> {

    @PersistenceContext(unitName = "IDDYPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriesFacade() {
        super(Categories.class);
    }
    
    public List<Categories> getAllCategories(){
        Query queryAllValues = em.createNamedQuery("Categories.findAll");
        return queryAllValues.getResultList();
    }
}
