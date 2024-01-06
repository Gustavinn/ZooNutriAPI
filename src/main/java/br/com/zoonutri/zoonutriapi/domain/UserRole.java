package br.com.zoonutri.zoonutriapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOONUTRI", name = "USER_ROLE")
public class UserRole implements Serializable {

    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String DOCTOR = "DOCTOR";
    public static final String OPERATOR = "OPERATOR";

    @Id
    @Column(name = "CD_ROLE")
    private Integer id;

    @Column(name = "DESC_ROLE", unique = true)
    private String description;

}
