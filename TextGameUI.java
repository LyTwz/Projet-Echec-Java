import java.util.Scanner;

public class TextGameUI extends GameUI {
    
    public TextGameUI() {
        super();
    }

    public String askUser(String question) {
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        sc.close();
        return answer;
    }
}
