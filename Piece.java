public abstract class Piece {

    private final boolean color; // false = black, true = white

    private int state; // 0 = unused, 1 = currently used, 2 = dead
    private String position; // position on the board, corresponding to a cell

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

    // to be implemented by children

    public abstract String[] getNextMoves();

}
