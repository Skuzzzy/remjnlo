public class Main
{

    public static void main(String[] args)
    {
	    String expression = "a+a2";
        //boolean[] inputs = {false,false,false};
        //boolean result = Evaluator.evaluatePostfixExpression(Parser.convertToPostfix(Parser.parseString(expression)),inputs);
        //System.out.println(result);
        IndexList maxterms = Evaluator.getMaxterms(expression);
        for(int i : maxterms.getIndexes())
        {
            System.out.println(i);
        }
    }
}
