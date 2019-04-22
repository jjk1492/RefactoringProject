package FrameState;

public class Spare implements FrameState {

    private Frame frame;

    Spare(Frame frame) {
        this.frame = frame;
    }

    @Override
    public int getScore(Frame[] frames) {

        int score = 0;

        if( this.frame.getFrameNumber() == 10){
            int[] numThrows = this.frame.getNumThrows();
            for( int pinsKnocked : numThrows){
                score += pinsKnocked;
            }
        }else {
            score = frames[this.frame.getFrameNumber()].getNumThrows()[0] + 10;
        }

        return score;

    }
}
