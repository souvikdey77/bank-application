package com.banking.app.bankingappdemo.pojo;

import com.banking.app.bankingappdemo.model.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatement {
    BigDecimal currentBalance;
    List<TransactionDetail> transactionHistory;
}
