package game;

public class Line extends Bomb {
    int cutStatus = 0;
    int Number;
    int Status = 0;

    public void setCut(){
        cutStatus = 1;
    }

    public boolean isCut(){
        return cutStatus == 1;
    }

}
