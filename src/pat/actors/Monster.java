package pat.actors;

import pat.function.Position;

public class Monster extends Character{

    public Monster(String representation, int speed, int movementLevel){
        super(representation, speed, movementLevel);
    }

    public Monster(Position position){
        super(position);
    }

}
