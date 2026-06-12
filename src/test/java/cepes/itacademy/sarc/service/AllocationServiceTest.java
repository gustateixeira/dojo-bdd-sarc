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
        LocalDate allocationDate = LocalDate.of(2026, 6, 12);

        Allocation result = allocationService.allocateResource(collaborator, notebook, allocationDate);

        assertNotNull(result, "The allocation should not be null");
        assertEquals(collaborator, result.getCollaborator(), "The collaborator should match Andre Oliveira");
        assertEquals(notebook, result.getResource(), "The resource should match Notebook CEPES1983");
        assertEquals(allocationDate, result.getDate(), "The date should match 2026-06-12");

        assertTrue(allocationRepo.existsByResourceAndDate(notebook, allocationDate),
                "The allocation should be persisted in the repository");
    }
}