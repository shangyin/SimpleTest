package shangyin.github.Calculator;

/**
 * Created by 41237 on 2016/4/8.
 */
@FunctionalInterface
public interface Operation {
    public double doCalculate(double...arg);
}
