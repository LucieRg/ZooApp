package org.example.repository;

import org.example.entity.Animals;
import org.example.util.Diet;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class AnimalRepository {
    private final EntityManager entityManager;

    public AnimalRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void createAnimal(Animals animal) {
        try {
            entityManager.persist(animal);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'animal.", e);
        }
    }

    public Animals findById(int id) {
        try {
            return entityManager.find(Animals.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'animal par ID.", e);
        }

    }

    public List<Animals> findByName(String nameSearch) {
        try {
            return entityManager.createQuery("SELECT a FROM Animals a WHERE a.name = :name", Animals.class)
                    .setParameter("name", nameSearch)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'animal par nom.", e);
        }
    }

    public List<Animals> findByDiet(Diet dietSearch) {
        try {
            return entityManager.createQuery("SELECT a FROM Animals a WHERE a.diet = :diet", Animals.class)
                    .setParameter("diet", dietSearch)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'animal par régime alimentaire.", e);
        }
    }


}

