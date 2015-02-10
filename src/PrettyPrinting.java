import java.util.ArrayList;

/**
 * Created by dan on 2/10/15.
 */
public class PrettyPrinting
{
    public static void printTruthTable(String expression)
    {
        ArrayList<boolean[]> truthTable = Evaluator.generateTruthTable(expression);
        ArrayList<String> variables = Evaluator.getLexographicalOrderOfVariables(expression);

        System.out.print("#\t");
        for(int i=0;i<variables.size();i++)
        {
            System.out.print(variables.get(i) + "\t\t");
        }
        System.out.println("result");

        for(int i=0;i<truthTable.size();i++)
        {
            System.out.print(i+"\t");
            for(int j=0;j<truthTable.get(i).length;j++)
            {
                System.out.print(truthTable.get(i)[j]+"\t");
            }
            System.out.println();
        }
    }

    public static void printMinterms(IndexList minterms)
    {
        System.out.print("f(");
        for(int i=0;i<minterms.getVariables().size();i++)
        {
            if(i!=0)
            {
                System.out.print(",");
            }
            System.out.print(minterms.getVariables().get(i));
        }
        System.out.print(") = Σm(");
        for(int i=0;i<minterms.getIndexes().length;i++)
        {
            if(i!=0)
            {
                System.out.print(",");
            }
            System.out.print(minterms.getIndexes()[i]);
        }
        System.out.println(")");
    }

    public static void printMaxterms(IndexList maxterms)
    {
        System.out.print("f(");
        for(int i=0;i<maxterms.getVariables().size();i++)
        {
            if(i!=0)
            {
                System.out.print(",");
            }
            System.out.print(maxterms.getVariables().get(i));
        }
        System.out.print(") = ∏M(");
        for(int i=0;i<maxterms.getIndexes().length;i++)
        {
            if(i!=0)
            {
                System.out.print(",");
            }
            System.out.print(maxterms.getIndexes()[i]);
        }
        System.out.println(")");
    }

}
