package org.example.util;

import org.example.repository.AnimalRepository;
import org.example.service.AnimalService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Ihm {
    private final AnimalService animalService;

    public Ihm(AnimalService animalService) {
        this.animalService = animalService;
    }

    public static void start() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zoo");
        EntityManager em = emf.createEntityManager();
        AnimalRepository animalRepository = new AnimalRepository(em);
        Scanner scanner = new Scanner(System.in);
        AnimalService animalService = new AnimalService(animalRepository, scanner);
        Ihm ihm = new Ihm(animalService);

        try {
            int choice;
            while (true) {
                MenuApp.displayMenu();
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            ihm.animalService.addAnimal();
                            break;
                        case 2:
                            ihm.animalService.searchAnimalById();
                            break;
                        case 3:
                            ihm.animalService.searchAnimalByName();
                            break;
                        case 4:
                            ihm.animalService.searchAnimalByRegime();
                            break;
                        case 5:
                            System.out.println("Au revoir !");
                            return;
                        default:
                            System.out.println("Choix invalide !");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un numéro valide.");
                }
            }
        } finally {
            em.close();
            emf.close();
            scanner.close();
        }
    }
}
