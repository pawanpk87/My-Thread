class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    synchronized public void useResource(Resource othResource) {
        System.out.println(Thread.currentThread().getName() + " locked " + this.name);

        try {
            // work with this resource
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        // need other resource
        System.out.println(Thread.currentThread().getName() + " Trying to lock " + othResource.getName() + " ...");
        othResource.doSomething();
    }

    synchronized public void doSomething() {
        System.out.println(Thread.currentThread().getName() + " locked " + this.name);
    }

    public String getName() {
        return name;
    }
}

public class Deadlock {
    public static void main(String[] args) {
        Resource resource1 = new Resource("resource1");
        Resource resource2 = new Resource("resource2");

        Thread thread1 = new Thread(() -> {
            resource1.useResource(resource2);
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            resource2.useResource(resource1);
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}