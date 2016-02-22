package mj;

/**
 * Created by hdhamee on 1/6/16.
 */
public class Token {
    public int kind;		// token code
    public int line;		// token line (for error messages)
    public int col;			// token column (for error messages)
    public int val;			// token value (for number and charConst)
    public String string;	// token string
}
