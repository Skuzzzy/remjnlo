public class Main
{

    public static void main(String[] args)
    {
	    String expression = "a1*b1+c";
        //boolean[] inputs = {false,false,false};
        //boolean result = Evaluator.evaluatePostfixExpression(Parser.convertToPostfix(Parser.parseString(expression)),inputs);
        //System.out.println(result);


        PrettyPrinting.printTruthTable(expression);

        /*
        for(boolean[] b : Evaluator.generateTruthTable(expression))
        {
            for(boolean a : b)
            {
                System.out.print(a+"\t");
            }
            System.out.println();
        }

        */
        IndexList minterms = Evaluator.getMinterms(expression);
        PrettyPrinting.printMinterms(minterms);
        IndexList maxterms = Evaluator.getMaxterms(expression);
        PrettyPrinting.printMaxterms(maxterms);

        /*
        System.out.println(AlternativeForms.getSumOfProductsString(minterms, true));
        //System.out.println(AlternativeForms.getSumOfProductsString(expression));



        System.out.println(AlternativeForms.getProductOfSumsString(maxterms, true));
        //System.out.println(AlternativeForms.getProductOfSumsString(expression));
        */

    }
}
