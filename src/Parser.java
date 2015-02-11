import tokens.*;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by dan on 2/5/15.
 */
public class Parser
{
    public static ArrayList<LogToken> parseString(String expression)
    {
        boolean implicitAnd = false;

        int position = 0;
        int exprlen = expression.length();
        ArrayList<LogToken> tokenizedExpression = new ArrayList<LogToken>();

        while(position < exprlen)
        {
            char currentChar = expression.charAt(position);
            if(Parser.isOr(currentChar))
            {
                implicitAnd = false;

                tokenizedExpression.add(new OrToken());
            }
            else if(Parser.isAnd(currentChar))
            {
                implicitAnd = false;

                tokenizedExpression.add(new AndToken());
            }
            else if(Parser.isNot(currentChar))
            {
                if(implicitAnd)
                {
                    tokenizedExpression.add(new AndToken());
                    implicitAnd = false;
                }
                tokenizedExpression.add(new NotToken());
            }
            else if(Parser.isLeftParenthesis(currentChar))
            {
                if(implicitAnd)
                {
                    tokenizedExpression.add(new AndToken());
                    implicitAnd = false;
                }
                tokenizedExpression.add(new LeftParenthesisToken());
            }
            else if(Parser.isRightParenthesis(currentChar))
            {
                implicitAnd = true;

                tokenizedExpression.add(new RightParenthesisToken());
            }
            else if(Character.isAlphabetic(currentChar))
            {
                if(implicitAnd)
                {
                    tokenizedExpression.add(new AndToken());
                }
                implicitAnd = true;

                String variable = "" + currentChar;

                ++position;
                while(position < exprlen && isValidPartOfVariable(currentChar) || currentChar == ' ')
                {
                    currentChar = expression.charAt(position);
                    if(currentChar == ' ')
                    {
                        // Ignore Spaces
                        position++;
                    }
                    else if(isValidPartOfVariable(currentChar))
                    {
                        variable += currentChar;
                        position++;
                    }
                    else // Not a valid part of the variable
                    {
                        position--; // Decrement to prepare for the increment at the end of the current parse loop
                    }

                }
                tokenizedExpression.add(new VariableToken(variable));
            }
            else if(currentChar == ' ')
            {
                // Ignore spaces
            }
            else
            {
                System.out.println("Unknown character " + currentChar + ". Skipping it");
            }
            ++position;
        }

        for(LogToken c : tokenizedExpression)
        {
            System.out.print(c.toString()+" ");
        }
        System.out.println();

        return tokenizedExpression;
    }

    public static ArrayList<LogToken> convertToPostfix(ArrayList<LogToken> infixExpression)
    {
        ArrayList<LogToken> postfixExpression = new ArrayList<LogToken>();
        Stack<LogToken> operatorStack = new Stack<LogToken>();

        for(LogToken currentToken : infixExpression)
        {
            String currentTokenType = currentToken.getTokenType();
            if(currentTokenType.equals("OrToken") || currentTokenType.equals("AndToken") || currentTokenType.equals("NotToken"))
            {
                //While stack is not empty
                //While the thing on top of the stack is not a LeftParenthesisToken
                //While the operator on the top of the stack has a higher or equal precedence
                while((operatorStack.size() > 0) && !operatorStack.peek().getTokenType().equals("LeftParenthesisToken") && ((Operator)operatorStack.peek()).getOperatorPrecedence() <= ((Operator)currentToken).getOperatorPrecedence())
                {
                    postfixExpression.add(operatorStack.pop());
                }
                operatorStack.add(currentToken);

            }
            else if(currentTokenType.equals("LeftParenthesisToken"))
            {
                operatorStack.add(currentToken);
            }
            else if(currentTokenType.equals("RightParenthesisToken"))
            {
                while(operatorStack.size() > 0 && !(operatorStack.peek()).getTokenType().equals("LeftParenthesisToken"))
                {
                    postfixExpression.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
            else if(currentTokenType.equals("VariableToken"))
            {
                postfixExpression.add(currentToken);
            }
            else
            {
                System.out.println("Unknown token type " + currentTokenType + ". Skipping it");
            }
        }

        while(operatorStack.size() > 0)
        {
            postfixExpression.add(operatorStack.pop());
        }

        return postfixExpression;
    }

    private static boolean isValidPartOfVariable(char c)
    {
        return (Character.isAlphabetic(c) || Character.isDigit(c));
    }
    private static boolean isOr(char c)
    {
        return (c == '+');
    }
    private static boolean isAnd(char c)
    {
        return (c == '*');
    }
    private static boolean isNot(char c)
    {
        return (c == '!');
    }
    private static boolean isLeftParenthesis(char c)
    {
        return (c == '(');
    }
    private static boolean isRightParenthesis(char c)
    {
        return (c == ')');
    }
}
