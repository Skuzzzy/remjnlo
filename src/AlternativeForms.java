import tokens.LogToken;

import java.util.ArrayList;

/**
 * Created by dan on 2/6/15.
 */
public class AlternativeForms
{
    public static String getSumOfProductsString(String expression)
    {
        return getSumOfProductsString(Evaluator.getMinterms(expression),true);
    }
    public static String getSumOfProductsString(IndexList minterms, boolean explicitAnd)
    {
        ArrayList<String> variables = minterms.getVariables();

        String SoP = "";

        for(int term : minterms.getIndexes())
        {

            boolean[] line = termNumberToInputs(term,variables.size());

            if(SoP.length() > 0)
            {
                SoP += "+";
            }

            if(line[0] == true)
            {
                SoP += "(" + variables.get(0);
            }
            else
            {
                SoP += "(!" + variables.get(0);
            }

            for(int i=1;i<line.length;i++)
            {
                if(explicitAnd)
                {
                    SoP += "*";
                }
                if(line[i] == true)
                {
                    SoP += "" + variables.get(i);
                }
                else
                {
                    SoP +=  "!" + variables.get(i);
                }
            }

            SoP += ")";
        }
        return SoP;
    }

    private static boolean[] termNumberToInputs(int termNumber,int variableNumber)
    {
        boolean[] inputs = new boolean[variableNumber];
        String someInputs = Integer.toString(termNumber, 2);
        for(int i =0; i<someInputs.length(); i++)
        {
            inputs[inputs.length-1-i] = someInputs.charAt(someInputs.length()-1-i) == '1' ? true : false;
        }
        for(int i=0; i<inputs.length-someInputs.length();i++)
        {
            inputs[i] = false;
        }
        return inputs;
    }
}