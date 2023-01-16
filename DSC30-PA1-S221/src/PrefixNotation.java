/*
    Name: Zehui Zhang
    PID:  A16151490
 */

/**
 * Prefix notation is a mathematical notation that places the operator before the two operands.
 * @author Zehui Zhang
 * @since  08/06/2021
 */
public class PrefixNotation {

    /**
     * check and recognize different operators
     * @param c a string contain operators
     * @return Boolean
     */
    static Boolean checkOperator(String c) {
        // check operator
        String [] operator = {"+", "-", "*", "/"};

        for (String str : operator)
        {
            if (c.equals(str))
            {
                return false;
            }
        }
        return true;

    }

    /**
     * Given a mathematical expression’s valid prefix notation as string where adjacent
     * operators/operands are separated by a single space,
     * evaluate the result of this expression.
     * @param notation the string contain numbers and operators
     * @return the mathematical result of the expression
     */
    public static int evaluate(String notation) {
        // By using the intstack we creater, create a brand new stack
        // Magic number
        IntStack Stack = new IntStack(notation.length(), 0.8, 0.2);
        String [] arrNotation = notation.split("\\s");

        for (int j = arrNotation.length - 1; j >= 0; j--) {

            if (checkOperator(arrNotation[j]))
                Stack.push(Integer.parseInt(arrNotation[j]));// push the character to the stack

            else {
                // only consider two numbers here
                int s1 = Stack.peek();
                Stack.pop();
                int s2 = Stack.peek();
                Stack.pop();

                // calculate the result for these two number based on different operator
                switch (arrNotation[j]) {
                    case "+":
                        Stack.push(s1 + s2);
                        break;
                    case "-":
                        Stack.push(s1 - s2);
                        break;
                    case "*":
                        Stack.push(s1 * s2);
                        break;
                    case "/":
                        Stack.push(s1 / s2);
                        break;
                }
            }
        }
        return Stack.peek();
    }

    /**
     * Test for the method
     * @param args string list contains number and operator
     */
    public static void main(String[] args){
        PrefixNotation prefixNotation= new PrefixNotation();
        String str = "+ * 10 + 5 21 / 9 4";
        System.out.println(PrefixNotation.evaluate(str));

        String str1 = "* + 1 / 2 + 3 4 5";
        System.out.println(PrefixNotation.evaluate(str1));

        String str2 = "* + 5 6 7";
        System.out.println(PrefixNotation.evaluate(str2));
    }
}
