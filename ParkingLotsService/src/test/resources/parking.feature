Feature: LAMetro 1

    #https://api.metro.net/agencies/lametro/parking/5ba05e7e00000029464d31c1/

    Scenario: Parks are or not free 1
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 349 free parking spaces and 6 disabled free parking spaces
        When the event is received through kafka topic "events"
        Then I should be told that "Park testPark1 is totally free!"
        #Then I should be told that2 "Park testPark1 for disabled is totally free!"


    Scenario: Parks are or not free 2
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 32 free parking spaces and 6 disabled free parking spacess
        When the event is received through kafka topic "eventss"
        Then I should be told thatt "Park testPark1 has a reduced number of free parking spaces!"


    Scenario: Parks are or not free 3
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 0 free parking spaces and 6 disabled free parking spacesss
        When the event is received through kafka topic "eventsss"
        Then I should be told thattt "Park testPark1 has the parking spaces full!"

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