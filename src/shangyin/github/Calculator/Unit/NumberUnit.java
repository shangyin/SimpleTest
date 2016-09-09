package shangyin.github.Calculator.Unit;

/**
 * Created by 41237 on 2016/4/3.
 */
public class NumberUnit extends Unit
{
    private double value;

    public NumberUnit(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "[" + value + "]";
    }
}
