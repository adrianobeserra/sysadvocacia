/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.pos.garanhuns.sysadvogacia.persistencia;

/**
 *
 * @author Gleydson
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public abstract class DAOImpl<T, I extends Serializable> implements DAO<T, I> {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("sysadvocaciaPU");
    private EntityManager entityManager;
    private final Class<T> classe;

    public DAOImpl(Class<T> classe) {
        this.classe = classe;
        entityManager = factory.createEntityManager();
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public T save(T entity) {
        T saved = null;
        getEntityManager().getTransaction().begin();
        saved = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();
        return saved;
    }

    @Override
    public void remove(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();
        //entityManager.close();    
    }

    @Override
    public T getById(I pk) {
        try {
            return getEntityManager().find(classe, pk);
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        entityManager.clear();
        List<T> temp = entityManager.createQuery("SELECT o FROM " + classe.getSimpleName() + " o").getResultList();
        //entityManager.close();
        return temp;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
