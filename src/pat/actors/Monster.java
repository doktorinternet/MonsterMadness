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

    /*void setPosition(int x){
        this.getPosition().x = x;
    }

    void setPosition(int y){
        this.getPosition().y = y;
    }*/

    public void move(Position player, Terminal terminal){
        Position monster = getPosition();
        Position oldMonster = new Position(monster);
        if(player.x > monster.x)
            monster.x += 1;
        else monster.x -= 1;

        if(player.y > monster.y)
            monster.y += 1;
        else monster.y -= 1;

        terminal.moveCursor(monster.x, monster.y);
        terminal.putCharacter(getRepresentation());
        terminal.moveCursor(0,0);

        // remove last position
        removeLastPosition(terminal);

        //  set new position
        //position = new Position(position.x, position.y-1);

    }

}
