public abstract class Piece {

    private final boolean color; // false = black, true = white

    private int state; // 0 = unused, 1 = currently used, 2 = dead
    private String position; // position on the board, corresponding to a cell
    private String representation; // this is supposed to be initialised by each child class

    public static final String BLACK_KING_UNICODE = "\u2654";
    public static final String BLACK_QUEEN_UNICODE = "\u2655";
    public static final String BLACK_ROOK_UNICODE = "\u2656";
    public static final String BLACK_BISHOP_UNICODE = "\u2657";
    public static final String BLACK_KNIGHT_UNICODE = "\u2658";
    public static final String BLACK_PAWN_UNICODE = "\u2659";

    public static final String WHITE_KING_UNICODE = "\u265A";
    public static final String WHITE_QUEEN_UNICODE = "\u265B";
    public static final String WHITE_ROOK_UNICODE = "\u265C";
    public static final String WHITE_BISHOP_UNICODE = "\u265D";
    public static final String WHITE_KNIGHT_UNICODE = "\u265E";
    public static final String WHITE_PAWN_UNICODE = "\u265F";

    public Piece(boolean clr, int stt) { // default and only constructor for the 'Piece' class
        this.color = clr;
        this.state = stt;
    }

    public Piece(boolean clr, int stt, String pos) {
        this(clr, stt);
        this.position = pos;
    } 

    // getters

    public boolean getColor() {
        return this.color;
    }

    public int getState() {
        return this.state;
    }

    public String getPosition() {
        return this.position;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public String getStatus() {
        return this.state == 1 ? (this.getType() + " at " + this.getPosition()) : (this.state == 0 ? "Unused " + this.getType() : "Captured " + this.getType());
    }

    // setters

    // it doesn't make sense to 'setColor' or 'setRole', 
    // as these attribute shouldn't be changed once the object has been instantiated

    public void setState(int newState) {
        this.state = newState >= 0 && newState <= 2 ? newState : this.state;
        // check whether newState is either 0, 1 or 2
        // if not, keep current value
    }

    public void setPosition(String pos) {
        // check whether 'pos' is a valid cell position
        this.position = ((int) pos.charAt(0) >= 97 && (int) pos.charAt(0) <= 104) && (Integer.parseInt(pos.substring(1)) >= 1 && Integer.parseInt(pos.substring(1)) <= 8) ? pos : this.position;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public void setRepresentation(String rep) {
        this.representation = rep;
    }

    public String toString() {
        return "Piece[state=" + this.state + ", position=" + this.position + "]";
    }

    // to be implemented by children

    public abstract String[] getNextMoves();

}
