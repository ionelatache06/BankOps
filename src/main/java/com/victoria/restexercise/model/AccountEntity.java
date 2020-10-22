package com.victoria.restexercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Currency;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {
    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private Currency currency;

    @JsonProperty(required = true)
    private Money balance;

    @JsonProperty(required = true)
    private Boolean treasury;

    private boolean treasuryWasSet = false;

    public AccountEntity(String name, Currency currency, Money balance, Boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Boolean getTreasury() {
        return treasury;
    }

    public void setTreasury(Boolean treasury) {
        if (!treasuryWasSet){
            this.treasury = treasury;
            treasuryWasSet = true;
        }
       // throw new InvalidOperationException("Treasury property can be set only once at creation time");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity account = (AccountEntity) o;
        return treasuryWasSet == account.treasuryWasSet &&
                Objects.equals(accountId, account.accountId) &&
                Objects.equals(name, account.name) &&
                Objects.equals(currency, account.currency) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(treasury, account.treasury);
    }

    @Override
    public int hashCode() {
        int result = (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + treasury.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", treasury=" + treasury +
                '}';
    }
}
