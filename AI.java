import java.util.Random;
import java.util.Scanner;

public class AI {
    int AI_turn;
    int width;
    int height;
    int win_req;
    int agent;
    int depth;
    double win;
    double lose;
    double count;

    public void createAI(int ai_turn,int ai_width,int ai_height,int ai_win_req){
        setAI_turn(ai_turn);
        setAI_width( ai_width);
        setAI_height( ai_height);
        setAI_win_req(ai_win_req);
    }
    public void setAI_turn(int ai_turn){
        this.AI_turn = ai_turn;
    }
    public void setAI_width(int ai_width){
        this.width = ai_width;
    }
    public void setAI_height(int ai_height){
        this.height = ai_height;
    }
    public void setAI_win_req(int ai_win_req){
        this.win_req = ai_win_req;
    }
    public void  setDepth(int depth){this.depth = depth;};

    public int[][] setGame(int choice, int AI_turn, int agent) {
        this.agent = agent;

        int[][] game;
        if(choice == 1){
            createAI(AI_turn,3,3,3);
            game = new int[3][3];
        }
        else{
            createAI(AI_turn,7,6,4);
            game = new int[6][7];
        }

        for (int i = 0; i < this.height; i++){
            for (int j = 0; j<this.width; j++){
                game[i][j] = 0;
            }
        }

        return game;
    }

    public int ai_3x3(int turn, int[][] game){
        int decision;
        int c1,c2,c3;
        if (!check_full(1,game)) {//if not full do minimax
         c1 = play_3x3(turn, 1, copy(game));
        }
        else {
            c1 = -1000;//if full don't play on this row
        }
        if (!check_full(2,game)) {
            c2 =  play_3x3(turn, 2, copy(game));
        }
        else {
            c2 = -1000;
        }
        if (!check_full(3,game)) {
            c3 =  play_3x3(turn, 3, copy(game));
        }
        else {
            c3 = -1000;
        }

        if (c1 > c2){
            if(c1 > c3) {
                decision = 1;
            }
            else {
                decision =3;
            }
        }
        else {
            if (c2 > c3) {
                decision = 2;
            }
            else {
                decision = 3;
            }
        }
        return decision;
    }

    public int play_3x3(int turn, int col,  int[][] game){
            add_piece(col, turn, game);

            if (check_win(turn, game)) {
                if (turn == this.AI_turn) {
                    return 1;
                } else {
                    return -1;
                }
            }

            if (check_draw(game)) {
                return 0;
            }

            if (turn == 1) {
                turn = 2;
            } else {
                turn = 1;
            }

            int[][] copy;
            int value;
            if (turn == AI_turn) {//minimax
                value = -1000;
                for (int i = 1; i < 4; i++) {
                    if (!check_full(i, game)) {
                        copy = copy(game);
                        int result = play_3x3(turn, i, copy);
                        if (result > value){
                            value = result;
                        }
                    }
                }
            }
            else {
                value = 1000;
                for (int i = 1; i < 4; i++) {
                    if (!check_full(i, game)) {
                        copy = copy(game);
                        int result = play_3x3(turn, i, copy);
                        if (result < value){
                            value = result;
                        }
                    }
                }
            }
            return value;
    }

    public void reset(){
        this.win =0;
        this.lose =0;
        this.count =0;
    }

