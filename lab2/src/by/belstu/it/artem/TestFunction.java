package by.belstu.it.artem;

public class TestFunction {
    public int test;

    public int getTest() {
        return test;
    }
    public void setTest(int test) {
        this.test = test;
    }

    public TestFunction() {
    }
    public TestFunction(int test) {
        this.test = test;
    }

    public String getValue() {
        it_speaks();
        return "This is the 1st project.";
    }

    private void it_speaks() {
        System.out.println("speaks!!");
    }
}