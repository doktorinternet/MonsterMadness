package pat.actors;

import com.googlecode.lanterna.terminal.Terminal;
import pat.function.Position;

public class Monster extends Character{

    public Monster(char representation, int speed, int movementLevel){
        super(representation, speed, movementLevel);
    }

    public Monster(Position position){
        super(position, '\u26BD');
    }

    public Monster(Position position, char representation){
        super(position, representation);
    }

    void setPosition(int x, int y){
        position.x = x;
        position.y = y;
    }

    public void move(Player player, Terminal terminal){
        Position playerPosition = player.getPosition();
        Position monster = getPosition();
        int x = monster.x, y = monster.y;

        if(playerPosition.x > monster.x){
            x += 1;
        }
        else if(playerPosition.x < monster.x) {
            x -= 1;
        }
        else if(playerPosition.x == monster.x){}

        if(playerPosition.y > monster.y){
            y += 1;
        }
        else if(playerPosition.y < monster.y) {
            y -= 1;
        }
        else if(playerPosition.y == monster.y){}

        terminal.moveCursor(x, y);
        terminal.putCharacter(getRepresentation());
        terminal.moveCursor(0,0);

        // remove last position
        removeLastPosition(terminal);

        setPosition(x, y);
        if(getPosition().x == playerPosition.x && getPosition().y == playerPosition.y){
            terminal.clearScreen();
            player.dies();
        }
    }
}
