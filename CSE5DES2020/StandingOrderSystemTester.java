public class StandingOrderSystemTester {
    static int testCount = 0;
    static String test;

    public static void main(String[] args) throws Exception {
        testInit();
        testUC1();
        testUC2();
        // for a complicated use case, such as use case 4, you should consider
        // having more than one test method
    }

    // test creation of new system
    public static void testInit() throws Exception {
        test = "TEST UC0: Create new system";
        StandingOrderSystem sos = new StandingOrderSystem();
        System.out.println("\n" + test + "\n" + sos);
    }

    // test add product
    public static void testUC1() throws Exception {
        test = "TEST UC1: Add product";
        // create a new system object
        StandingOrderSystem sos = new StandingOrderSystem();
        // then add a product
        sos.addProduct("P1", "Coke");
        System.out.println("\n" + test + "\n" + sos);
        // add two more valid products
        sos.addProduct("P2", "Thums Up");
        sos.addProduct("P3", "Sprite");
        System.out.println("\n" + test + "\n" + sos);

        // some invalid cases, i.e. preconditions are not satisfied
        try {
            sos.addProduct("P2", "Thums Up");
            sos.addProduct("P3", "Sprite");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("\n" + test + "\n" + sos);
        }
    }

    // test add customer
    public static void testUC2() throws Exception {
        test = "TEST UC2: Add customer";
        // create a system object
        // then add enough data for your testing purpose
        StandingOrderSystem sos = new StandingOrderSystem();
        sos.addProduct("P1", "Coke");
        // some valid cases
        sos.addCustomer("C1", "Smith", "A1", "1 Street-1", "Suburb-1", "John", "1111");
        sos.addCustomer("C2", "Rohith", "A2", "ABC 23", "Suburb-19", "Adi", "3707");

        System.out.println("\n" + test + "\n" + sos);
        // some invalid cases
        try {
            sos.addCustomer("C2", "Rohith", "A3", "ABC 23", "Suburb-19", "Adi", "3707");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("\n" + test + "\n" + sos);
        }
        try {
            sos.addCustomer("C4", "Rohith", "A1", "ABC 23", "Suburb-19", "Adi", "3707");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("\n" + test + "\n" + sos);
        }
    }
}