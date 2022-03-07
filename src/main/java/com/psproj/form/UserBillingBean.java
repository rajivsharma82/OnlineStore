package com.psproj.form;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserBillingBean {


    @NotEmpty(message = "Full name is required")
    private String fullname;

    @NotEmpty(message="Email is required")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    private String email;

    @NotEmpty(message="Address is required")
    private String address;
    @NotEmpty(message="City is required")
    private String city;
    @NotEmpty(message="State is required")
    private String state;
    @NotEmpty(message="Zip is required")
    private String zip;
    @NotEmpty(message="Card Name is required")
    private String cardname;
    @NotEmpty(message="Card Number is required")
    private String cardnumber;
    @NotEmpty(message="Year of card expiry is required")
    private String expyear;
    @NotEmpty(message="Month of card expiry is required")
    private String expmonth;
    @NotEmpty(message="cvv is required")
    private String cvv;

    // This list is populated by controller for all error messages in binding result
    private List<String> errorMessages = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
