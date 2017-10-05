package pat.actors;

import com.googlecode.lanterna.terminal.Terminal;
import pat.function.Position;

public class Character {

    private Position position;
    private char representation;

    private int speed;
    private int movementLevel;

    public final static int NORTH = 0, WEST = 1, SOUTH = 2, EAST = 3;

    Character(char representation, int speed, int movementLevel){
        this.representation = representation;
        this.speed = speed;
        this.movementLevel = movementLevel;
    }

    Character(Position position){
        representation = ' ';
        this.position = position;
    }

    Character(Position position, char representation){
        this.representation = representation;
        this.position = position;
    }

    public Position hasPosition(){
        return position;
    }

    public char getRepresentation(){
        return representation;
    }

    public void move(int direction, Terminal terminal){

        switch(direction){
            case NORTH:
                moveNorth(terminal);
                break;
            case WEST:
                moveWest(terminal);
                break;
            case SOUTH:
                moveSouth(terminal);
                break;
            case EAST:
                moveEast(terminal);
                break;
        }

    }

    private void moveNorth(Terminal terminal){
        // draw next step
        terminal.moveCursor(position.x, position.y-1);
        terminal.putCharacter('O');
        terminal.moveCursor(0,0);

        // remove last position
        removeLastPosition(terminal);

        //  set new position
        position = new Position(position.x, position.y-1);
    }

    private void moveWest(Terminal terminal){
        terminal.moveCursor(position.x-1, position.y);
        terminal.putCharacter('O');
        terminal.moveCursor(0,0);

        // remove last position
        removeLastPosition(terminal);
        position = new Position(position.x-1, position.y);
    }
    private void moveSouth(Terminal terminal){
        terminal.moveCursor(position.x, position.y+1);
        terminal.putCharacter('O');
        terminal.moveCursor(0,0);

        removeLastPosition(terminal);

        position = new Position(position.x, position.y+1);
    }
    private void moveEast(Terminal terminal){
        terminal.moveCursor(position.x+1, position.y);
        terminal.putCharacter('O');
        terminal.moveCursor(0,0);

        removeLastPosition(terminal);

        position = new Position(position.x+1, position.y);
    }

    private void removeLastPosition(Terminal terminal){
        // remove last position
        terminal.moveCursor(position.x, position.y);
        terminal.putCharacter(' ');
        terminal.moveCursor(0,0);

    }

}
