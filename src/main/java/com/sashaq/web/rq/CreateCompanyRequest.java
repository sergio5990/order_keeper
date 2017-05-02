package com.sashaq.web.rq;

import com.sashaq.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class CreateCompanyRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    //TODO Only ID or rest fields
    // TODO UserRequest instead of User
    private User contactUser;
}
