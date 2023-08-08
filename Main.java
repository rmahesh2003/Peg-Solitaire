
import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String prompt = "";
        int[] moves;
        System.out.println("WELCOME TO CS300 PEG SOLITARE!");
        System.out.println("==============================");
        System.out.println();
        System.out.println("Board Style Menu");
        System.out.println("  1) Cross");
        System.out.println("  2) Circle");
        System.out.println("  3) Triangle");
        System.out.println("  4) Simple T");
        prompt = "Choose a board style:";
        char[][] board = createBoard(readValidInt(sc, prompt, 1, 4));
        displayBoard(board);
        while(countMovesAvailable(board)!=0) {

            moves = readValidMove(sc, board);
            board=performMove(board, moves[1], moves[0], moves[2]);
            displayBoard(board);
        }
        if(countPegsRemaining(board)==1){
            System.out.println("Congrats, you won!");
            System.out.println();
            System.out.println("==========================================");
            System.out.println("THANK YOU FOR PLAYING CS300 PEG SOLITAIRE!");
        }
        else {
            System.out.println("It looks like there are no more legal moves.  Please try again.");
            System.out.println();
            System.out.println("==========================================");
            System.out.println("THANK YOU FOR PLAYING CS300 PEG SOLITAIRE!");
        }



    }

    public static int readValidInt(Scanner in, String prompt, int min, int max) {
        int input = 0;
        boolean wrongInput;
        System.out.print(prompt);
        do {
            wrongInput = false;
            try {
                input = in.nextInt();
            } catch (Exception e) {
                System.out.print("Please enter your choice as an integer between " + min + " and " + max + ":");
                wrongInput = true;
                in.nextLine();
            }
        } while (wrongInput);

        while (input < min || input > max) {
            System.out.print("Please enter your choice as an integer between " + min + " and " + max + ":");
            do {
                wrongInput = false;
                try {
                    input = in.nextInt();
                } catch (Exception e) {
                    System.out.print("Please enter your choice as an integer between " + min + " and " + max + ":");
                    wrongInput = true;
                    in.nextLine();
                }
            } while (wrongInput);
        }
        return input;
    }

    public static char[][] createBoard(int boardType) {
        if (boardType == 1) {
            char[][] board = new char[7][9];
            for (int j = 0; j < 3; j++) {
                board[0][j] = '#';
                board[1][j] = '#';
                board[5][j] = '#';
                board[6][j] = '#';
            }
            for (int j = 3; j < 6; j++) {
                board[0][j] = '@';
                board[1][j] = '@';
                board[5][j] = '@';
                board[6][j] = '@';
            }
            for (int j = 6; j < 9; j++) {
                board[0][j] = '#';
                board[1][j] = '#';
                board[5][j] = '#';
                board[6][j] = '#';
            }
            for (int j = 0; j < 9; j++) {
                board[2][j] = '@';
                board[3][j] = '@';
                board[4][j] = '@';
            }
            board[3][4] = '-';
            return board;
        } else if (boardType == 2) {

            char[][] board = new char[6][6];
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 6; j++) {
                    board[i][j] = '@';
                }
            int k=0;
            while (k < 6) {
                board[0][k] = '#';
                board[1][k] = '-';
                board[4][k] = '-';
                board[5][k] = '#';
                k= k + 5;
            }
            k = 1;
            while (k < 5) {
                board[0][k] = '-';
                board[5][k] = '-';
                k = k + 3;
            }
            return board;

        } else if (boardType == 3) {
            char[][] board = new char[4][9];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = '@';
                }
            }
            int j = 0;
            while (j < 9) {
                for (int i = 0; i < 3; i++)
                    board[i][j] = '#';
                board[3][j] = '-';
                j = j + 8;

            }
            j = 1;
            while (j < 9) {
                for (int i = 0; i < 2; i++)
                    board[i][j] = '#';
                board[2][j] = '-';
                j = j + 6;
            }
            j = 2;
            while (j < 9) {
                board[0][j] = '#';
                board[1][j] = '-';
                j = j + 4;
            }
            board[0][3] = '-';
            board[0][5] = '-';
            board[2][4] = '-';
            return board;
        } else if (boardType == 4) {
            char[][] board = new char[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = '-';
                }
            }
            for (int i = 1; i < 4; i++) {
                board[1][i] = '@';
            }
            for (int i = 2; i < 4; i++) {
                board[i][2] = '@';
            }
            return board;
        }
        return null;
    }


    public static void displayBoard(char[][] board) {
        System.out.println();
        System.out.print(" ");
        for (int j = 0; j < board[0].length; j++) {
            System.out.print(j+1);
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }


    }

    public static int[] readValidMove(Scanner in, char[][] board)
    { String prompt;
        String dir;
        int[] list=new int[3];
        int column,row,direction;
        prompt = "Choose the COLUMN of a peg you'd like to move:";
        column=readValidInt(in, prompt, 1, board[0].length);
        prompt = "Choose the ROW of a peg you'd like to move:";
        row=readValidInt(in, prompt, 1, board.length);
        prompt = "Choose a DIRECTION to move that peg 1) UP, 2) DOWN, 3) LEFT, or 4) RIGHT:";
        direction=readValidInt(in, prompt, 1, 4);
        while (!isValidMove(board,row,column,direction)){
            if(direction==1){
                dir="UP";
            }
            else if(direction==2){
                dir="DOWN";
            }
            else if(direction==3){
                dir="LEFT";
            }
            else dir="RIGHT";
            System.out.println("Moving a peg from row "+row+" and column "+column+" "+dir+ " is not currently a legal move.");
            System.out.println();
            prompt = "Choose the COLUMN of a peg you'd like to move:";
            column=readValidInt(in, prompt, 1, board[0].length);
            prompt = "Choose the ROW of a peg you'd like to move:";
            row=readValidInt(in, prompt, 1, board.length);
            prompt = "Choose a DIRECTION to move that peg 1) UP, 2) DOWN, 3) LEFT, or 4) RIGHT:";
            direction=readValidInt(in, prompt, 1, 4);
        }
        list[0]=column;
        list[1]=row;
        list[2]=direction;

        return list ;
    }

    public static boolean isValidMove(char[][] board, int row, int column, int direction)
    { if(board[row-1][column-1]!='@')
        return false;
        if(direction==1)
            try{
                if((board[row-2][column-1]=='@')&&(board[row-3][column-1]=='-'))
                    return true;
                else return  false;

            }catch (Exception e){
                return  false;
            }
        if(direction==2)
            try {
                if((board[row][column-1]=='@')&&(board[row+1][column-1]=='-'))
                    return true;
                else return false;

            }catch (Exception e){
                return  false;
            }

        if(direction==3)
            try {
                if ((board[row - 1][column - 2] == '@') && (board[row - 1][column - 3] == '-'))
                    return true;
                else return false;
            }catch (Exception e){
                return false;
            }
        if(direction==4)
            try{
                if((board[row-1][column]=='@')&&(board[row-1][column+1]=='-'))
                    return true;
                else return false;
            }catch (Exception e){
                return false;
            }

        return false;
    }


    public static char[][] performMove(char[][] board, int row, int column, int direction)
    {
        if(isValidMove(board,row,column,direction)) {
            board[row - 1][column - 1] = '-';
            if (direction == 1) {
                board[row - 3][column - 1] = '@';
                board[row - 2][column - 1] = '-';
            }
            if (direction == 2) {
                board[row + 1][column - 1] = '@';
                board[row][column - 1] = '-';
            }
            if (direction == 3) {
                board[row - 1][column - 3] = '@';
                board[row - 1][column - 2] = '-';
            }
            if (direction == 4) {
                board[row - 1][column + 1] = '@';
                board[row - 1][column] = '-';
            }
        }

        return board;
    }

    public static int countPegsRemaining(char[][] board)
    {int count=0;
        for(int i=0;i< board.length;i++)
            for(int j=0;j< board[0].length;j++)
                if(board[i][j]=='@')
                    count++;
        return count;
    }

    public static int countMovesAvailable(char[][] board)
    {int moves=0;
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++) {
                if (board[i][j] == '@') {
                    if (isValidMove(board, i + 1, j + 1, 1))
                        moves++;
                    if (isValidMove(board, i + 1, j + 1, 2))
                        moves++;
                    if (isValidMove(board, i + 1, j + 1, 3))
                        moves++;
                    if (isValidMove(board, i + 1, j + 1, 4))
                        moves++;
                }
            }
        return moves;
    }

}
