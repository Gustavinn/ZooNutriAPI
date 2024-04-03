package br.com.zoonutri.zoonutriapi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOONUTRI", name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_USER")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "HASH")
    private String hash;

    @ManyToOne
    @JoinColumn(name = "CD_ROLE")
    private UserRole userRole;

    @Column(name = "ACTIVE")
    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @OneToMany(orphanRemoval = true, mappedBy = "responsibleUser")
    private List<Task> tasks;

    public User(Integer id, String name, String email, UserRole role, boolean active, Date creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = role;
        this.active = active;
        this.creationDate = creationDate;
    }
}
