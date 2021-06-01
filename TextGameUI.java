import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

import org.w3c.dom.Text;

public class TextGameUI extends GameUI {

    private String info;

    private Scanner input;

    public static final String WHITE_SQUARE_UNICODE = "\u25A0";
    public static final String BLACK_SQUARE_UNICODE = "\u25A1";

    /*
    public static final String HORIZONTAL_LINE_UNICODE = "\u2015";  // ―
    public static final String VERTICAL_LINE_UNICODE = "\u2758";    // ❘
    public static final String TOP_LEFT_CORNER_UNICODE = "\u231C";  // ⌜
    public static final String TOP_RIGHT_CORNER_UNICODE = "\u231D"; // ⌝
    public static final String BOTTOM_LEFT_CORNER_UNICODE = "\u231E"; // ⌞
    public static final String BOTTOM_RIGHT_CORNER_UNICODE = "\u231F"; // ⌟
    */

    public TextGameUI() {
        super();
        input = new Scanner(System.in);
    }

    public void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    public String drawCell(Cell c) {
        if(c == null) { return null; }
        if(c.getPiece() != null) {
            String symbol = c.getPiece().getRepresentation();
            return " " + (c.getPiece().getState() == 1 ? symbol : (c.getColor() ? TextGameUI.WHITE_SQUARE_UNICODE : TextGameUI.BLACK_SQUARE_UNICODE)) + " ";
        }
        return " " + (c.getColor() ? TextGameUI.WHITE_SQUARE_UNICODE : TextGameUI.BLACK_SQUARE_UNICODE) + " ";
    }

    private String horizontalLine() {
        String l = "";
        for(int i = 0; i < Board.NB_COLUMNS; i++) { l += "―――"; }
        return l;
    }

    public void drawBoard(Board b) {
        int col = 1;
        int line = Board.NB_LINES; // start at a1
        System.out.println("  ⌜"+horizontalLine()+"⌝");
        while(line > 0) {
            System.out.print(line + " ❘");
            for(col=1; col <= Board.NB_COLUMNS; col++) {
                System.out.print(drawCell(b.getCell(col, line)));
            }
            line--;
            if(line > 0) {
                System.out.println("❘");
            } else {
                System.out.println("❘");
                System.out.println("  ⌞"+horizontalLine()+"⌟");
                System.out.println("    a  b  c  d  e  f  g  h ");
            }
        }
    }

    public void drawUI(Board b) {
        clearScreen();
        drawBoard(b);
    }

    public String askUser(String question) {
        System.out.print(question + " ");
        String answer = "";
        answer = this.input.nextLine();
        return answer;
    }

    public void alert(String message) {
        System.out.println(message);
    }
}
