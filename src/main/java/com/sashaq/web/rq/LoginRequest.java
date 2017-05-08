package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Length(min = 8)
    private String password;
}
