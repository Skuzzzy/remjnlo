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



    public static String getProductOfSumsString(String expression)
    {
        return getProductOfSumsString(Evaluator.getMaxterms(expression),true);
    }
    public static String getProductOfSumsString(IndexList maxterms, boolean explicitAnd)
    {
        ArrayList<String> variables = maxterms.getVariables();

        String PoS = "";

        for(int term : maxterms.getIndexes())
        {
            boolean[] line = termNumberToInputs(term,variables.size());

            if(PoS.length() > 0 && explicitAnd)
            {
                PoS += "*";
            }

            if(line[0] == false)
            {
                PoS += "(" + variables.get(0);
            }
            else
            {
                PoS += "(!" + variables.get(0);
            }

            for(int i=1;i<line.length;i++)
            {

                PoS += "+";

                if(line[i] == false)
                {
                    PoS += "" + variables.get(i);
                }
                else
                {
                    PoS +=  "!" + variables.get(i);
                }
            }
            PoS += ")";
        }

        return PoS;
    }



    private static boolean[] termNumberToInputs(int termNumber,int numberOfVariables)
    {
        boolean[] inputs = new boolean[numberOfVariables];
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