
package frigo.dojo.bowling;

public class Bowling {

    private int[] rolls = new int[22];
    private int numberOfRolls;

    public void roll (int roll) {
        rolls[numberOfRolls++] = roll;
    }

    public int score () {
        int score = 0;
        int i = 0;
        for( int frame = 0; frame < 10; frame++ ){
            score += rolls[i];
            score += rolls[i + 1];
            if( isStrike(i) ){
                score += rolls[i + 2];
                i--;
            }else if( isSpare(i) ){
                score += rolls[i + 2];
            }
            i++;
            i++;
        }
        return score;
    }

    private boolean isStrike (int roll) {
        return rolls[roll] == 10;
    }

    private boolean isSpare (int roll) {
        if( isStrike(roll) ){
            return false;
        }
        return rolls[roll] + rolls[roll + 1] == 10;
    }

}
