package org.example.Dao;

import org.example.Entity.Animals;
import org.example.Entity.Diet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AnimalDao {
    private EntityManager entityManager;

    public AnimalDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void createAnimal(Animals animal) {
        entityManager.persist(animal);
    }

    public Animals findById(int id) {
        return entityManager.find(Animals.class, id);

    }

    public List<Animals> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT a FROM Animals a WHERE a.name = :name", Animals.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Animals> findByDiet(Diet diet) {
        return entityManager.createQuery(
                        "SELECT a FROM Animals a WHERE a.diet = :diet", Animals.class)
                .setParameter("diet", diet)
                .getResultList();
    }


}

