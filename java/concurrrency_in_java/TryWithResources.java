/**
 * TryWithResources
 */
public class TryWithResources {

    public static class AutoCloseableResourcesFirst implements AutoCloseable {

        public AutoCloseableResourcesFirst() {
            System.out.println("Constructor -> AutoCloseableResources_First");
        }

        public void doSomething() {
            System.out.println("Something -> AutoCloseableResources_First");
        }

        @Override
        public void close() throws Exception {
            System.out.println("Closed AutoCloseableResources_First");
        }
    }

    public static class AutoCloseableResourcesSecond implements AutoCloseable {

        public AutoCloseableResourcesSecond() {
            System.out.println("Constructor -> AutoCloseableResources_Second");
        }
    
        public void doSomething() {
            System.out.println("Something -> AutoCloseableResources_Second");
        }
    
        @Override
        public void close() throws Exception {
            System.out.println("Closed AutoCloseableResources_Second");
        }
    }

    private static void orderOfClosingResources() throws Exception {
        try (AutoCloseableResourcesFirst af = new AutoCloseableResourcesFirst();
            AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {
    
            af.doSomething();
            as.doSomething();
        }
    }

    public static void main(String[] args) {
        try {
            TryWithResources.orderOfClosingResources();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * Output:
 * java TryWithResources.java
 * Constructor -> AutoCloseableResources_First
 * Constructor -> AutoCloseableResources_Second
 * Something -> AutoCloseableResources_First
 * Something -> AutoCloseableResources_Second
 * Closed AutoCloseableResources_Second
 * Closed AutoCloseableResources_First
 */