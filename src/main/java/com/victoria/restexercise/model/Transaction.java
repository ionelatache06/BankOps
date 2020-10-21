package com.victoria.restexercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @JsonProperty(required = true)
    private Currency currency;

    @JsonProperty(required=true)
    private Money money;

    @JsonProperty(required = true)
    private Long sourceAccountId;

    @JsonProperty(required = true)
    private Long destinationAccountId;

    private Timestamp transactionDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(money, that.money) &&
                Objects.equals(sourceAccountId, that.sourceAccountId) &&
                Objects.equals(destinationAccountId, that.destinationAccountId) &&
                Objects.equals(transactionDateTime, that.transactionDateTime);
    }

    public int hashCode() {
        int result = (int) (transactionId ^ (transactionId >>> 32));
        result = 31 * result + currency.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + sourceAccountId.hashCode();
        result = 31 * result + destinationAccountId.hashCode();
        result = 31 * result + transactionDateTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", currency=" + currency +
                ", money=" + money +
                ", sourceAccountId=" + sourceAccountId +
                ", destinationAccountId=" + destinationAccountId +
                ", transactionDateTime=" + transactionDateTime +
                '}';
    }
}
