package shangyin.github.SimpleCalc;

import java.util.*;

/**
 * Created by 41237 n 2016/3/22.
 */
public class Calculator {
    private static void doMath(Stack<Integer> number, Stack<Character> operator)
    {
        int a = number.pop();
        int b = number.pop();
        char c = operator.pop();
        int res = 0;

        switch (c)
        {
            case '+':
                res = b + a;
                break;
            case '-':
                res = b - a;
                break;
            case '*':
                res = b * a;
                break;
            case '/':
                res = b / a;
                break;
            default:
        }
        number.push(res);
    }

    public static int subfixMath(String s)
    {
        Stack<Integer> myStack = new Stack<>();
        for (String e : s.split("\\s+"))
        {
            System.out.println(e);
            if (WordJudge.isNumber(e))
            {
                myStack.push(Integer.valueOf(e));
            }
            else
            {
                Integer arg1 = myStack.pop();
                Integer arg2 = myStack.pop();
                switch (e)
                {
                    case "add":
                        myStack.push(new Integer(arg1+arg2));
                        break;
                    case "sub":
                        myStack.push(new Integer(arg1-arg2));
                        break;
                    case "mul":
                        myStack.push(new Integer(arg1*arg2));
                        break;
                    case "div":
                        myStack.push(new Integer(arg1/arg2));
                }
            }
        }
        return myStack.pop();
    }

    public static int midFix(String s)
    {

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (WordJudge.isNumber(c))
            {
                int ret = WordScanner.readNumber(s, i);
                i += String.valueOf(ret).length() - 1;   //don't forget i++
                numStack.push(ret);
            }
            else if (c == ')')
            {
                while (operatorStack.peek() != '(')
                    doMath(numStack, operatorStack);
                operatorStack.pop();
            }
            else if (c == '*' || c == '/')
                operatorStack.push(c);
            else if (c == '+' || c == '-')
            {
                while ( !operatorStack.empty() &&
                        (operatorStack.peek() == '*'|| operatorStack.peek() == '/'))
                    doMath(numStack, operatorStack);
                operatorStack.push(c);
            }
            else
                if (c != ' ')
                    operatorStack.push(c);
        }
        while (!operatorStack.empty())
            doMath(numStack, operatorStack);
        return numStack.pop();
        /* for test
        while (!numStack.empty())
        {
            System.out.println(numStack.pop());
        }
        System.out.println();
        while (!operatorStack.empty())
        {
            System.out.println(operatorStack.pop());
        }*/
    }

    public  static void main(String[] argv)
    {
        //System.out.println(midFix(new Scanner(System.in).nextLine()));
    }
}


