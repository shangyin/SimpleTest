package shangyin.github.Calculator.Unit;

import shangyin.github.Calculator.Operation;

import java.util.List;

/**
 * Created by 41237 on 2016/4/8.
 */
public class NumberOperatorUnit extends OperatorUnit
{
    private Operation op;
    private int priority;
    private int subject;

    public int getPriority()
    {
        return priority;
    }

    public int getSubject()
    {
        return subject;
    }

    public NumberOperatorUnit(String value, int subject, int priority, Operation op)
    {
        super(value);
        this.subject = subject;
        this.priority = priority;
        this.op = op;
    }

    public void doOperation(List<Unit> list, int position)
    {
        double[] arg = new double[10];
        for (int i = subject - 1; i >= 0; i--)
            arg[i] = ((NumberUnit)list.get(position - i - 1)).getValue();
        NumberUnit ret = new NumberUnit(op.doCalculate(arg));
        for (int i = 0; i <= subject; i++)
            list.remove(position - subject);
        list.add(position - subject, ret);
    }


}
