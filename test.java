import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        // test whatever you want over here
        System.out.println(Board.intToPosition(1, 8));
        int [] pos = Board.positionToInt("h2");
        System.out.println(Arrays.toString(pos));
    }
}
