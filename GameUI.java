public abstract class GameUI {
    
    public GameUI() {
        // nothing special here
    }

    public abstract void drawUI(Board b);
    public abstract void alert(String message);
    public abstract String askUser(String question);
    public abstract int askInt(String question);
}
