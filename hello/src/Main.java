import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class Main {

    int iv1;
    int iv2;

    public Main() {
    }

    @Override
    public String toString() {
        return "Main{" +
                "iv1=" + iv1 +
                ", iv2=" + iv2 +
                '}';
    }

    public int getIv1() {
        return iv1;
    }

    public void setIv1(int iv1) {
        this.iv1 = iv1;
    }

    public int getIv2() {
        return iv2;
    }

    public void setIv2(int iv2) {
        this.iv2 = iv2;
    }

    public static void main(String[] args) {
        String s = "IntelliJ Test";
        ext(s);

        Calendar cal = getInstance();
        System.out.println("cal = " + cal);
    }

    private static void ext(String s) {
        try {
            System.out.println("s = " + s);
            System.out.println("s = " + s);
            System.out.println("s = " + s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}