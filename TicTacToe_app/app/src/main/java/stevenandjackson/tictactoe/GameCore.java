package stevenandjackson.tictactoe;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Scanner;


public class GameCore extends AppCompatActivity {

    /**
     *  Simple struct encapsulates x & y of a spot
     */

    Scanner reader = new Scanner(System.in);  // Creating a scanner that reads user input
    //boolean isPlayerDone = false;
    //Thread t = new Thread();


    int[][] board;
    int gameStatus;
    static final int BLANK = 0, CROSS = 1, CIRCLE =2;
    static final int IN_PROCESS = 0, PLAYER_WON = 1, AI_WON =2, DRAW =3;


    public GameCore(){

        this.board = new int[3][3];

        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[i].length; j++){
                board[i][j] = GameCore.BLANK;
            }
        }


        this.gameStatus = GameCore.IN_PROCESS;
    }


    /**
     * Where main game procedure runs.
     */
    public void MainProcedure(){

        if(!GameCore.isFull(this.board) && this.gameStatus == GameCore.IN_PROCESS ){

            gameStatus = GameCore.inspectGameStatus(this.board);

            if (gameStatus == GameCore.IN_PROCESS){
                this.IntelligentAIMoves();
            }
            // Check if the game if finished
            this.gameStatus = GameCore.inspectGameStatus(this.board);

        }

    }


    /**
     * Graphic method printing out all the states of the board
     */
    public static void printBoard(int[][] curBoard){
        System.out.println("-------");

        for (int i = 0; i < curBoard.length; i++){
            for (int j = 0; j < curBoard[i].length; j++){



                if (curBoard[i][j] == GameCore.BLANK){
                    System.out.print("-");
                } else if (curBoard[i][j] == GameCore.CROSS){
                    System.out.print("X");
                } else if (curBoard[i][j] == GameCore.CIRCLE){
                    System.out.print("O");
                } else {
                    // thorw some kinda error
                    // thats very descriptive Steven
                }

                if (j == 0 || j == 1){
                    System.out.print("|");
                }

            }
            System.out.print("\n");

        }
        System.out.println("-------");

    }


    /**
     *
     * @param x
     * @param y
     * @param token
     * @return return true if successfully put down a token, false if failed
     *
     */
    public boolean addToken(int x, int y, int token){
        boolean success = true;

        if (this.board[y][x] == GameCore.BLANK){
            this.board[y][x] = token;

        } else {

            success = false;
        }

        return success;
    }


    public static boolean isFull(int[][] curBoard){

        boolean isFull = true;
        for (int i = 0; i < curBoard.length && isFull; i++){
            for (int j = 0; j < curBoard[i].length && isFull; j++){
                if (curBoard[i][j] == GameCore.BLANK){
                    isFull = false;
                }
            }
        }
        return isFull;
    }


    /**
     * This method updates the game status based on current situation
     * @return
     */
    public static int inspectGameStatus(int[][] curBoard){

        for (int i = 0; i < curBoard.length; i++){
            int xRowCircleCount = 0, xRowCrossCount = 0;
            int yRowCircleCount = 0, yRowCrossCount = 0;

            for (int j = 0; j < curBoard[i].length; j++){
                if (curBoard[i][j] == GameCore.CIRCLE){
                    xRowCircleCount ++;
                } else if (curBoard[i][j] == GameCore.CROSS){
                    xRowCrossCount ++;
                } else {}


                if (curBoard[j][i] == GameCore.CIRCLE){
                    yRowCircleCount ++;
                } else if (curBoard[j][i] == GameCore.CROSS){
                    yRowCrossCount ++;
                } else {}
            }

            if ( xRowCircleCount >= 3 || yRowCircleCount >= 3){
                return GameCore.AI_WON;
            } else if (xRowCrossCount >= 3 || yRowCrossCount >= 3){
                return GameCore.PLAYER_WON;
            }

        }


        int leftCircleCount = 0, leftCrossCount = 0;
        int rightCircleCount = 0, rightCrossCount = 0;
        for (int i = 0, j = 2; i < curBoard.length; i++, j--){

            if (curBoard[i][i] == GameCore.CIRCLE){
                leftCircleCount ++;
            } else if (curBoard[i][i] == GameCore.CROSS){
                leftCrossCount ++;
            } else {}

            if (curBoard[i][j] == GameCore.CIRCLE){
                rightCircleCount ++;
            } else if (curBoard[i][j] == GameCore.CROSS){
                rightCrossCount ++;
            } else {}

        }


        if ( leftCircleCount >= 3 || rightCircleCount >= 3){
            return GameCore.AI_WON;
        } else if (leftCrossCount >= 3 || rightCrossCount >= 3){
            return GameCore.PLAYER_WON;
        }

        if (isFull(curBoard)){
            return GameCore.DRAW;
        } else {
            return GameCore.IN_PROCESS;
        }

    }




    /**
     * For use in terminal -- left in for reference
     */
    public void readPlayerInput(){

        boolean moveMade = false;

        while (!moveMade){

            System.out.println("please input in the following format:    x y     ");
            String input = this.reader.nextLine();

            String[] inputList = input.split(" ");

            if (inputList.length == 2){

                if( 		inputList[0].length() == 1 && inputList[1].length() == 1
                        && 49 <= inputList[0].charAt(0) && inputList[0].charAt(0) <= 51
                        && 49 <= inputList[1].charAt(0) && inputList[1].charAt(0) <= 51){

                    Integer x = Integer.valueOf(inputList[0]) -1;
                    Integer y = Integer.valueOf(inputList[1]) -1;

                    moveMade = this.addToken(x, y, GameCore.CROSS);

                } else {}
            } else {}
        }
    }

