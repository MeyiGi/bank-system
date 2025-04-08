package model;

import java.math.BigDecimal;

public class Bank {
    private String name;
    private String code;
    private String address;
    private BigDecimal balance;

    public Bank(String name, String code, String address, BigDecimal balance) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getAddress() {
        return address;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Object[] toArray() {
        return new Object[] { name, code, address, balance };
    }
}