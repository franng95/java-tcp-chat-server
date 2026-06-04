public class ThreadExample {

    // This is a task that can run in its own thread
    static class CountTask implements Runnable {
        private String name;

        public CountTask(String name) {
            this.name = name;
        }

        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " counting: " + i);
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " finished!");
        }
    }
    public static void main(String[] args) {
        // Create two tasks
        CountTask task1 = new CountTask("Task-A");
        CountTask task2 = new CountTask("Task-B");

        // Create two threads
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        // Start both threads 
        thread1.start();
        thread2.start();

        System.out.println("Main thread: Both tasks started!");
    }
}

