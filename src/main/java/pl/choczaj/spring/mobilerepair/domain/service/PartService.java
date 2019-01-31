package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.stereotype.Service;
import pl.choczaj.spring.mobilerepair.domain.repository.PartRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;

@Service
public class PartService {

    PartRepository partRepository;
    TaskRepository taskRepository;

//    public PartService(PartRepository partRepository, TaskRepository taskRepository) {
//        this.partRepository = partRepository;
//        this.taskRepository = taskRepository;
//    }
//
//    public List<Part> findAvailableParts(String manufacturer, String model) {
//        List<Part> partsAvailableInStorage = partRepository.findPartsByManufacturerAndModel(manufacturer, model).orElse(new ArrayList<>());
//        List<Task> tasksInProgres = taskRepository.findTasksInProgres().orElse(new ArrayList<>());
//
//
//
//        return
//    }

}
