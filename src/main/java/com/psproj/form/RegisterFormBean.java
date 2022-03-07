package com.psproj.form;

import com.psproj.validation.TwoFieldsAreEqual;
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
@TwoFieldsAreEqual(fieldOneName = "confirmPassword", fieldTwoName = "password",
        message = "Password and Confirm Password values must be the same.")
public class RegisterFormBean {

    @NotEmpty(message = "User name is required")
    private String userName;

    @NotEmpty(message="Email is required")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    private String email;

    @Length(min=1, max=20,message = "Firstname must be between 1 and 20 character in length")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    private String lastName;

    private String password;
    private String confirmPassword;
    private String phone;

    // This list is populated by controller for all error messages in binding result
    private List<String> errorMessages = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
