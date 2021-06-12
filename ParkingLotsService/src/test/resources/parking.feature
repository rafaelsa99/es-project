Feature: LAMetro 1

    #https://api.metro.net/agencies/lametro/parking/5ba05e7e00000029464d31c1/

    Scenario: Parks are or not free 1
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 349 free parking spaces and 7 disabled free parking spaces test1
        When the event is received through kafka topic "events" test1
        Then I should be told that test1 "Park testPark1 is totally free!"


    Scenario: Parks are or not free 2
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 32 free parking spaces and 6 disabled free parking spaces test2
        When the event is received through kafka topic "events" test2
        Then I should be told that test2 "Park testPark1 has a reduced number of free parking spaces!"


    Scenario: Parks are or not free 3
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 0 free parking spaces and 6 disabled free parking spaces test3
        When the event is received through kafka topic "events" test3
        Then I should be told that test3 "Park testPark1 has the parking spaces full!"

    Scenario: Parks are or not free 4
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 340 free parking spaces and 7 disabled free parking spaces test4
        When the event is received through kafka topic "events" test4
        Then I should be told that test4 "Park testPark1 for disabled is totally free!"


    Scenario: Parks are or not free 5
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 340 free parking spaces and 2 disabled free parking spaces test5
        When the event is received through kafka topic "events" test5
        Then I should be told that test5 "Park testPark1 has a reduced number of free parking spaces for disabled!"


    Scenario: Parks are or not free 6
        Given a parking lot with a total of 349 parking spaces and 7 disabled parking spaces and with 340 free parking spaces and 0 disabled free parking spaces test6
        When the event is received through kafka topic "events" test6
        Then I should be told that test6 "Park testPark1 has the disabled parking spaces full!"
