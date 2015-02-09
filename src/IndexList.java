import tokens.LogToken;

import java.util.ArrayList;

/**
 * Created by dan on 2/9/15.
 */
public class IndexList
{
    private int[] indexes;
    private ArrayList<String> variables;

    public IndexList(String expression,int[] indexes)
    {
        variables = Evaluator.getLexographicalOrderOfVariables(expression);
        this.indexes = indexes;
    }

    public IndexList(ArrayList<String> variables, int[] indexes)
    {
        this.variables = variables;
        this.indexes = indexes;
    }

    public int[] getIndexes()
    {
        return indexes;
    }

    public IndexList getOppositeIndexList()
    {
        int[] oppositeIndexes = new int[(int)Math.pow(variables.size(),2)-indexes.length];
        int currentIndex = 0;

        for(int i = 0; i<oppositeIndexes.length ; i++)
        {
            if(!containsIndex(i))
            {
                oppositeIndexes[currentIndex] = i;
                currentIndex++;
            }
        }

        return new IndexList(variables,oppositeIndexes);
    }

    public boolean containsIndex(int i)
    {
        for(int j=0; j<indexes.length; j++)
        {
            if(indexes[j] == i)
            {
                return true;
            }
        }
        return false;
    }
}
