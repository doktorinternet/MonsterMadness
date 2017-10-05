package pat.actors;

import pat.function.Position;

public class Player extends Character{

    public Player(String representation, int speed, int movementLevel){
        super(representation, speed, movementLevel);
    }

    public Player(Position position){
        super(position);
    }


}
