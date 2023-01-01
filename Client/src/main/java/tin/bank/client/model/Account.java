package tin.bank.client.model;

public class Account {
    private String id;
    private String name;
    private Double balance;
    private boolean isBlocked;

    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public String getId() {
        return id;
    }
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double credit(int Amount) {
        this.balance += Amount;
        return this.balance;
    }

    public boolean debit(int Amount) {
        if (Amount <= balance) {
            this.balance -= Amount;
            isBlocked = false;
        } else {

            System.out.println("Amount exceeded balance");
            isBlocked = true;

        }
        return this.isBlocked;
    }

    public boolean transferTo(Account a, int Amount) {
        if (Amount <= balance) {
            a.balance += Amount;
            this.balance -= Amount;
            isBlocked = false;
        } else {
            isBlocked = true;
            System.out.println("Amount exceeded balance");

        }
        return this.isBlocked;

    }

    public String toString() {
        return "Account is " + this.id + " name " + this.name + " balance " + this.balance;
    }
}
