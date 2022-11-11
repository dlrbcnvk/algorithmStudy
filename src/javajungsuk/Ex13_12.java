package javajungsuk;

public class Ex13_12 {
    public static void main(String[] args) {
        RunnableEx12 r = new RunnableEx12();
        new Thread(r).start(); // ThreadGroup에 의해 참조되므로 gc대상이 아니다
        new Thread(r).start(); // ThreadGroup에 의해 참조되므로 gc대상이 아니다
    }
}

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int money) { // 메서드 동기화
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            balance -= money;
        }
    }
}

class RunnableEx12 implements Runnable {
    Account acc = new Account();

    @Override
    public void run() {
        while (acc.getBalance() > 0) {
            int money = (int)(Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance:"+acc.getBalance());
        }
    }
}
