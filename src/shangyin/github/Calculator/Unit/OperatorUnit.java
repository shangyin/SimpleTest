package shangyin.github.Calculator.Unit;

/**
 * Created by 41237 on 2016/4/3.
 */

public abstract class OperatorUnit extends Unit
{
    private String value;

    public OperatorUnit(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "[" + value +"]";
    }
}