    public int ai_6x7(int turn, int[][] game){
       /* if (check_empty(game)){
            return 4;
        }*/

        double c1, c2,c3,c4,c5,c6,c7;
        if (!check_full(1,game)) {
            c1 = play_6x7(turn, 1, copy(game), 0);//call minimax
            if (c1 == -2){
                c1 = (this.win-this.lose)/this.count; //if reach depth, return net win rate for this move
            }
        }
        else {
            c1 = -1000;
        }
        reset();

        if (!check_full(2,game)) {
            c2 =  play_6x7(turn, 2, copy(game), 0);
            if (c2 == -2){
                c2 = (this.win-this.lose)/this.count;;
            }
        }
        else {
            c2 = -1000;
        }
        reset();
        if (!check_full(3,game)) {
            c3 =  play_6x7(turn, 3, copy(game), 0);
            if (c3 == -2){
                c3 = (this.win-this.lose)/this.count;;
            }
        }
        else {
            c3 = -1000;
        }
        reset();
        if (!check_full(4,game)) {
            c4 =  play_6x7(turn, 4, copy(game), 0);
                if (c4 == -2){
                    c4 = (this.win-this.lose)/this.count;;
                }

        }
        else {
            c4 = -1000;
        }
        reset();

        if (!check_full(5,game)) {
            c5 =  play_6x7(turn, 5, copy(game), 0);
            if (c5 == -2){
                c5 = (this.win-this.lose)/this.count;;
            }
        }
        else {
            c5 = -1000;
        }
        reset();
        if (!check_full(6,game)) {
            c6 =  play_6x7(turn, 6,copy(game), 0);
            if (c6 == -2){
                c6 = (this.win-this.lose)/this.count;;
            }
        }
        else {
            c6 = -1000;
        }
        reset();
        if (!check_full(7,game)) {
            c7 =  play_6x7(turn, 7, copy(game), 0);
            if (c7 == -2){
                c7 = (this.win-this.lose)/this.count;;
            }
        }
        else {
            c7 = -1000;
        }
        reset();

        System.out.println("wining condition for each move:");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);

