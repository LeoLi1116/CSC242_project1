import java.util.Scanner;

public class main {

    public static AI initialize(){
        AI game = new AI();

        System.out.println("Choose your game:");
        System.out.println("1. Tiny 3x3x3 Connect-Three");
        System.out.println("2. Standard 7x6x4 Connect-Four");
        System.out.print("Your choice? ");
        Scanner s = new Scanner(System.in);
        try {
            int choice = s.nextInt();

            System.out.println("Choose your opponent:");
            System.out.println("1. An agent that plays randomly");
            System.out.println("2. Am agent that uses MINIMAX");
            int agent = s.nextInt();
            if(choice == 2 && agent == 2){
                System.out.println("Set depth limit for MiniMax Agent: ");
                int depth = s.nextInt();
                game.setDepth(depth);
            }
            System.out.println("Do you want to play RED (1) or YELLOW (2)?");
            int player_turn = s.nextInt();
            int AI_turn;
            if (player_turn == 1){
                AI_turn =2;
            }
            else {
                AI_turn = 1;
            }

            if (choice == 1 || choice == 2) {
                int[][] board = game.setGame(choice, AI_turn, agent);
                game.print_game(board);
                game.play_AI_game(1,board);
            } else {
                System.out.println("Please enter a valid input");
                game = initialize();
            }
            return game;
        }
        catch (Exception e){
            System.out.printf("Please enter an integer.\n ");
            return initialize();
        }


    }


    public static void main(String[] args) {
        initialize();
        while (true) {
           System.out.println("Game end. Start a new game or not? y/n");
           Scanner s = new Scanner(System.in);
           String ans = s.nextLine();
           if (ans.equals("n")){
               break;
           }
           else if (ans.equals("y")){
               initialize();
           }
       }

    }


}
