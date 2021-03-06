package zoo.park;

import zoo.animals.Animal;
import zoo.animals.IAnimal;
import zoo.enums.FoodQuantity;
import zoo.enums.Tidyness;

import java.util.ArrayList;
import java.util.Iterator;

public class Cage<A extends IAnimal> {

    protected String name;
    protected int area;
    protected int capacity;
    protected int numbersOfAnimalsInCage;
    protected ArrayList<A> animals ;
    protected Tidyness tidyness;
    protected String type;

    public Cage() {
        name = "Cage";
        type = "Normal";
        area = 50;
        capacity = 10;
        numbersOfAnimalsInCage = 0 ;
        tidyness = Tidyness.Dirty; // Set to clean
        animals = new ArrayList<A>();
    }

    public Cage(String name, int area, int capacity, int numbersOfAnimalsInCage, Tidyness tidyness, ArrayList<A> animals) {
        this.name = name;
        this.area = area;
        this.capacity = capacity;
        this.numbersOfAnimalsInCage = numbersOfAnimalsInCage;
        this.tidyness = tidyness;
        this.animals = animals;
        type = "Normal";
    }

    public void displayAnimals() {
        for (Iterator<A> animal = animals.iterator(); animal.hasNext(); ) {
            A currentAnimal = animal.next();
            currentAnimal.display();
        }
    }

    public String display() {

        String statusSpeciesInCage;
        if (animals.isEmpty()) {
            statusSpeciesInCage = "empty";
        }
        else {
            statusSpeciesInCage = numbersOfAnimalsInCage + " " + animals.get(0).getSpecieName();
        }
        String toReturn = statusSpeciesInCage + " | " + tidyness.toString() + " | Capacity : " + capacity + " | " + type ;
        return toReturn;

    }

    public A getAnimaltype() {
        if (!animals.isEmpty()) {
            return animals.get(0);
        }
        else {
            return null;
        }

    }

    public void addAnimal(A animal) throws Exception {
        if (animals.isEmpty()) {
                animals.add(animal);
                numbersOfAnimalsInCage += 1 ;

        } // Les animaux sont de la bonne espèce
        else if (!animals.isEmpty() && animals.get(0).getSpecieName() == animal.getSpecieName()){
            animals.add(animal);
            numbersOfAnimalsInCage +=1 ;
        }
        else {
            throw new Exception("L'animal " + animal.getSpecieName() + " n'a pas pu être ajouté.");
        }
    }

    public void removeAnimal(A animal) throws Exception {
        if (numbersOfAnimalsInCage !=0) {
            animals.remove(animal);
            numbersOfAnimalsInCage -= 1 ;
        }
        else {
            throw new Exception("Impossible de retirer l'animal") ;
        }
    }

    public void feedAnimals(FoodQuantity foodQuantity) {
        for (Iterator<A> animal = animals.iterator(); animal.hasNext(); ) {
            A currentAnimal = animal.next();
            currentAnimal.eat(foodQuantity);
        }
    }

    public void clean() {
        if ((tidyness == Tidyness.Dirty || tidyness == Tidyness.Alright) && numbersOfAnimalsInCage == 0) {
            tidyness = Tidyness.Clean;
            System.out.println("La cage a été lavée");
        }
        else {
            System.out.println("La cage n'a pas pû être lavée");
        }
    }

    public Tidyness getTidyness() {
        return tidyness;
    }

    public ArrayList<A> getAnimals() {
        return animals;
    }
}
