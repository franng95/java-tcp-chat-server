public class GreetingProgram {

    public static String greet(String name) {
        return "Hello, " + name + "!";
    }

    public static int addNumbers(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        String message = greet("Fran");
        System.out.println(message);

        String message2 = greet("Toro");
        System.out.println(message2);

        int result = addNumbers(10, 5);
        System.out.println("10 + 5 = " + result);
    }
}