public class Main
{

    public static void main(String[] args)
    {
	    String expression = "a*!c(x(!a*c))";

        PrettyPrinting.printMinterms(Evaluator.getMinterms(expression));
        PrettyPrinting.printMaxterms(Evaluator.getMaxterms(expression));

        System.out.println();

        System.out.println(expression);
        System.out.println(AlternativeForms.getProductOfSumsString(expression));
        System.out.println(AlternativeForms.getSumOfProductsString(expression));

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


    }
}
