# language: en
Feature: Resource Allocation
  As an IT Academy collaborator
  I want to be able to reserve notebooks, rooms, and labs
  So that students can learn

  Background:
    Given that resources and collaborators are registered in the system

  Scenario: Successful allocation of an available resource
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983"
    When they request the reservation for a valid date
    Then the system should successfully confirm the allocation
    
  Scenario: Unsuccessful allocation of an unavaiable resource
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983"
    When they request the reservation for a valid date
    And the notebook "CEPES1983" is already allocated
    Then the system should deny the allocation

  Scenario: Unsuccessful allocation of a past date
    Given that the collaborator "10002020" wants to allocate the lab "312"
    When they request the reservation for a past date
    Then the system should deny the allocation

  Scenario: Successful allocation of both notebook and room for the same date
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983" and the room "512" 
    When they request the reservations for a valid date
    Then the system should successfully confirm both allocations

  Scenario: Unsuccessful allocation of a notebook and a lab for the same date
    Given that the collaborator "10002020" wants to allocate the notebook "CEPES1983" and the lab "312"
    When they request the reservations for a valid date
    Then the system should deny the allocations

  Scenario:  Unsuccessful allocation of a same resource type on the same date
    Given that the collaborator "10002020" wants to allocate the resource "CEPES1983" and the resource "CEPES1995"
    When they request the reservations for a valid date
    Then the system should deny the allocations