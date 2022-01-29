package com.banking.app.bankingappdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionDetail {

    @Id
    private Long id;
    private String accountNumber;
    private BigDecimal transactionAmount;
    private Timestamp transactionTime;
}
