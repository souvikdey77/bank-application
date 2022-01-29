package com.banking.app.bankingappdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferBalanceTO {
    @NotEmpty(message = "Please provide fromAccountNumber")
    private String fromAccountNumber;
    @NotEmpty(message = "Please provide toAccountNumber")
    private String toAccountNumber;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
}
