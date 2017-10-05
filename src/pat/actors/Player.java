package pat.actors;

import pat.function.Position;

public class Player extends Character{

    public Player(char representation, int speed, int movementLevel){
        super(representation, speed, movementLevel);
    }

    public Player(Position position){
        super(position, 'O');
    }

    public Player(Position position, char representation){
        super(position, representation);
    }

}
