package steps;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.*;
import cepes.itacademy.sarc.service.AllocationService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AllocationSteps {

    private CollaboratorRepository collaboratorRepo;
    private ResourceRepository resourceRepo;
    private AllocationRepository allocationRepo;

    private AllocationService allocationService;

    private List<Resource> pendingResources;
    private Collaborator currentCollaborator;
    private Resource currentResource;
    private LocalDate allocationDate;
    private Allocation confirmedAllocation;
    private Exception exceptionTriggered;

    @Before()

    @Given("that resources and collaborators are registered in the system")
    public void that_resources_and_collaborators_are_registered_in_the_system() {
        collaboratorRepo = new InMemoryCollaboratorRepository();
        resourceRepo = new InMemoryResourceRepository();
        allocationRepo = new InMemoryAllocationRepository();
        allocationService = new AllocationService(allocationRepo);
        pendingResources = new ArrayList<>();
    }

    @Given("that the collaborator {string} wants to allocate the notebook {string}")
    public void that_the_collaborator_wants_to_allocate_the_notebook(String collaboratorId, String resourceId) {
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        currentResource = resourceRepo.findById(resourceId).orElseThrow();
    }

    @When("they request the reservation for a valid date")
    public void they_request_the_reservation_for_a_valid_date() {
        allocationDate = LocalDate.now().plusDays(1);

        try {
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource, allocationDate);
        } catch (Exception e) {
            exceptionTriggered = e;
        }
    }

    @Then("the system should successfully confirm the allocation")
    public void the_system_should_successfully_confirm_the_allocation() {
        assertNotNull(confirmedAllocation, "The allocations should be confirmed");
        assertNull(exceptionTriggered, "No exception should have been thrown");
    }
    
    @And("the notebook {string} is already allocated")
    public void the_notebook_is_already_allocated(String resourceId) {

        try {
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource, allocationDate);
        } catch (Exception e) {
            exceptionTriggered = e;
        }
    }

    @Then("the system should deny the allocation")
    public void the_system_should_deny_the_allocation(){
        assertNotNull(exceptionTriggered, exceptionTriggered.getMessage());
    }

    @Given("that the collaborator {string} wants to allocate the lab {string}")
    public void that_the_collaborator_wants_to_allocate_the_lab(String collaboratorId, String resourceId){
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        currentResource = resourceRepo.findById(resourceId).orElseThrow();
    }

    @When("they request the reservation for a past date")
    public void they_request_the_reservation_for_a_past_date(){
        allocationDate = LocalDate.now().minusDays(1);
        
        try {
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource, allocationDate);
        } catch (Exception e) {
            exceptionTriggered = e;
        }
    }

    @And("wants to allocate the lab {string}")
    public void wants_to_allocate_the_lab(String resourceId){
        currentResource = resourceRepo.findById(resourceId).orElseThrow();

        try{
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource, allocationDate);
        }catch(Exception e){
            exceptionTriggered = e;
        }
    }

    @And("wants to allocate the room {string}")
    public void wants_to_allocate_the_room(String resourceId){
        currentResource = resourceRepo.findById(resourceId).orElseThrow();

        try{
            confirmedAllocation = allocationService.allocateResource(currentCollaborator, currentResource, allocationDate);
        }catch(Exception e){
            exceptionTriggered = e;
        }
    }

    @Given("that the collaborator {string} wants to allocate the notebook {string} and the room {string}")
    public void that_the_collaborator_wants_to_allocate_the_resources(String collaboratorId, String notebookId, String roomId) {
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        var notebookResource = resourceRepo.findById(notebookId).orElseThrow();
        pendingResources.add(notebookResource);
        var roomResource = resourceRepo.findById(roomId).orElseThrow();
        pendingResources.add(roomResource);
    }
    
    @When("they request the reservations for a valid date")
    public void they_request_the_reservations_for_a_valid_date(){
        allocationDate = LocalDate.now().plusDays(1);
        
        for(var resource : pendingResources){
            try {
                confirmedAllocation = allocationService.allocateResource(currentCollaborator, resource, allocationDate);
            } catch (Exception e) {
                exceptionTriggered = e;
            }
        }
    }
    
    @Then("the system should successfully confirm both allocations")
    public void the_system_should_successfully_confirm_both_allocations() {
        assertNotNull(confirmedAllocation, "The allocations should be confirmed");
        assertNull(exceptionTriggered, "No exception should have been thrown");
    }

    @Given("that the collaborator {string} wants to allocate the notebook {string} and the lab {string}")
    public void that_the_collaborator_want_to_allocate_the_notebook_and_the_lab(String collaboratorId, String notebookId, String labId) {
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        var notebookResource = resourceRepo.findById(notebookId).orElseThrow();
        pendingResources.add(notebookResource);
        var labResource = resourceRepo.findById(labId).orElseThrow();
        pendingResources.add(labResource);
    }

    @Then("the system should deny the allocations")
    public void the_system_should_deny_the_allocations() {
        assertNotNull(exceptionTriggered, "An exception should have been thrown.");
    }

    @Given("that the collaborator {string} wants to allocate the resource {string} and the resource {string}")
    public void the_collaborator_wants_to_allocate_the_resource_and_the_resource(String collaboratorId, String resourceId1, String resourceId2){
        currentCollaborator = collaboratorRepo.findById(collaboratorId).orElseThrow();
        var resource1 = resourceRepo.findById(resourceId1).orElseThrow();
        pendingResources.add(resource1);
        var resource2 = resourceRepo.findById(resourceId2).orElseThrow();
        pendingResources.add(resource2);
    }
}
