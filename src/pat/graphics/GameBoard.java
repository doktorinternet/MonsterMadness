package pat.graphics;

import java.nio.charset.Charset;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import pat.actors.Character;
import pat.actors.Monster;
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
    private static Position m1Position = new Position((xCells/10)*9, (yCells/10)*9); //Vänster-ner
    private static Position m2Position = new Position((xCells/10)*9, (yCells/10)); //Vänster -upp
    private static Position m3Position = new Position(xCells/10, yCells/10); //Höger -upp
    private static Position m4Position = new Position(xCells/10, (yCells/10)*9);//Höger ner


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

    static void drawCharacter(Character actor){
        Position position = actor.getPosition();
        terminal.moveCursor(position.x, position.y);
        if(actor.getRepresentation() != ' ') terminal.putCharacter(actor.getRepresentation());
        else {
            System.out.println("Error, no presentation in actor");
            System.exit(0);
        }
        terminal.moveCursor(0, 0);
    }

    public static void drawString(String s){
        int startX = (xCells/2)-4, startY = yCells/2;
        char [] gameOver = s.toCharArray();
        terminal.clearScreen();

        for(int i = 0; i < gameOver.length; i++){
            terminal.moveCursor(startX + i, startY);
            terminal.putCharacter(gameOver[i]);
        }
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
        drawCharacter(player);

        Monster m1 = new Monster (m1Position);
        drawCharacter(m1);
        Monster m2 = new Monster (m2Position);
        drawCharacter(m2);
        Monster m3 = new Monster (m3Position);
        drawCharacter(m3);
        Monster m4 = new Monster (m4Position);
        drawCharacter(m4);

        Monster[] monsters = new Monster[]{m1,m2,m3,m4};

        /*
            Använd nu detta metodanrop för att rita karaktären istället
            drawCharacter(monster eller player);
            kommer dock avsluta programmet ifall
        */
        while(true) {
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
            for (Monster m : monsters){
                m.move(player, terminal);
            }

            System.out.println(key.getCharacter());
        }
    }
}