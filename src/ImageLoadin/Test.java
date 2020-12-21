package ImageLoadin;

public class Test {

    public static void main(String[] args) {
        String string = "";
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 160000; ++j) {
                string = string + "0" + " ";
                System.out.println(i + " " + j);
            }
        }
    }
}
