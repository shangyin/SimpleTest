package ProxyPattern;

import java.rmi.*;

public class GumballMonitor {
    GumballMachineRemote machine;

    public GumballMonitor(GumballMachineRemote machine) {
        this.machine = machine;
    }

    public void report() {
        try {
        System.out.println("location: " + machine.getLocation());
        System.out.println("inventory: " + machine.getCount());
        System.out.println("state: " + machine.getState());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        GumballMachineRemote machine =(GumballMachineRemote) Naming.lookup("rmi://127.0.0.1/aa");
        GumballMonitor monitor = new GumballMonitor(machine);
        monitor.report();
    }
}