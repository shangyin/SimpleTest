package ProxyPattern;

import java.io.Serializable;

/**
 * Created by 41237 on 2016/5/7.
 */
public interface State extends Serializable {
    public void insertQuarter();
    public void ejectQuarter();
    public void turnCrank();
    public void dispense();
    public void refill();

}
