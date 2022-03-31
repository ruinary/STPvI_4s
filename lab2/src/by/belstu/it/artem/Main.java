package by.belstu.it.artem;

public class Main {

    public static void main(String[] args) {
        // TODO Add a new method
        System.out.println("Hello World!");

        /*for (int i=0; i<9; i++) {
            if (i>-1)
                if (i<10)
                    System.out.println(i);
        }*/

        for (int count = 0; count < 10; count++) {
            System.out.println("count = " + count);
        }

        TestFunction obj = new TestFunction();

        String elem = "some string";

        WrapperString ws1 = new WrapperString(elem);
        ws1.replace();

        WrapperString ws2 = new WrapperString(elem) {
            @Override
            public void replace() {
                System.out.println("другой replace");
                delete();
            }

            public void delete() {
                System.out.println("delete!");
            }
        };
        ws2.replace();
    }
}
