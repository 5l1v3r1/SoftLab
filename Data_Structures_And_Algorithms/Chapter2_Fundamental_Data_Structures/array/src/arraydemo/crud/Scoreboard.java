package arraydemo.crud;

import java.util.Arrays;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Scoreboard {
    private int numEntries = 0;
    private GameEntry[] board = null;

    public Scoreboard(int capacity) {
        this.board = new GameEntry[capacity];
    }

    public void add(GameEntry e){
        int newScore = e.getScore();
        if (numEntries < board.length || newScore > board[numEntries-1].getScore()){
            if (numEntries < board.length) {
                numEntries++;
            }
            int j = numEntries - 1;
            while (j > 0 && newScore > board[j-1].getScore()){
                board[j] = board[j-1];
                j--;
            }
            board[j] = e;
        }
    }

    public GameEntry remove(int j) {
        if (j < 0 || j >= numEntries){
            return null;
        }
        int i = j;
        GameEntry e = board[i];
        while (i < numEntries-1){
            board[i] = board[i+1];
            i++;
        }
        board[numEntries-1] = null;
        numEntries--;
        return  e;
    }


    @Override
    public String toString() {
        return "Scoreboard{" +
                "numEntries=" + numEntries +
                ", board=" + Arrays.toString(board) +
                '}';
    }
}
