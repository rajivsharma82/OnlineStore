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
public class QueryFormBean {

    @Length(min=1, max=20,message = "Firstname must be between 1 and 20 character in length")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    private String lastName;

    private String phone;

    private String orderTrackingNumber;

    @NotEmpty(message="Email is required")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    private String email;

    private String country;

    private String query;


    // This list is populated by controller for all error messages in binding result
    private List<String> errorMessages = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
