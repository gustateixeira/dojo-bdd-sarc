package cepes.itacademy.sarc.service;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.AllocationRepository;
import java.time.LocalDate;

public class AllocationService {
    private final AllocationRepository repository;

    public AllocationService(AllocationRepository repository) {
        this.repository = repository;
    }

    public Allocation allocateResource(Collaborator collaborator, Resource resource, LocalDate date) throws Exception {
        if(repository.existsByResourceAndDate(resource, date)){
            String message = String.format("this resource is already allocated %s", resource.getId());
            throw new Exception(message);
        }
        if(date.isBefore(LocalDate.now())){
            String message = String.format("The date %s is in the past and not allowed", date.toString());
            throw new Exception(message);
        }
        var collaboratorAllocations = repository.findByCollaboratorIdAndDate(collaborator, date);
        var hasSameTypeOfResourceAllocated = collaboratorAllocations.stream().anyMatch(a -> a.getResource().getClass().equals(resource.getClass()));
        if(hasSameTypeOfResourceAllocated){
            throw new Exception("Can't allocate two resources of the same type");
        }
        if (resource instanceof Lab) {
            var roomOrNotebookAllocated = collaboratorAllocations.stream().anyMatch(a -> a.getResource() instanceof Room || a.getResource() instanceof Notebook);
            if (roomOrNotebookAllocated) {
                String message = String.format("Can't allocate a room/notebook and a lab");
                throw new Exception(message);
            }
        }else if(resource instanceof Notebook){
            var hasLabAllocated = collaboratorAllocations.stream().anyMatch(a -> a.getResource() instanceof Lab);
            if (hasLabAllocated) {
                String message = String.format("Can't allocate a notebook and a lab");
                throw new Exception(message);
            }
        }

        Allocation newAllocation = new Allocation(collaborator, resource, date);
        
        repository.saveAllocation(newAllocation);
        return newAllocation;
    }
}
