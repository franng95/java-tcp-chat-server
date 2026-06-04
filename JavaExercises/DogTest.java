public class DogTest {
    public static void main(String[] args) {
        //Create Dog objects
        Dog dog1 = new Dog("Buddy", 3);
        Dog dog2 = new Dog("Sparkles", 5);

        // Use the objects
        System.out.println(dog1.bark());
        System.out.println(dog2.bark());

        System.out.println(dog1.getName() + " is " + dog1.getAge() + " years old");
        System.out.println(dog2.getName() + " is " + dog2.getAge() + " years old");
    }
}