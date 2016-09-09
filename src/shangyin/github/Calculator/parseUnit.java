package shangyin.github.Calculator;

import shangyin.github.Calculator.Unit.*;

import java.util.*;

/**
 * Created by 41237 on 2016/4/8.
 */
public class parseUnit
{
    public static boolean checkPolish(List<Unit> list)
    {
        int acc = 0;
        for (Unit u : list)
        {
            if (u instanceof NumberUnit)
                acc += 1;
            else if (u instanceof NumberOperatorUnit)
            {
                if (((NumberOperatorUnit) u).getSubject() > 1)
                    acc -= 1;
            }
        }
        return acc == 1;
    }

    public static List<Unit> parseIntoUnit(String exp, Map<String, NumberOperatorUnit> keyWord)
    {
        List<Unit> ret = new ArrayList<>();

        for (int i = 0; i < exp.length(); i++)
        {
            char c = exp.charAt(i);
            if (Character.isDigit(c))
            {
                int length = 1;
                while (length+i < exp.length()
                        && (Character.isDigit(exp.charAt(i+length))
                        || exp.charAt(i+length) == '.'))
                    length += 1;
                ret.add(new NumberUnit(Double.parseDouble(exp.substring(i, i + length))));
                i += length - 1;
            }
            else if (c == '(' || c == ')')
                ret.add(new BracketOperatorUnit(String.valueOf(c)));
            else if (keyWord.containsKey(String.valueOf(c)))
                ret.add(keyWord.get(String.valueOf(c)));
            else
                System.out.println("not support char");
        }
        return ret;
    }

    public static List<Unit> parseIntoPolish(List<Unit> list)
    {
        List<Unit> numberTemp = new ArrayList<>();
        List<Unit> OperatorTemp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            Unit u = list.get(i);
            if (u instanceof NumberUnit)
            {
                numberTemp.add(u);
            }
            else if (u instanceof BracketOperatorUnit)
            {
                if (((BracketOperatorUnit) u).getValue().equals("("))
                    OperatorTemp.add(u);
                else    // ")"
                {
                    int index = OperatorTemp.size() - 1;
                    while (!((OperatorUnit) OperatorTemp.get(index)).getValue().equals("("))
                    {
                        numberTemp.add(OperatorTemp.get(index));
                        OperatorTemp.remove(index);
                        index -= 1;
                    }
                    OperatorTemp.remove(index);
                }
            }
            else    // 普通运算符
            {
                int currentP = ((NumberOperatorUnit) u).getPriority();
                int topP;
                if (OperatorTemp.isEmpty() || ((OperatorUnit)OperatorTemp.get(OperatorTemp.size()-1)).getValue().equals("("))    //符号栈是否为空
                    topP = Integer.MIN_VALUE;
                else
                    topP = ((NumberOperatorUnit) OperatorTemp.get(OperatorTemp.size() - 1)).getPriority();
                if (currentP < topP)
                {
                    while (currentP < topP)
                    {
                        numberTemp.add(OperatorTemp.get(OperatorTemp.size() - 1));
                        OperatorTemp.remove(OperatorTemp.size() - 1);
                        if (OperatorTemp.size() - 1 < 0)
                            break;
                        else
                            topP = ((NumberOperatorUnit)OperatorTemp.get(OperatorTemp.size() - 1)).getPriority();
                    }
                }
                OperatorTemp.add(u);
            }
        }
        while (!OperatorTemp.isEmpty())
            numberTemp.add(OperatorTemp.remove(OperatorTemp.size() - 1));
        if (!checkPolish(numberTemp))
            System.out.println("Wrong input!");
        return numberTemp;
    }

    public static double calculatePolish(List<Unit> list)
    {
        int index = 0;
        while (list.size() != 1)
        {
            while (list.get(index) instanceof NumberUnit)
                index += 1;
            NumberOperatorUnit n =(NumberOperatorUnit) list.get(index);
            n.doOperation(list, index);
            index = 0;
        }
        return ((NumberUnit) list.get(0)).getValue();
    }

    public static Map<String, NumberOperatorUnit> initKeyWord()
    {
        Map<String, NumberOperatorUnit> keyWord = new HashMap<>();
        keyWord.put("+", new NumberOperatorUnit("+", 2, 1, x -> x[0] + x[1]));
        keyWord.put("-", new NumberOperatorUnit("-", 2, 1, x -> x[0] - x[1]));
        keyWord.put("*", new NumberOperatorUnit("*", 2, 2, x -> x[0] * x[1]));
        keyWord.put("/", new NumberOperatorUnit("*", 2, 2, x -> x[0] / x[1]));
        keyWord.put("!", new NumberOperatorUnit("!", 1, 3, x -> {double acc = 1; int i = 2;
                                                                    while (i <= x[0])acc *= i++;
                                                                    return acc;}));
        return keyWord;
    }

    public static void main(String[] args)
    {
        System.out.println(calculatePolish( parseIntoPolish(parseIntoUnit("(3+3+2)*4", initKeyWord()))));
    }
}
