package at.martin;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by martin on 05.08.16.
 */
public class Game  {
    private char [][] field;
    public int playerRow = 1;
    public int playerCol = 1;
    public final char WALL = '#';
    private boolean run = true;
    public final char FINISH = 'X';
    public final char EMPTY = ' ';
    public final int MOVE_LEFT = 0;
    public  boolean GAME_WON = false;
    public final int MOVE_RIGHT = 1;
    public final int MOVE_UP = 2;
    public final int MOVE_DOWN = 3;
    public final char PLAYER_LEFT = '<';
    public final char PLAYER_RIGHT = '>';
    public final char PLAYER_UP = '^';
    public final char PLAYER_DOWN = 'v';
    private  HashMap<Character,Integer> MOVE_MAP = new HashMap<>();

    public char[][] getField(){
        return this.field;
    }
    public Game () throws IOException {
        MazeGenerator mazeGenerator = new MazeGenerator(20,20);
        String map = "#####################################\n" +
                "# #       #       #     #         # #\n" +
                "# # ##### # ### ##### ### ### ### # #\n" +
                "#       #   # #     #     # # #   # #\n" +
                "##### # ##### ##### ### # # # ##### #\n" +
                "#   # #       #     # # # # #     # #\n" +
                "# # ####### # # ##### ### # ##### # #\n" +
                "# #       # # #   #     #     #   # #\n" +
                "# ####### ### ### # ### ##### # ### #\n" +
                "#     #   # #   # #   #     # #     #\n" +
                "# ### ### # ### # ##### # # # #######\n" +
                "#   #   # # #   #   #   # # #   #   #\n" +
                "####### # # # ##### # ### # ### ### #\n" +
                "#     # #     #   # #   # #   #     #\n" +
                "# ### # ##### ### # ### ### ####### #\n" +
                "# #   #     #     #   # # #       # #\n" +
                "# # ##### # ### ##### # # ####### # #\n" +
                "# #     # # # # #     #       # #   #\n" +
                "# ##### # # # ### ##### ##### # #####\n" +
                "# #   # # #     #     # #   #       #\n" +
                "# # ### ### ### ##### ### # ##### # #\n" +
                "# #         #     #       #       # #\n" +
                "#X###################################";
        String [] mapRows = map.split("\\n");
        field = new char[mapRows.length][mapRows[0].length()];
        for (int i = 0; i < mapRows.length;i++){
            for (int j = 0; j < mapRows[i].length();j++){
                field[i][j] = mapRows[i].charAt(j);
            }
        }

        MOVE_MAP.put('a',MOVE_LEFT);
        MOVE_MAP.put('d',MOVE_RIGHT);
        MOVE_MAP.put('s',MOVE_DOWN);
        MOVE_MAP.put('w',MOVE_UP);
     //  field = mazeGenerator.mazeToArray();
        positionPlayer();


    }




    public void printField () throws IOException {
        Runtime.getRuntime().exec("clear");
        for (int i = 0; i < field.length;i++ ){
            for (int j = 0; j < field[i].length;j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    private void positionPlayer(){
        field[1][1] = PLAYER_LEFT;
    }

    private void positionPlayer(int row, int col, char direction){
        if (field[row][col] == FINISH){
            GAME_WON = true;
        }
        field[playerRow][playerCol] = EMPTY;
        field[row][col] = direction;
        playerRow = row;
        playerCol = col;
    }


    public void move (char direction){
        switch (MOVE_MAP.get(direction)){
            case MOVE_LEFT:
                if (field[playerRow][playerCol] != PLAYER_LEFT){
                    positionPlayer(playerRow,playerCol,PLAYER_LEFT);
                } else if (playerCol-1 >= 0 && field[playerRow][playerCol-1] != WALL){
                    positionPlayer(playerRow,playerCol-1,PLAYER_LEFT);
                }
                break;
            case MOVE_RIGHT:
                if(field[playerRow][playerCol] != PLAYER_RIGHT){
                    positionPlayer(playerRow,playerCol,PLAYER_RIGHT);
                } else if(playerCol+1 < field[0].length && field[playerRow][playerCol+1] != WALL){
                    positionPlayer(playerRow,playerCol+1,PLAYER_RIGHT);
                }
                break;
            case MOVE_DOWN:
                if (field[playerRow][playerCol] != PLAYER_DOWN){
                    positionPlayer(playerRow,playerCol,PLAYER_DOWN);
                } else if (playerRow+1 < field.length && field[playerRow+1][playerCol] != WALL){
                    positionPlayer(playerRow+1,playerCol,PLAYER_DOWN);
                }
                break;
            case MOVE_UP:
                if(field[playerRow][playerCol] != PLAYER_UP){
                    positionPlayer(playerRow,playerCol,PLAYER_UP);
                } else if (playerRow-1 >= 0 && field[playerRow-1][playerCol] != WALL){
                    positionPlayer(playerRow-1,playerCol,PLAYER_UP);
                }
                break;

        }
    }
}
