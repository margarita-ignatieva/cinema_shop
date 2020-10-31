package com.cinema.shop.model.dto;

import com.cinema.shop.validators.ValidEmail;
import com.cinema.shop.validators.ValidPassword;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Min(4)
    private String password;
    @NotNull
    @ValidPassword
    private String repeatPassword;
}
