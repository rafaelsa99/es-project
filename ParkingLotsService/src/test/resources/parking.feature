Feature: LAMetro 1

    #https://api.metro.net/agencies/lametro/parking/5ba05e7e00000029464d31c1/

    Scenario: Parks are or not free 1
        Given Parks space is 349
        When I ask whether there are free parks
        Then I should be told that "is totally free!"

    Scenario: Parks are or not free 2
        Given Parks space is between 50 and 1
        When I ask whether there are free parks
        Then I should be told that "has a reduced number of free parking spaces!"

    Scenario: Parks are or not free 3
        Given Parks space is 0
        When I ask whether there are free parks
        Then I should be told that "has the parking spaces full!"
    

        #Examples:
        #    | int       | answer                                           |
        #    | 349       | is totally free!                                 |
        #    | 50        | has a reduced number of free parking spaces!     |
        #    | 0         | has the parking spaces full!                     |

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
