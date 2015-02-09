package tokens;

/**
 * Created by dan on 2/5/15.
 */
public class VariableToken implements LogToken
{
    String s;

    public VariableToken(String s)
    {
        this.s = s;
    }

    public String getTokenType()
    {
        return "VariableToken";
    }

    public String toString()
    {
        return s;
    }
}
