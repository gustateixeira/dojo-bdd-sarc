# language: en
Feature: Resource Allocation
  As an IT Academy collaborator
  I want to be able to reserve notebooks, rooms, and labs
  So that students can learn

  Background:
    Given that resources and collaborators are registered in the system

  Scenario: Successful allocation of an available resource
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983"
    When they request the reservation for the date "2026-06-12"
    Then the system should successfully confirm the allocation
    
  Scenario: Unsuccessful allocation of an unavaiable resource
    Given that collaborator "10002020" wants to allocate the notebook "CEPES1983"
    When they request the reservation for the date "2026-06-12"
    Then the system should deny the allocation

  Scenario: Unsuccessful allocation of a past date
    Given that collaborator "10002020" wants to allocate the lab "LAB123"
    When they request the reservation for a past date, e.g.: "2025-05-12"
    Then the system should deny the allocation

  Scenario: Successful allocation of both notebook and room for the same date
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983"
    And wants to allocate the lab "LAB112"
    When they request the reservation for the date "2026-06-12"
    Then the system should successfully confirm the allocations

  Scenario: Unsuccessful allocation of a notebook and a lab 
    Given that the collaborator "10002020" wants to allocate the room "R112"
    And wants to allocate the lab "LAB112"
    When they request the reservation for the date "2026-06-12"
    Then the system should deny the allocations
