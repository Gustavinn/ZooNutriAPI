package br.com.zoonutri.zoonutriapi.domain.dto;

import br.com.zoonutri.zoonutriapi.domain.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
public class TaskDTO  implements Serializable {

    private Integer id;

    private String tittle;

    private String description;

    private Date date;

    @JsonFormat(shape = STRING)
    private TaskStatus taskStatus;

    private Integer animalId;

    private Integer responsibleUserId;

    private String responsibleUserName;

}
