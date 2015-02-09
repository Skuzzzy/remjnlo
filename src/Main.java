public class Main
{

    public static void main(String[] args)
    {
	    String expression = "a+b+c";
        //boolean[] inputs = {false,false,false};
        //boolean result = Evaluator.evaluatePostfixExpression(Parser.convertToPostfix(Parser.parseString(expression)),inputs);
        //System.out.println(result);
        IndexList minterms = Evaluator.getMinterms(expression);

        System.out.println(AlternativeForms.getSumOfProductsString(minterms, true));
        //System.out.println(AlternativeForms.getSumOfProductsString(expression));

        IndexList maxterms = Evaluator.getMaxterms(expression);

        System.out.println(AlternativeForms.getProductOfSumsString(maxterms, true));
        //System.out.println(AlternativeForms.getProductOfSumsString(expression));


    }
}
