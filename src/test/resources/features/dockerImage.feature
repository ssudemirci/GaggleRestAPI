Feature:Docker image

  Scenario Outline: Verification of Get request methods.
    Given Users Performs Get operation
    Then User should see the success status  as 200
    And  Payload has "<Value>"
    Examples:
      | Value                   |
      | Welcome to the machine. |
