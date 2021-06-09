Feature: LAMetro 1

    #https://api.metro.net/agencies/lametro/parking/5ba05e7e00000029464d31c1/

    Scenario: Parks are or not free 1
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 349 free parking spaces and 7 disabled free parking spaces
        When the event is received through kafka topic "events"
        Then I should be told that "Park testPark1 is totally free!"


    #Scenario: Parks are or not free 2
    #    Given Parks space is between 50 and 1
    #    When I ask whether there are free parkss
    #    Then I should be told that "has a reduced number of free parking spaces!"

    #Scenario: Parks are or not free 3
    #    Given Parks space is 0
    #    When I ask whether there are free parksss
    #    Then I should be told that "has the parking spaces full!"

    #Scenario: Disabled Parks are or not free 1
    #    Given Disabled parks space is 7
    #    When I ask whether there are free disabled parks
    #    Then I should be told "for disabled is totally free!"

    #Scenario: Disabled Parks are or not free 2
    #    Given Disabled parks space is between 3 and 1
    #    When I ask whether there are free disabled parkss
    #    Then I should be told "has a reduced number of free parking spaces for disabled!"

    #Scenario: Disabled Parks are or not free 3
    #    Given Disabled parks space is 0
    #    When I ask whether there are free disabled parksss
    #    Then I should be told "has the disabled parking spaces full!"

    #######################################################

    #Scenario Outline: Park is or is not free
    #    Given Park space is <space>
    #    When I ask whether park space it's free
    #    Then I should be told "<answer>"
    #
    #    Examples:
    #        | space     | answer                                           |
    #        | 349       | is totally free!                                 |
    #        | 50        | has a reduced number of free parking spaces!     |
    #        | 0         | has the parking spaces full!                     |

    #######################################################
