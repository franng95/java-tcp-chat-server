import java.util.ArrayList;

public class DogList {
    public static void main(String[] args) {
        //Create an ArrayList to store Dog objects
        ArrayList<Dog> dogs = new ArrayList<Dog>();

        // Add dogs to the list
        dogs.add(new Dog("Toro", 3));
        dogs.add(new Dog("Sparkles", 5));
        dogs.add(new Dog("Taily", 1));

        // Print all dogs
        System.out.println("All dogs:");
        for (Dog dog : dogs) {
            System.out.println(dog.bark());
        }

        // Access specific dog
        System.out.println("\nFrist dog:");
        Dog firstDog = dogs.get(0);
        System.out.println(firstDog.getName() + " is " + firstDog.getAge());

        // Number of dogs
        System.out.println("\nTotal dogs: " + dogs.size());
    }
}