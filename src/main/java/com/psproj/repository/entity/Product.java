package com.psproj.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn (name = "category_id", nullable = false)
    private ProductCategory category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @CreationTimestamp
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private Date lastUpdated;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItem;

//    @OneToOne(mappedBy = "product")
//    private OrderItem orderItem;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", category=" + category +
//                ", sku='" + sku + '\'' +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", unitPrice=" + unitPrice +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", active=" + active +
//                ", unitsInStock=" + unitsInStock +
//                ", dateCreated=" + dateCreated +
//                ", lastUpdated=" + lastUpdated +
//                '}';
//    }
}