//    /**
//     * <p> Android app version of readPlayerInput</p>
//     * Waits until player taps a button on screen and relays press GameCore after which
//     * the variable is reset.
//     */
//    public void waitForPlayer(){
//        synchronized (t) {
//            Log.i("TESTING WFP", "About to start wait");
//            while (!isPlayerDone) {
//                try {
//                    t.wait();
//                } catch (InterruptedException ex) {
//                    Log.e("TESTING WFP", "Interrupted... IDK why");
//                }
//            }
//            Log.i("TESTING WFP", "Player Done Moving");
//            isPlayerDone = false;
//        }
//    }


    public void IntelligentAIMoves(){
        System.out.println("AI making a intelligent decision");

        Spot move = GameCore.nextMove(this.board, true, 1);
        this.addToken(move.x, move.y, GameCore.CIRCLE);

        System.out.println("AI made it");


    }



    /**
     * An implementation of minimax algorithm
     * 		alternatively, every level returns the move that makes the best of its specified intention
     * 		(either makes AI win or player win)
     * 		More immediate win is preferred, so deeper routes are weighted down.
     *
     * @param curBoard
     * @param AIMove
     * @return
     */
    public static Spot nextMove(int[][] curBoard, boolean AIMove, int level){

        ArrayList<Spot> viableSpots = new ArrayList<Spot>();


        for (int i = 0; i < curBoard.length ; i++){
            for (int j = 0; j < curBoard[i].length; j++){

                // Iterate thru every possible spots
                if (curBoard[i][j] == GameCore.BLANK){

                    int[][] newBoard = new int[curBoard.length][curBoard[0].length];

                    for( int m = 0; m < curBoard.length; m++){
                        for( int n = 0; n < curBoard[m].length; n++){
                            newBoard[m][n] = curBoard[m][n];
                        }
                    }

                    if (AIMove)			newBoard[i][j] = GameCore.CIRCLE;
                    else 				newBoard[i][j] = GameCore.CROSS;

                    //					GameCore.printBoard(newBoard);


                    int diagResult = GameCore.inspectGameStatus(newBoard);
                    Spot newViableSpot = null;


                    if (diagResult == GameCore.DRAW){
                        newViableSpot = new Spot(j, i, 0);
                    } else if (diagResult ==  GameCore.IN_PROCESS){
                        newViableSpot = new Spot(j, i, GameCore.nextMove(newBoard, !AIMove, level +1).rating);
                    } else if (diagResult ==  GameCore.AI_WON){
                        newViableSpot = new Spot(j, i, 10 - level);
                    } else if (diagResult ==  GameCore.PLAYER_WON){
                        newViableSpot = new Spot(j, i, level - 10);
                    } else {}

                    //	System.out.println("putting in... " +newViableSpot.x+", "+newViableSpot.y+", "+newViableSpot.rating);

                    viableSpots.add(newViableSpot);
                } else {}

            }
        }


        if (AIMove){
            // Finds best outcome for AI.
            Spot bestSpot = viableSpots.get(0);


            for (int i = 1; i < viableSpots.size() ; i++){
                if (bestSpot.rating < viableSpots.get(i).rating ){
                    bestSpot = viableSpots.get(i);
                } else {}
            }

            //			for (int i = 0; i < viableSpots.size() ; i++){
            //				System.out.print( i + ", " + viableSpots.get(i).x+", "+viableSpots.get(i).y+", "+viableSpots.get(i).rating + " ; ");
            //
            //			}
            //			System.out.print("\n");
            //			System.out.println("size" + viableSpots.size());
            //
            //			System.out.println("best of level " + level +" is " + bestSpot.rating+". coord: "+ bestSpot.x +", "+bestSpot.y);

            return new Spot(bestSpot.x, bestSpot.y, bestSpot.rating);

        } else {
            // Finds worst outcome for AI.

            Spot bestSpot = viableSpots.get(0);

            for (int i = 1; i < viableSpots.size() ; i++){
                if (viableSpots.get(i).rating < bestSpot.rating){
                    bestSpot = viableSpots.get(i);
                } else {}
            }

            //			for (int i = 0; i < viableSpots.size() ; i++){
            //				System.out.print( i + ", " + viableSpots.get(i).x+", "+viableSpots.get(i).y+", "+viableSpots.get(i).rating + " ; ");
            //
            //			}
            //			System.out.print("\n");
            //			System.out.println("size" + viableSpots.size());
            //
            //			System.out.println("worst of level " + level +" is " + bestSpot.rating+". coord: "+ bestSpot.x +", "+bestSpot.y);

            return new Spot(bestSpot.x, bestSpot.y, bestSpot.rating);

        }
    }


    public static void main(String[] args ){
        int[][] ye = {{1,1,2},
                    {2,2,1},
                    {1,2,2}
        };

        GameCore.printBoard(ye);
        System.out.println(GameCore.inspectGameStatus(ye));

        GameCore testCore = new GameCore();

        testCore.MainProcedure();


    }
}