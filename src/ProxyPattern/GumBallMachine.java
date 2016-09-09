package ProxyPattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

//  注意，这个程序必须要class也在同一个目录里。因此个玩意儿最好在命令行里面运行

public class GumBallMachine extends UnicastRemoteObject implements GumballMachineRemote {

    public static void main(String[] args) throws RemoteException {
//        GumBallMachine machine = new GumBallMachine(5, "CA");
//
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        int a = 10;
//        while (a-- > 0) {
//            machine.refill();
//            machine.turnCrank();
//            machine.insertQuarter();
//        }
        GumballMachineRemote machine = null;
        try {
            machine = new GumBallMachine(100, "惠州");
            Context namingContext = new InitialContext();
            namingContext.bind("rmi://127.0.0.1:1099/aa", machine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    会在这些接口中改变context的状态（变量），毕竟他们拥有context的引用
    State soldOutState;
    State soldState;
    State hasQuarterState;
    State noQuarterState;
    State winnerState;

    State state = soldOutState;


    String location;
    int count = 0;

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getState() {
        return state;
    }

    public GumBallMachine(int count, String location) throws RemoteException {
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        winnerState = new WinnerState(this);
        this.location = location;
        this.count = count;
        if (count > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void refill() {
        state.refill();
    }

    void setState(State state) {
        this.state = state;
    }


    public void releaseBall() {
        System.out.println("The ball is coming!");
        if (count != 0) {
            count -= 1;
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class SoldOutState implements State {
    transient GumBallMachine machine;

    public SoldOutState(GumBallMachine machine) {
        this.machine = machine;
    }

    public void insertQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void ejectQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void turnCrank() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void dispense() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void refill() {
        System.out.println("I got many balls!");
        machine.setCount(10);
        machine.setState(machine.getNoQuarterState());
    }
}

class SoldState implements State {
    transient GumBallMachine machine;

    public SoldState(GumBallMachine machine) {
        this.machine = machine;
    }

    public void insertQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void ejectQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void turnCrank() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void dispense() {
        machine.releaseBall();
        if (machine.getCount() > 0) {
            machine.setState(machine.getNoQuarterState());
        } else {
            System.out.println("Lucky, you are the last one!");
            machine.setState(machine.getSoldOutState());
        }
    }
    public void refill() {
        System.out.println("Don't talk to me like that! - abort");
    }
}

class HasQuarterState implements State {
    transient GumBallMachine machine;
    Random random = new Random(System.currentTimeMillis());

    public HasQuarterState(GumBallMachine machine) {
        this.machine = machine;
    }

    public void insertQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void ejectQuarter() {
        System.out.println("Here is your quarter");
        machine.setState(machine.getNoQuarterState());
    }

    public void turnCrank() {
        System.out.println("You turned the crank!");
        if (random.nextInt(10) == 0 && machine.getCount() > 1) {
            machine.setState(machine.getWinnerState());
        } else {
            machine.setState(machine.getSoldState());
        }
    }

    public void dispense() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void refill() {
        System.out.println("Don't talk to me like that! - abort");
    }
}

class NoQuarterState implements State {
    transient GumBallMachine machine;

    public NoQuarterState(GumBallMachine machine) {
        this.machine = machine;
    }

    public void insertQuarter() {
        System.out.println("Recieve your quarter");
        machine.setState(machine.getHasQuarterState());
    }

    public void ejectQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void turnCrank() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void dispense() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void refill() {
        System.out.println("Don't talk to me like that! - abort");
    }
}

class WinnerState implements State {
    transient GumBallMachine machine;

    public WinnerState(GumBallMachine machine) {
        this.machine = machine;
    }

    public void insertQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void ejectQuarter() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void turnCrank() {
        System.out.println("Don't talk to me like that! - abort");
    }

    public void dispense() {
        System.out.println("Happy winner!");
        machine.releaseBall();
        machine.releaseBall();
        if (machine.getCount() > 0) {
            machine.setState(machine.getNoQuarterState());
        } else {
            System.out.println("Lucky, you got last two");
            machine.setState(machine.getSoldOutState());
        }

    }

    public void refill() {
        System.out.println("Don't talk to me like that! - abort");
    }
}

