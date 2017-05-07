package com.sashaq.web.rq;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCompanyRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    @Length(min = 5)
    private String phone;

    @Range
    @NotNull
    private Integer contactUserId;
}
