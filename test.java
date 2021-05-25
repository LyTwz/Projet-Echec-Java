import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        // test whatever you want over here
        Board board = new Board();
        String pos = "a1";
        Bishop b = new Bishop(false, 1, pos);
        System.out.println(Arrays.toString(b.getNextMoves()));
        Rook r = new Rook(false, 1, pos);
        System.out.println(Arrays.toString(r.getNextMoves()));
        Queen q = new Queen(false, 1, pos);
        System.out.println(Arrays.toString(q.getNextMoves()));
    }
}
