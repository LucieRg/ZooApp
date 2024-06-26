package org.example.Service;

import org.example.Dao.AnimalDao;
import org.example.Entity.Animals;
import org.example.Entity.Diet;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AnimalService {
    private final AnimalDao animalDao;
    private final Scanner scanner;

    public AnimalService(AnimalDao animalDao, Scanner scanner) {
        this.animalDao = animalDao;
        this.scanner = scanner;
    }

    public void addAnimal() {
        System.out.print("Entrez le nom de l'animal: ");
        String name = scanner.nextLine();
        System.out.print("Entrez l'âge de l'animal: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le régime alimentaire (HERBIVORE, CARNIVORE, OMNIVORE): ");
        String diet = scanner.nextLine().toUpperCase();
        Diet dietEnum;
        try {
            dietEnum = Diet.valueOf(diet);
        } catch (IllegalArgumentException e) {
            System.out.println("Régime alimentaire invalide.");
            return;
        }
        Animals animal = Animals.builder()
                .name(name)
                .age(age)
                .diet(dietEnum)
                .arrivalDate(LocalDate.now())
                .build();
        animalDao.createAnimal(animal);
        System.out.println("Animal ajouté avec succès !");
    }

    public void searchAnimalById() {
        System.out.print("Entrez l'ID de l'animal: ");
        int id = Integer.parseInt(scanner.nextLine());
        Animals animal = animalDao.findById(id);
        if (animal != null) {
            System.out.println("Animal trouvé: " + animal);
        } else {
            System.out.println("Aucun animal trouvé avec cet ID.");
        }
    }

    public void searchAnimalByName() {
        System.out.print("Entrez le nom de l'animal: ");
        String nom = scanner.nextLine();
        List<Animals> animals = animalDao.findByName(nom);
        if (!animals.isEmpty()) {
            animals.forEach(System.out::println);
        } else {
            System.out.println("Aucun animal trouvé avec ce nom.");
        }
    }

    public void searchAnimalByRegime() {
        System.out.print("Entrez le régime alimentaire (HERBIVORE, CARNIVORE, OMNIVORE): ");
        String diet = scanner.nextLine().toUpperCase();
        try {
            Diet dietEnum = Diet.valueOf(diet);
            List<Animals> animals = animalDao.findByDiet(dietEnum);
            if (!animals.isEmpty()) {
                animals.forEach(System.out::println);
            } else {
                System.out.println("Aucun animal trouvé avec ce régime alimentaire.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Régime alimentaire invalide.");
        }
    }
}
