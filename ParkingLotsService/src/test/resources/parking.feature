Feature: LAMetro 1

    #https://api.metro.net/agencies/lametro/parking/5ba05e7e00000029464d31c1/

    Scenario: Parks are or not free
        Given Parks space is between 349 and 51
        When I ask whether there are free parks
        Then I should be told that "Park are Free!"
    

        #Examples:
        #    | space     | answer                    |
        #    | 340       | Park are Free!            |
        #    | 9         | Parks are almost Full!    |
        #    | 0         | Parks are all Full!       |

    #######################################################
