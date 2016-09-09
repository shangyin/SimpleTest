package shangyin.github.SimpleCalc;

/**
 * Created by 41237 on 2016/3/23.
 */
public class WordJudge {
    public static boolean isNumber(String s)
    {
        return s.matches("[+-]?[0-9]+");
    }

    public static boolean isOperator(String s)
    {
        return s.matches("[\\+\\-\\*/%]");
    }

    public static boolean isBracket(String s)
    {
        return s.matches("[()]");
    }

    public static boolean isNumber(char c)
    {
        return c>=48 && c<=57;
    }

    public static boolean isLeftBracket(char c)
    {
        return c == '(';
    }

    public static boolean isRightBracket(char c)
    {
        return c == ')';
    }

   /* public static void main(String[] argv)
    {
        System.out.println(isOperator(""));
    }
    */
}
