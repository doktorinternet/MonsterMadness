package pat.function;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(){}

    public Position(Position position){
        x = position.x;
        y = position.y;

    }
}
