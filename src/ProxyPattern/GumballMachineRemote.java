package ProxyPattern;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 41237 on 2016/5/7.
 */
public interface GumballMachineRemote extends Remote {
    public int getCount() throws RemoteException;
    public String getLocation() throws RemoteException;
    public State getState() throws RemoteException;
}

