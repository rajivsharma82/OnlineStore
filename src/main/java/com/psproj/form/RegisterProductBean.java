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
public class RegisterProductBean {

    @NotEmpty(message = "Product Category is required")
    private String productCategory;

    @NotEmpty(message="Product stock unit is required")
    private String sku;

    @NotEmpty(message="Product name is required")
    private String name;

    @NotEmpty(message="Product description is required")
    private String description;

//    @NotEmpty(message="Product price is required")
    private double unitPrice;

    @NotEmpty(message = "Image URL is required")
    private String imageUrl;

    private boolean active;
    private Integer unitsInStock;


    // This list is populated by controller for all error messages in binding result
    private List<String> errorMessages = new ArrayList<>();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
