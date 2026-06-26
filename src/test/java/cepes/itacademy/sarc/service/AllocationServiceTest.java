package cepes.itacademy.sarc.service;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.*;
import cepes.itacademy.sarc.service.AllocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AllocationServiceTest {

    private CollaboratorRepository collaboratorRepo;
    private ResourceRepository resourceRepo;
    private AllocationRepository allocationRepo;
    private AllocationService allocationService;


    @BeforeEach
    void setUp() {
        collaboratorRepo = new InMemoryCollaboratorRepository();
        resourceRepo = new InMemoryResourceRepository();

        allocationRepo = new InMemoryAllocationRepository();

        allocationService = new AllocationService(allocationRepo);
    }

    @Test
    void shouldSuccessfullyAllocateAnAvailableResource() {
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        LocalDate allocationDate = LocalDate.now().plusDays(1);
        Exception catchException = null;
        Allocation result = null;
        try{
            result = allocationService.allocateResource(collaborator, notebook, allocationDate);
        }catch(Exception e ){
            catchException = e;
        }

        assertNull(catchException);
        assertNotNull(result, "The allocation should not be null");
        assertEquals(collaborator, result.getCollaborator(), "The collaborator should match Andre Oliveira");
        assertEquals(notebook, result.getResource(), "The resource should match Notebook CEPES1983");
        assertEquals(allocationDate, result.getDate(), "The date should match 2026-06-12");

        assertTrue(allocationRepo.existsByResourceAndDate(notebook, allocationDate),
                "The allocation should be persisted in the repository");
    }

    @Test
    void allocatingSameResourceTwiceShouldThrowAnException(){
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        LocalDate allocationDate = LocalDate.of(2026, 6, 12);
        Allocation secondAllocation = null;
        Exception catchException = null;
        try{
            allocationService.allocateResource(collaborator, notebook, allocationDate);
            secondAllocation = allocationService.allocateResource(collaborator, notebook, allocationDate);
        }catch(Exception e){
            catchException = e;
        }
        assertNotNull(catchException);
        assertNull(secondAllocation, "Should be null");
    }

    @Test
    void allocatingResourceOnAnInvalidDateShouldThrowException(){
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        LocalDate allocationDate = LocalDate.now().minusDays(1);
        Exception catchException = null;
        Allocation result = null;
        try{
            result = allocationService.allocateResource(collaborator, notebook, allocationDate);
        }catch(Exception e ){
            catchException = e;
        }
        assertNotNull(catchException);
        assertNull(result, "Should be null");
    }

    @Test
    void allocatingSameTypeOfResourceShouldThrowException(){
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        Resource notebook2 = resourceRepo.findById("CEPES2017").orElseThrow();
        LocalDate allocationDate = LocalDate.now().plusDays(1);
        Exception catchException = null;
        Allocation result = null;
        Allocation sndResult = null;
        try{
            result = allocationService.allocateResource(collaborator, notebook, allocationDate);
            sndResult = allocationService.allocateResource(collaborator, notebook2, allocationDate);
        }catch(Exception e ){
            catchException = e;
        }
        assertNotNull(catchException);
        assertNotNull(result, "First allocation should work!");
        assertNull(sndResult, "Second allocation should fail!");
    }
    @Test
    void allocatingLabAndNotebookShouldThrowException(){
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        Resource lab = resourceRepo.findById("312").orElseThrow();
        LocalDate allocationDate = LocalDate.now().plusDays(1);
        Exception catchException = null;
        Allocation result = null;
        Allocation sndResult = null;
        try{
            result = allocationService.allocateResource(collaborator, notebook, allocationDate);
            sndResult = allocationService.allocateResource(collaborator, lab, allocationDate);
        }catch(Exception e ){
            catchException = e;
        }
        assertNotNull(catchException);
        assertEquals("Can't allocate a room/notebook and a lab", catchException.getMessage());
        assertNotNull(result, "First allocation should work!");
        assertNull(sndResult, "Second allocation should fail!");
    }
    @Test
    void allocatingNotebookAndRoomShouldWork(){
        Collaborator collaborator = collaboratorRepo.findById("10002020").orElseThrow();
        Resource notebook = resourceRepo.findById("CEPES1983").orElseThrow();
        Resource room = resourceRepo.findById("512").orElseThrow();
        LocalDate allocationDate = LocalDate.now().plusDays(1);
        Exception catchException = null;
        Allocation result = null;
        Allocation sndResult = null;
        try{
            result = allocationService.allocateResource(collaborator, notebook, allocationDate);
            sndResult = allocationService.allocateResource(collaborator, room, allocationDate);
        }catch(Exception e ){
            catchException = e;
        }
        assertNull(catchException);
        assertNotNull(result, "First allocation should work!");
        assertNotNull(sndResult, "Second allocation should work!");
    }
}