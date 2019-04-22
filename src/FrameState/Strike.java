package FrameState;

public class Strike implements FrameState{
    private Frame frame;

    public Strike(Frame frame) {
        this.frame = frame;
    }

    @Override
    public int getScore(Frame[] frames) {
        int score = 0;
        if( this.frame.getFrameNumber() == 10 ){
            int[] numThrows = this.frame.getNumThrows();
            for( int pinsKnocked : numThrows ){
                if(pinsKnocked == -1){
                    score = -1;
                    System.out.println("Frame not finished");
                    return score;
                }
                score += pinsKnocked;
            }
        }else{
            score = 10;
            Frame secondFrame = frames[this.frame.getFrameNumber()];
            int[] secondThrow = secondFrame.getNumThrows();

            if( secondFrame.getFrameNumber() == 10){
                score += secondThrow[1] + secondThrow[2];
            }else {
                score += secondThrow[0];
                if(score == 20){
                    Frame thirdFrame = frames[this.frame.getFrameNumber()+1];
                    int thirdThrow = thirdFrame.getNumThrows()[0];
                    score += thirdThrow;
                }else {
                    score += secondThrow[1];
                }
            }
        }
        return score;
    }
}
