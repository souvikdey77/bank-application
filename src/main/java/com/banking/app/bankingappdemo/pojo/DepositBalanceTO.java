package com.banking.app.bankingappdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositBalanceTO {
    @NotEmpty(message = "Please provide accountNumber")
    private String accountNumber;
    private BigDecimal amount;
    private Timestamp transactionTime;
}
