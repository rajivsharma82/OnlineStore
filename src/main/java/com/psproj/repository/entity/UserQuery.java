package com.psproj.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_query")
public class UserQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    private User user ;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "user_query")
    private String userQuery;

    @Column(name = "admin_response")
    private String adminResponse;

}
