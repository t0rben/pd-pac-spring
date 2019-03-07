package com.prodyna.pac.model.dto;

import java.util.Objects;

public class AccountDTO {

    private AccountOperation accountOperation;

    private Double amount;

    public AccountOperation getAccountOperation() {

        return accountOperation;
    }

    public Double getAmount() {

        return amount;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof AccountDTO)) return false;
        AccountDTO that = (AccountDTO) o;
        return getAccountOperation() == that.getAccountOperation() &&
                Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAccountOperation(), getAmount());
    }

    @Override
    public String toString() {

        return "AccountDTO{" +
                "accountOperation=" + accountOperation +
                ", amount=" + amount +
                '}';
    }
}
