package FrameState;


public class OpenFrame implements FrameState {

    private Frame frame;

    OpenFrame(Frame frame){
        this.frame = frame;
    }


    @Override
    public int getScore(Frame[] frames) {
        // Calculate the score
        int[] ballThrows = this.frame.getNumThrows();
        int score = ballThrows[0] + ballThrows[1];

        // If the player knocked every pin down this throw change state
        // so the next throw is added to the total.
        if( ballThrows[0] == 10){
            this.frame.setState(new Strike(this.frame));
            return this.frame.getScore(frames);
        }
        if( score == 10 ){
            this.frame.setState(new Spare(this.frame));
            return this.frame.getScore(frames);
        }

        return score;
    }
}
