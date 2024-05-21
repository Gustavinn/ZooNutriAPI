package br.com.zoonutri.zoonutriapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
public class UserWithPasswordDTO implements Serializable {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private UserRoleDTO userRole;

    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date creationDate;

    private List<TaskDTO> tasks;
}
