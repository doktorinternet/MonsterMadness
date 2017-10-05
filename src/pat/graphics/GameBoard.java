package pat.graphics;

import java.nio.charset.Charset;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import pat.actors.Character;
import pat.actors.Player;
import pat.function.Position;

public class GameBoard {

    //private static Random rand = new Random();

    // sätt storlek på array, detta täckte min skärm. Delay är hur ofta den ska rita om, håll inne valfri tangent för

    // att köra. Hittade ingen metod för att sätta terminalens fönsterstorlek, men går att maximera under körning
    private static Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));

    private static int xCells = terminal.getTerminalSize().getColumns(), yCells = terminal.getTerminalSize().getRows();
    private static int delay = 10;
    //private static int[][] board = new int[xCells][yCells];
    private static final int WHITE = 0;
    private static Position playerPosition = new Position(xCells/2, yCells/2);

    public static void run() { // Gör till main
        terminal.enterPrivateMode();
        drawTerminal();
        drawFrame();
        terminal.setCursorVisible(false);
    }

    private static void drawFrame(){
        int x = 0;
        int y = 0;

        drawHorizontal(x,y);
        drawHorizontal(x,yCells);
        drawVertical(x,y);
        drawVertical(xCells,y);
    }

    static void drawHorizontal(int x, int y){
        for (; x < xCells; x++ ){
            paint(x,y,WHITE);
        }
    }

    static void drawVertical(int x, int y){
        for (; y < yCells; y++){
            paint(x,y,WHITE);
            paint(x-1,y,WHITE);
        }
    }

    static void drawCharacter(int x, int y){
        terminal.moveCursor(x, y);
        terminal.putCharacter('O');
        //paint(x, y, WHITE);
        terminal.moveCursor(0, 0);


    }

    private static void paint(int x, int y, int color){
        terminal.moveCursor(x,y);
        switch (color){
            case WHITE:
                terminal.applyBackgroundColor(Terminal.Color.WHITE);
                break;

            default:
                terminal.applyBackgroundColor(color);
        }
        terminal.putCharacter('\u2588');
    }



    private static void drawTerminal(){
        Player player = new Player(playerPosition);
        drawCharacter(xCells/2, yCells/2);
        //terminal.clearScreen();
        while(true) {

            //drawGrid();

            // Formula to take the average value of the current cell
            // and all of its neighbors

            //Wait for a key to be pressed

            Key key;
            do{
                try{
                    Thread.sleep(delay);
                } catch (InterruptedException i){
                    System.out.println("Error in waiting for keypress" + i);
                    System.exit(0);
                }
                key = terminal.readInput();

            } while(key == null);

            switch(key.getKind()) {

                case ArrowDown:
                    player.move(Character.SOUTH, terminal);
                    break;

                case ArrowUp:
                    player.move(Character.NORTH, terminal);
                    break;

                case ArrowLeft:
                    player.move(Character.WEST, terminal);
                    break;

                case ArrowRight:
                    player.move(Character.EAST, terminal);
                    break;

                case Escape:
                    terminal.clearScreen();
                    break;

                default:

                    System.out.println("No key bound");

            }

            System.out.println(key.getCharacter());
        }
    }
}

/*
    private static void drawGrid() {

        for(int i = 0; i<(xCells*2); i++){
            int x = rand.nextInt(xCells);
            int y = rand.nextInt(yCells);
            board[x][y] = rand.nextInt(256);
        }

        for (int xAxis = 0; xAxis < xCells; xAxis++) {
            for (int yAxis = 0; yAxis < yCells; yAxis++) {
                terminal.moveCursor(xAxis,yAxis);
                terminal.applyForegroundColor(board[xAxis][yAxis]);
                terminal.putCharacter('\u2588');
            }
        }
    }

    public static void m(){

     [][] tmpboard = new int[xCells][yCells];
            for (int y = 1; y < yCells-1; y++) {
        for (int x = 1; x < xCells-1; x++) {
        int color = 0;
                        color += board[x - 1][y - 1]; //
                       color += board[x + 1][y - 1];
                        color += board[x ][y - 1];
                        color += board[x - 1][y];
        color += board[x ][y];
                       color += board[x + 1][y];
                        color += board[x ][y + 1];
                        color += board[x - 1][y + 1];
                        color += board[x + 1][y + 1];
        if(color>0) color /= 9;
        tmpboard[x][y] = color;
        }
        }
        board = tmpboard;

    }*/