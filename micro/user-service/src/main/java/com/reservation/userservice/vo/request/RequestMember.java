package com.reservation.userservice.vo.request;

import com.reservation.userservice.entity.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMember {

    @NotNull(message = "Email cannot be null")
    @Size(min = 4, message = "Check your email")
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Check your sub")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be longer than 7")
    private String password;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 9)
    private String phone;

    @Null
    private Address address;

}
