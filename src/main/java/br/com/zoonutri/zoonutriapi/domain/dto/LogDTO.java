package br.com.zoonutri.zoonutriapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
@Builder
public class LogDTO implements Serializable {

    private Integer id;

    private String icon;

    private String userName;

    private String message;

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date creationDate;
}
