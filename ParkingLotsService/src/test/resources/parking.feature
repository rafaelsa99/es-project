Feature: LAMetro 1

    #https://api.metro.net/agencies/lametro/parking/5ba05e7e00000029464d31c1/

    Scenario: Parks are or not free
        Given Parks space is greater than "<int>"
        When I ask whether there are free parks
        Then I should be told that "Park are Free!"
    

        #Examples:
        #    | int       | answer                    |
        #    | 349       | is totally free!          |
        #    | 50        | has a reduced number of free parking spaces!    |
        #    | 0         | has the parking spaces full!       |

    #######################################################
