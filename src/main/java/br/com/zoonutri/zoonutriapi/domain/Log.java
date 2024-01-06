package br.com.zoonutri.zoonutriapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "ZOONUTRI", name = "LOG")
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CD_LOG")
    private Integer id;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "MESSAGE")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_USER")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

}