        return find_max(c1,c2,c3,c4,c5,c6,c7);

    }

    public int find_max(double a, double b, double c, double d, double e, double f, double g){
        if (a >= b && a >= c && a >= d && a >= e && a >= f && a >= g){
            return 1;
        }
        if (b >= a && b >= c && b >= d && b >= e && b >= f && b >= g){
            return 2;
        }
        if (c >= b && c >= a && c >= d && c >= e && c >= f && c >= g){
            return 3;
        }
        if (d >= b && d >= c && d >= a && d >= e && d >= f && d >= g){
            return 4;
        }
        if (e >= b && e >= c && e >= d && e >= a && e >= f && e >= g){
            return 5;
        }
        if (f >= b && f >= c && f >= d && f >= e && f >= a && f >= g){
            return 6;
        }
        return 7;


    }

    public int play_6x7(int turn, int col,  int[][] game, int level){
        this.count++;
        add_piece(col, turn, game);

        if (check_win(turn, game)) {
            if (turn == this.AI_turn) {
                this.win++;
                return 1;
            } else {
                this.lose++;
                return -1;
            }
        }

        if (check_draw(game)) {
            return 0;
        }

        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }

        if (level== this.depth){
            return -2;
        }


        int[][] copy;
        int value;
        if (turn != AI_turn) {
            value = -1000;
            for (int i = 1; i < 8; i++) {
                if (!check_full(i, game)) {
                    copy = copy(game);
                    int result = play_6x7(turn, i, copy, level+1);
                    if (result > value){
                        value = result;
                        if (result == 1){//alpha beta pruning, since this highest is 1, and when return a 1
                            break;       // cut off search
                        }
                    }
                }
            }
        }
        else {
            value = 1000;
            for (int i = 1; i < 8; i++) {
                if (!check_full(i, game)) {
                    copy = copy(game);
                    int result = play_6x7(turn, i, copy, level+1);
                    if (result < value){
                        value = result;

                    }
                }
            }
        }
        return value;
    }

    public void print_game(int[][] game){
        if (this.width == 3){
            print_3x3(game);
        }
        else {
            print_6x7(game);
        }


    }

    public void print_6x7(int[][] game){
        System.out.println("  1 2 3 4 5 6 7");
        for (int i = 0; i< 6; i++){
            System.out.printf("%d|", i+1);
            for (int j = 0; j < 7 ; j++){
                if (game[i][j] == 0){
                    System.out.print(" |");
                }
                if (game[i][j] == 1){
                    System.out.print("X|");
                }
                if (game[i][j] == 2){
                    System.out.print("O|");
                }
            }
            System.out.println();
        }

    }

    public void print_3x3(int[][] game){
        System.out.println("  1 2 3 ");
        for (int i = 0; i<3; i++){
            System.out.printf("%d|", i+1);
            for (int j = 0; j <3; j++){
                if (game[i][j] == 0){
                    System.out.print(" |");
                }
                if (game[i][j] == 1){
                    System.out.print("X|");
                }
                if (game[i][j] == 2){
                    System.out.print("O|");
                }
            }
            System.out.println();
        }


    }

    public void play_AI_game(int turn, int[][] game){
        Scanner s = new Scanner(System.in);
        Random r = new Random();
        int choice;
        while (true){

            if (turn == 1){
                System.out.println("Next to play: RED/X");
            }
            else {
                System.out.println("Next to play: YEllOW/O");
            }

            System.out.println("Your move [column]? ");

            if (turn == this.AI_turn){
                System.out.println("I'm thinking...");
                int[][] copy = copy(game);
                if (this.agent == 1){
                    choice = r.nextInt((width+1))+1;
                }
                else {
                 if (this.width == 3) {
                        choice = this.ai_3x3(turn, copy);
                    } else {
                        choice = this.ai_6x7(turn, copy);
                    }
                }
                System.out.printf("Best move: %d\n", choice);

            }
            else {
                try {
                    choice = s.nextInt();
                }
                catch (Exception e){
                    System.out.print("Please enter an valid input, game restarting.");
                    break;
                }
            }

            if (choice < 1 || choice > width || check_full(choice, game)) {
                if (turn != this.AI_turn) {
                    System.out.println("this one is full");
                    System.out.println(game[0][choice - 1]);
                    System.out.println("Invalid move, try again.");
                }
            } else {
                add_piece(choice,turn,game);

                if (check_win(turn, game)){
                    print_game(game);
                    System.out.printf("Player %d win the game.\n", turn);
                    break;
                }

                if (check_draw(game)){
                    print_game(game);
                    System.out.println("Draw");
                    break;
                }

                if (turn ==1 ){
                    turn =2;
                }
                else {
                    turn =1;
                }
                print_game(game);
            }

        }
    }

    //check wining condition
    public boolean check_win(int turn, int[][] game){
        if (this.width == 3){
            return win_3x3(turn, game);
        }
        else {
            return win_6x7_cr(turn, game) || win_6x7_dig(turn, game);
        }

    }

    public boolean win_6x7_dig(int turn, int[][] game){
        if (game[2][0] == turn && game[3][1] == turn && game[4][2] == turn && game[5][3] == turn){
            return true;
        }
        if (game[1][0] == turn && game[2][1] == turn && game[3][2] == turn && game[4][3] == turn){
            return true;
        }
        if (game[2][1] == turn && game[3][2] == turn && game[4][3] == turn && game[5][4] == turn){
            return true;
        }
        if (game[0][0] == turn && game[1][1] == turn && game[2][2] == turn && game[3][3] == turn){
            return true;
        }
        if (game[1][1] == turn && game[2][2] == turn && game[3][3] == turn && game[4][4] == turn){
            return true;
        }
        if (game[2][2] == turn && game[3][3] == turn && game[4][4] == turn && game[5][5] == turn){
            return true;
        }
        if (game[0][1] == turn && game[1][2] == turn && game[2][3] == turn && game[3][4] == turn){
            return true;
        }
        if (game[1][2] == turn && game[2][3] == turn && game[3][4] == turn && game[4][5] == turn){
            return true;
        }
        if (game[2][3] == turn && game[3][4] == turn && game[4][5] == turn && game[5][6] == turn){
            return true;
        }
        if (game[0][2] == turn && game[1][3] == turn && game[2][4] == turn && game[3][5] == turn){
            return true;
        }if (game[1][3] == turn && game[2][4] == turn && game[3][5] == turn && game[4][6] == turn){
            return true;
        }if (game[0][3] == turn && game[1][4] == turn && game[2][5] == turn && game[3][6] == turn){
            return true;
        }if (game[3][0] == turn && game[2][1] == turn && game[1][2] == turn && game[0][3] == turn){
            return true;
        }if (game[4][0] == turn && game[3][1] == turn && game[2][2] == turn && game[1][3] == turn){
            return true;
        }if (game[3][1] == turn && game[2][2] == turn && game[1][3] == turn && game[0][4] == turn){
            return true;
        }if (game[5][0] == turn && game[4][1] == turn && game[3][2] == turn && game[2][3] == turn){
            return true;
        }if (game[4][1] == turn && game[3][2] == turn && game[2][3] == turn && game[1][4] == turn){
            return true;
        }if (game[3][2] == turn && game[2][3] == turn && game[1][4] == turn && game[0][5] == turn){
            return true;
        }if (game[5][1] == turn && game[4][2] == turn && game[3][3] == turn && game[2][4] == turn){
            return true;
        }if (game[4][2] == turn && game[3][3] == turn && game[2][4] == turn && game[1][5] == turn){
            return true;
        }if (game[3][3] == turn && game[2][4] == turn && game[1][5] == turn && game[0][6] == turn){
            return true;
        }if (game[5][2] == turn && game[4][3] == turn && game[3][4] == turn && game[2][5] == turn){
            return true;
        }if (game[4][3] == turn && game[3][4] == turn && game[2][5] == turn && game[1][6] == turn){
            return true;
        }
        return game[5][3] == turn && game[4][4] == turn && game[3][5] == turn && game[2][6] == turn;
    }

    public boolean win_6x7_cr(int turn, int[][] game){
        int count;
        for (int i = 0; i< 6; i++){
            count = 0;
            for (int j = 0; j < 7; j++){
                if(turn == game[i][j]){
                    count++;
                    if (count == this.win_req){
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        for (int j = 0; j< 7; j++){
            count = 0;
            for (int i = 0; i < 6; i++){
                if(turn == game[i][j]){
                    count++;
                    if (count == this.win_req){
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public boolean win_3x3(int turn, int[][] game){
        int count = 0;

        for (int i = 0; i< 3; i++){
            count = 0;
            for (int j = 0; j < 3; j++){
                if(turn == game[i][j]){
                    count++;
                    if (count == this.win_req){
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        for (int j = 0; j< 3; j++){
            count = 0;
            for (int i = 0; i < 3; i++){
                if(turn == game[i][j]){
                    count++;
                    if (count == this.win_req){
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }

        if(game[0][0] == turn && game[1][1] == turn && game[2][2] == turn){
            return true;
        }

        return game[0][2] == turn && game[1][1] == turn && game[2][0] == turn;
    }
    //check win end
    // check draw
    public boolean check_draw(int[][] game){
        boolean draw = false;
        for (int i = 1; i <= width; i++){
            draw = check_full(i, game);
            if (!draw){
                break;
            }
        }
        return draw;
    }

    public void add_piece(int a, int turn, int[][] game){
        int i = this.height;
        while (i > 0){
            if (game[i-1][a-1] == 0){
                game[i-1][a-1] = turn;
                break;
            }
            i--;
        }

    }

    public boolean check_full(int a, int[][]game){
        return  game[0][a - 1] != 0;

    }

    public int[][] copy(int[][] a){
        int[][] c1;
        if (this.width == 3){
            c1 = new int[3][3];
        }
        else {
            c1 = new int[6][7];
        }

        for (int i = 0; i < height; i++){
            if (width >= 0) System.arraycopy(a[i], 0, c1[i], 0, width);
        }
        return c1;
    }


}
