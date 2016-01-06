package mj;

/**
 * Created by hdhamee on 1/6/16.
 */
public class Token {
    public int kind;		// token kind
    public int line;		// token line
    public int col;			// token column
    public int val;			// token value (for number and charConst)
    public String string;	// token string
}
