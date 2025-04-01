package db.entity;

import java.math.BigDecimal;

public class Client {
    private String name;
    private BigDecimal balance;

    public Client(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
