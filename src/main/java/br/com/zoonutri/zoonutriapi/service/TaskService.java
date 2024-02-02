package br.com.zoonutri.zoonutriapi.service;

import br.com.zoonutri.zoonutriapi.domain.dto.TaskDTO;
import br.com.zoonutri.zoonutriapi.domain.mapper.TaskMapper;
import br.com.zoonutri.zoonutriapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.zoonutri.zoonutriapi.domain.enums.TaskStatus.UNCOMPLETED;
import static br.com.zoonutri.zoonutriapi.service.LogService.TASK_ICON;
import static br.com.zoonutri.zoonutriapi.util.GeneralUtil.getMessage;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TaskService {

    public static final String MSG_ERROR_TASK_ID = "msg.error.task.id";

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final LogService logService;

    public List<TaskDTO> findAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    public List<TaskDTO> findUserTasks(final Integer userId) {
        return taskMapper.toDtoList(taskRepository.findAllByResponsibleUserId(userId));
    }

    public List<TaskDTO> findAnimalTasks(final Integer animalId) {
        return taskMapper.toDtoList(taskRepository.findAllByAnimalId(animalId));
    }

    public void saveTask(final TaskDTO taskDTO, Boolean isUpdate) {
        if (isNull(taskDTO.getTaskStatus())) {
            taskDTO.setTaskStatus(UNCOMPLETED);
        }

        taskRepository.save(taskMapper.toEntity(taskDTO));

        if (isUpdate) {
            logService.saveLog(logService.createLogDTO(TASK_ICON, "atualizou uma tarefa às"));
        } else {
            logService.saveLog(logService.createLogDTO(TASK_ICON, "inseriu uma tarefa às"));
        }
    }

    public void deleteTask(final Integer taskId) {
        taskRepository.delete(taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException(
                        getMessage(MSG_ERROR_TASK_ID) + taskId)));
        logService.saveLog(logService.createLogDTO(TASK_ICON, "removeu uma tarefa às"));
    }

}
