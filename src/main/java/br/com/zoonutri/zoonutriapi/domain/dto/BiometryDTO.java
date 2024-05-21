package br.com.zoonutri.zoonutriapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
public class BiometryDTO implements Serializable {

    private Integer id;

    private Integer animalId;

    private String animalName;

    private Integer userId;

    private String userName;

    private String weight;

    private String length;

    private String height;

    private String note;

    private String prescription;

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date creationDate;

}
