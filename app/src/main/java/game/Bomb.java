package game;

public class Bomb {

    int Time;
    int BoomStatus = 0;


    public void setTime(int i){
        Time = i;
    }

    public void countDown() throws InterruptedException{
        for(int i = 30; i >= 0; i++) {
            Thread.sleep(1000);
            setTime(i);
        }
        Thread.sleep(1000);
    }

    public int getTime(){
        return Time;
    }

    public void setBoom(){
        BoomStatus = 1;
    }

    public boolean isBoom(){
        return BoomStatus == 1;
    }
}

