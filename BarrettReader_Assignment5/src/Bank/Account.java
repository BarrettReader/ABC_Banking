/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bank;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author User
 */
public class Account {
 
    private String id = "c-100";
    private final DoubleProperty balance = new SimpleDoubleProperty(this, "balance");
    private final DoubleProperty interestRate = new SimpleDoubleProperty(this, "interestRate", 2.3);
 
    public Account() { }
     
    public final void setBalance(double balance) {
        if (balance >= 0) 
            this.balance.set(balance);
        else
            throw new IllegalArgumentException("Error: balance must be 0 or more.");        
    }
 
    public final double getBalance() {
        return balance.get();
    }
 
    public final DoubleProperty balanceProperty() {
        return balance;
    }
 
    public final void setInterestRate(double rate) {
        if (rate >= 0) 
            interestRate.set(rate);
        else
            throw new IllegalArgumentException("Error: interest rate must be greater than 0.");
    }
 
    public final double getInterestRate() {
        return interestRate.get();
    }
 
    public final DoubleProperty interestRateProperty() {
        return interestRate;
    }
 
    public void setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Error - ID can't be empty.");
        }
    }
 
    public String getId() {
        return id;
    }
    public String toString() { return String.format("%s Balance: $%.2f", id, balance.get()); }

}
