public class Cell {

    private final String position; 

    private final Boolean color;

    // this attribute really shouldn't be changed once the object has been instantiated

    private Piece piece; 

    public Cell(String pos, Boolean clr) {
        this.position = ((int) pos.charAt(0) >= 97 && (int) pos.charAt(0) <= 104) && (Integer.parseInt(pos.substring(1)) >= 1 && Integer.parseInt(pos.substring(1)) <= 8) ? pos : null;
        this.color = clr;
        this.piece = null;
    }

    public Cell(String pos, Boolean clr, Piece p) {
        this(pos, clr);
        this.piece = p;
    }

    // getters

    public String getPosition() {
        return this.position;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Boolean getColor() {
        return this.color;
    }

    // setters 

    // no setter for position, as it's a final attribute

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public String toString() {
        return "Cell [position=" + this.position + ", color=" + this.color + ", piece=" + this.piece + "]";
    }

}
