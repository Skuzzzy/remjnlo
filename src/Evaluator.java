import tokens.LogToken;
import tokens.Operator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by dan on 2/5/15.
 */
public class Evaluator
{
    public static boolean evaluateStringExpression(String expression, final boolean[] inputs)
    {
        return Evaluator.evaluatePostfixExpression(Parser.convertToPostfix(Parser.parseString(expression)),inputs);
    }

    public static boolean evaluatePostfixExpression(ArrayList<LogToken> expression, final boolean[] inputs)
    {
        // Note, if inputs and orderedVariables have different lengths we are going to have a problem
        final ArrayList<String> orderedVariables = getLexographicalOrderOfVariables(expression);

        Stack<Boolean> evalStack = new Stack<Boolean>();

        for(LogToken token : expression)
        {
            if(token.getTokenType().equals("VariableToken"))
            {
                int index = orderedVariables.indexOf(token.toString());
                evalStack.add(inputs[index]);
            }
            else
            {
                int stackConsumption = ((Operator)token).expectedParameters();
                boolean[] fromStack = new boolean[stackConsumption];
                while(stackConsumption>0)
                {
                    fromStack[stackConsumption-1] = evalStack.pop();
                    --stackConsumption;
                }
                evalStack.add(((Operator)token).evaluate(fromStack));
            }
        }

        //The last thing on the stack will be the solution
        return evalStack.pop();
    }

    public static ArrayList<String> getLexographicalOrderOfVariables(String expression)
    {
        return Evaluator.getLexographicalOrderOfVariables(Parser.convertToPostfix(Parser.parseString(expression)));
    }
    public static ArrayList<String> getLexographicalOrderOfVariables(ArrayList<LogToken> expression)
    {
        ArrayList<String> orderedVariableNames = new ArrayList<String>();
        for(LogToken token : expression)
        {
            if(token.getTokenType().equals("VariableToken"))
            {
                if(!orderedVariableNames.contains(token.toString()))
                {
                    orderedVariableNames.add(token.toString());
                }
            }
        }

        Collections.sort(orderedVariableNames);

        return orderedVariableNames;
    }

    public static ArrayList<boolean[]> generateTruthTable(String expression)
    {
        return Evaluator.generateTruthTable(Parser.convertToPostfix(Parser.parseString(expression)));
    }

    public static ArrayList<boolean[]> generateTruthTable(ArrayList<LogToken> postfixExpression)
    {
        ArrayList<boolean[]> truthTable = new ArrayList<boolean[]>();

        ArrayList<String> variables =  getLexographicalOrderOfVariables(postfixExpression);
        int numVars = variables.size();
        boolean inputs[] = new boolean[numVars];

        for(int i=0;i<inputs.length;i++)
        {
            inputs[i] = true; //Whatever the inputs are set to here will be flipped
        }

        for(int vertPos=0;vertPos<Math.pow(2,numVars);vertPos++)
        {
            for(int horzPos=0;horzPos<numVars;horzPos++)
            {
                // Magic if statement! Do not touch!
                if(vertPos%(Math.pow(2,numVars)/Math.pow(2,horzPos+1)) == 0) // This works I swear
                {
                    inputs[horzPos] = !inputs[horzPos];
                }
            }
            boolean[] truthTableLine = new boolean[numVars+1];
            for(int i=0;i<inputs.length;i++)
            {
                truthTableLine[i] = inputs[i];
            }
            truthTableLine[truthTableLine.length-1] = Evaluator.evaluatePostfixExpression(postfixExpression,inputs);
            truthTable.add(truthTableLine);
        }
        return truthTable;
    }

    public static IndexList getMinterms(String expression)
    {
        ArrayList<boolean[]> truthTable = Evaluator.generateTruthTable(expression);

        int requiredSize = 0;
        // Determine how many terms there are
        for(int i = 0;i<truthTable.size();i++)
        {
            if(truthTable.get(i)[truthTable.get(i).length-1])
            {
                requiredSize++;
            }
        }

        int[] minterms = new int[requiredSize];
        int mintermsIndex = 0;

        // Fill the array with their indexes
        for(int i = 0;i<truthTable.size();i++)
        {
            if(truthTable.get(i)[truthTable.get(i).length-1])
            {
                minterms[mintermsIndex] = i;
                mintermsIndex++;
            }
        }

        return new IndexList(expression,minterms);
    }
    public static IndexList getMaxterms(String expression)
    {
        ArrayList<boolean[]> truthTable = Evaluator.generateTruthTable(expression);

        // Determine how many terms there are
        int requiredSize = 0;
        for(int i = 0;i<truthTable.size();i++)
        {
            if(!truthTable.get(i)[truthTable.get(i).length-1])
            {
                requiredSize++;
            }
        }

        int[] maxterms = new int[requiredSize];
        int maxtermsIndex = 0;

        // Fill the array with their indexes
        for(int i = 0;i<truthTable.size();i++)
        {
            if(!truthTable.get(i)[truthTable.get(i).length-1])
            {
                maxterms[maxtermsIndex] = i;
                maxtermsIndex++;
            }
        }

        return new IndexList(expression,maxterms);
    }

}