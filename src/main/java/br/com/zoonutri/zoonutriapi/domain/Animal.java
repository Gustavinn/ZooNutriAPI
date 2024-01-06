package br.com.zoonutri.zoonutriapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOONUTRI", name = "ANIMAL")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_ANIMAL")
    private Integer id;

    @Column(name = "POPULAR_NAME")
    private String popularName;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "SCIENTIFIC_NAME")
    private String scientificName;

    @Column(name = "NOTE")
    private String note;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @OneToMany(orphanRemoval = true, mappedBy = "animal")
    private List<Task> task;

    @OneToMany(orphanRemoval = true, mappedBy = "animal")
    private List<Biometry> biometrics;

}
