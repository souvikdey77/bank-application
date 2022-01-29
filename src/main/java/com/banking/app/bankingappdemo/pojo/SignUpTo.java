package com.banking.app.bankingappdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpTo {
    @NotEmpty(message = "Please provide firstname")
    private String firstName;
    @NotEmpty(message = "Please provide lastname")
    private String lastName;
    @NotEmpty(message = "Please provide username")
    private String userName;
    @NotEmpty(message = "Please provide password")
    private String password;
}
