package FrameState;

public class Frame {

    private FrameState current;
    private int[] numThrows;
    private int frameNumber;

    public Frame(int frameNumber) {
        this.current = new OpenFrame(this);
        this.frameNumber = frameNumber;

        if( frameNumber == 10){
            this.numThrows = new int[3];
        }else if(frameNumber > 0 && frameNumber < 10){
            this.numThrows = new int[2];
        }else{
            throw new Error("A game can only have frame numbers between 1 and 10.");
        }
    }

    public int getScore(Frame[] bowlerFrames){
        return this.current.getScore(bowlerFrames);
    }

    int[] getNumThrows(){
        return this.numThrows;
    }

    void setState(FrameState newState){
        this.current = newState;
    }

    int getFrameNumber(){
        return this.frameNumber;
    }
}
