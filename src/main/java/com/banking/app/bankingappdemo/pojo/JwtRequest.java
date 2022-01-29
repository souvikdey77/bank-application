package com.banking.app.bankingappdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {
    @NotEmpty(message = "Please provide username")
    private String userName;
    @NotEmpty(message = "Please provide password")
    private String password;
}
