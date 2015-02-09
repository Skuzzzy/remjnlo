public class Main
{

    public static void main(String[] args)
    {
	    String expression = "a+b";
        //boolean[] inputs = {false,false,false};
        //boolean result = Evaluator.evaluatePostfixExpression(Parser.convertToPostfix(Parser.parseString(expression)),inputs);
        //System.out.println(result);
        IndexList minterms = Evaluator.getMinterms(expression);
        for(int i : minterms.getIndexes())
        {
            System.out.println(i);
        }
        System.out.println(AlternativeForms.getSumOfProductsString(minterms,false));
        System.out.println(AlternativeForms.getSumOfProductsString(expression));

    }
}
