public class Dog {
    //Properties (like instance variables in Python)
    private String name;
    private int age;

    // Constructor (like __init_ in Python)
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //Method
    public String bark() {
        return this.name + " says Woof!";
    }

    //Getter methods (to access private variables)
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}