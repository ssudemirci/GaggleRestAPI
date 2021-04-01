Feature:Docker image

  Scenario Outline: Verification of Get request methods.
    Given Users Performs Get operation
    Then User should see the success status  as 200
    And  Docker Payload "<body>" value is "<value>"
    Examples:
      | body    | value                   |
      | message | Welcome to the machine. |