Feature:GET

  Verification of get operation

  Scenario Outline: Verify if place is being Successfully "<method>" method using "<EndPoint>" endpoint
    Given Users Performs "<method>" operation
    Then User should see the success status "<code>"
    And  Payload "<body>" value is "<value>"
    Examples:
      | method | code | body    | value                   |
      | Post   | 201  | message | Welcome to the machine. |
      | Get    | 200  | message | Welcome to the machine. |
      | Put    | 200  | message | update                  |
      | Patch  | 200  | message | partially               |
     # | Delete | 200  | message |                         |










