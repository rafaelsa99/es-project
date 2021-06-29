# LA Metro Project
Project of the Software Engineering course of the Masters in Informatics Engineering of the University of Aveiro.

## Scope
<b>LAMetro</b> is a system that displays information about buses/metro in real time. On a map it will be possible to see the routes and stops available and the position of each bus/metro. In addition, it will be possible to see information about the occupation of some parking lots, and also historical information about the average number of vehicles per route and the average number of free spaces per parking lot.The application's target audience would include all Los Angeles residents and tourists who need to know the nearest bus/metro.

The information will be obtained via the API https://api.metro.net/, in JSON format.<br>
More detailed information about the API can be found at https://developer.metro.net/api/.

More info on: https://sites.google.com/view/p52-lametro/ </br>
Demonstration video on: https://youtu.be/sGUBn83DSTg

### Main features
- Map with stops and vehicles location, in real time.
- Select specific vehicle and view its location and direction.
- When selecting a stop, see forecasts of vehicle departures per stop.
- View in table format the status of parking spaces.
- View historical information in graphical format.
- View in table format the generated events.


<b>History:</b>
- Average number of free parking spaces per parking lot.
- Average number of vehicles per route.


<b>Events when:</b>
- Park gets reduced capacity (Total / Disabled);
- Park gets full (Total / Disabled);
- Park gets totally empty (Total / Disabled).

## Implementation
The project will cover:
- Design, implement and deploy an IT system using current DevOps best practices
- Use and apply QA through tests 

### System Architecture
LAMetro External API: 
- Static Information:
  - Bus and subway routes;
  - Bus and subway stops;
  - Parking lots;
- Real-time information:
  - Capacity of the parking lots;
  - Arrival forecasts for buses and metros;
  - Bus and subway positions;

Event Flow:
- 4 Kafka Topics:
  - <b>Vehicles</b>: receives the updated vehicle information from the external API, which is then read by the vehicle service. The information consists of the updated latitude, longitude and heading values of the vehicles.
  - <b>Predictions</b>: gets the updated information about the predicted arrival of the vehicles at the stops from the external API, which is then read by the vehicle service. The information consists of the updated arrival forecast values (in minutes and seconds) of the vehicles at the stops.
  - <b>Parking</b>: receives the updated parking lot information from the external API, which is then read by the parking lot service. The information consists of the updated values of the number of free spaces, both for handicapped spaces and for common spaces.
  - <b>Events</b>: receives the defined alarms related to parking lots (Park gets reduced capacity (Total / Disabled), Park gets full, and Park gets totally empty) which are then read by the service handling the alarms.

Applications:
- 4 Services:
  - <b>Management Service</b>: responsible for receiving requests from the web application through the REST API. It responds to the request by communicating with the necessary services to create the message that will be returned to the endpoint in JSON format.
  - <b>Vehicles Service</b>: responsible for consuming the updated information regarding the vehicles from the Vehicles topic, and updating its information. The information stored in the history, in this case the number of vehicles per route, is updated.
  - <b>Parking Lots Service</b>: responsible for consuming the updated information regarding the parking lots from the Parking topic, and updating its information. The information stored in the history, in this case the number of free parking spaces per park, is updated. If the requirements for an event are met (Parking is at reduced capacity (Total / Handicapped), Parking is full (Total / Handicapped), and Parking is totally empty (Total / Handicapped)), an event is sent to the Events topic.
  - <b>Alarms Service</b>: responsible for consuming the new events from the Events topic.
- <b>Static Information</b>:
  - Contains the static information regarding routes, route stops, and parking lots, which is then returned through the endpoint.
- <b>Frontend</b>:
  - Reading data through the REST API endpoints;
  - Pages for real-time subway and bus information, parking lots and history information;

### Implementation Details
#### External API
Responsible for receiving the updated information from the external API (https://api.metro.net/) and sending the updated information to the respective topics. The information is updated every second.
- Technology: Springboot 
- Port: 52041
- Assumptions:
  - Kafka: 192.168.160.18:9092 
- Changes to run locally:
  - Change Kafka to: 172.0.0.16:9092 
- Endpoints to get static information from the API:
  - <b>agencies/</b>: returns information about available agencies. 
    - [{"display_name": "Los Angeles Metro", "id": "lametro", "localtime": "2021-06-25 22:03:56.191321"}, {"display_name": "Los Angeles Metro Rail", "id": "lametro-rail", "localtime": "2021-06-25 22:03:56.191339"}]
  - <b>{agency}/routes/</b>: returns the routes of a particular agency. 
    - {"items": [{"display_name": "Metro A Line (blue)", "id": "801"}, {"display_name": "Metro B Line (red)", "id": "802"}]}
  - {agency}/parkinglots/: returns the parking lots for a given agency. 
    - [{"id": "5b81ee9500000021f0ce8a8b", "name": "APU/Citrus College"}, {"id": "5ba05e7e00000029464d31c1", "name": "Irwindale"}]}
  - <b>{agency}/routes/{route_id}/stops/</b>: returns the stops for a given route.
    - {"items": [{"id": "80120", "display_name": "Grand / Lattc Station", "latitude": 34.0331599, "longitude": -118.26933}, {"id": "80109", "display_name": "Del Amo Station", "latitude": 33.8482199, "longitude": -118.21102}]}
- Format of messages sent to Kafka topics:
  - Topic Vehicles: data model "ItemsVehiclesAgency" in JSON format;
  - Topic Predictions: "ItemsPredictionsStop" data model in JSON format;
  - Topic ParkingLotations: the actual message received from the API is used. Note: The information received from the API is not in JSON format in this case, so the message must be correctly formatted before being sent to the Kafka topic.
#### Static Info
Responsible for getting the static information from the ExternalAPI, and making it available via endpoints to the ManagementService.
- Technology: Springboot 
- Port: 52044 
- Assumptions: 
  - ExternalAPI: http://192.168.160.87:52041/ 
- Changes to run locally: 
  - Change BASE_URL of ExternalAPI to: http://172.0.0.20:8082/ 
- Endpoints to get static information:
  - <b>agencies/</b>: returns information about available agencies. 
    - [{"display_name": "Los Angeles Metro", "id": "lametro", "localtime": "2021-06-25 22:03:56.191321"}, {"display_name": "Los Angeles Metro Rail", "id": "lametro-rail", "localtime": "2021-06-25 22:03:56.191339"}]
  - <b>{agency}/routes/</b>: returns the routes of a particular agency. 
    - {"items":[{"id": "801", "display_name": "Metro A Line (blue)"},{"id": "802", "display_name": "Metro B Line (red)"}]}
  - <b>parkinglots/</b>: returns the parking lots.
    - [{"name":"APU/Citrus College","id":"5b81ee9500000021f0ce8a8b"},{"name":"Irwindale","id":"5ba05e7e00000029464d31c1"}]}
  - <b>{agency}/routes/{route_id}/stops/</b>: returns the stops for a given route. 
    - {"items":[{"id":"80114","display_name":"Firestone Station","latitude":33.9596099,"longitude":-118.2432},{"id":"80108","display_name":"Wardlow Station","latitude":33.8198599,"longitude":-118.19609}]}
#### Vehicles Service
Responsible for consuming the updated information regarding the vehicles from the Vehicles topic, and updating its information. Stores historical information about the number of vehicles per route.
- Technology: Springboot 
- Database:
  - Uses the "VehiclesRoutes" data model;
  - Table: vehiclesRoutes 
  - Automatically generated integer ID
- Port: 52038
- Assumptions: 
  - Kafka: 192.168.160.18:9092
  - By default the URL, Username and Password of the MySQL database is local:
    - url: jdbc:mysql://localhost:3306/lametro?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
    - username: root
    - password: password  
  - The database data to run on the VM is provided in the "docker-compose" file.
- Changes to run locally:
  - Change Kafka to: 172.0.0.16:9092
- Endpoints to get the updated vehicle information:
  - <b>{agency}/vehicles/</b>: returns up-to-date information about an agency's vehicles;
    - {"agency_id":"lametro-rail","items":[{"id":"110","run_id":"801_1_var0","route_id":"801","latitude":33.870222,"longitude":-118.220166,"predictable":true,"seconds_since_report":44,"heading":180. 0},{"id":"111","run_id":"801_0_var0","route_id":"801","latitude":33.790072,"longitude":-118.189292,"predictable":true,"seconds_since_report":113,"heading":0.0}]}
  - <b>{agency}/predictions/</b>: returns the updated arrival predictions of vehicles at the stations of a given agency;
    - [{"agency_id":"lametro-rail","stop_id":"80119","items":[{"route_id":"801","block_id":"101","run_id":"801_1_var0","stop_id":"80119","is_departing":true,"minutes":7,"seconds":429,"trip_id":"53676432"}]}]
  - <b>history/</b>: returns the history information (number of vehicles per route);
    - [{"id":1,"route_id":"801","num_vehicles":9},{"id":2,"route_id":"802","num_vehicles":4},{"id":3,"route_id":"803","num_vehicles":7}]

#### Parking Lots Services
Responsible for consuming the updated parking information from the Parking topic, and updating its information. It stores historical information about the number of free parking spaces per parking lot.
- Technology: Springboot 
- Database: 
  - Uses the "ParkingLotationFree" data model;
  - Table: parkingLotation
  - Automatically generated integer ID
- Tests:
  - Cucumber / Kafka:
    - Given: We create the parking lot and send the object through the producer with the message format in the "parking" topic, in order to be processed by the service.
    - When: We receive the message through the consumer in the "events" topic.
    - Then: We test if the message received was the supposed one.
    - We do these steps six times (three for the normal parkings spaces and the disabled parkings spaces).
  - Database:
    - Regarding the database we don't test, but we do start an "in-memory database" (h2 database) to avoid errors when testing if we don't have any database initialized.
  - Mock MVC:
    - The only test performed was to observe if the endpoint "http://localhost:8083/lotations/" returns status 200, which means that it is operational. 
- Port: 52043
- Assumptions:
  - Kafka: 192.168.160.18:9092
  - By default the URL, Username and Password of the MySQL database is local:
    - url: jdbc:mysql://localhost:3306/lametro?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
    - username: root
    - password: password
  - The database data to run on the VM is provided in the "docker-compose" file.
- Changes to run locally:
  - Change Kafka to: 172.0.0.16:9092
- Endpoints to get updated information from the parking lots:
  - <b>lotations/</b>: returns up-to-date information about the capacity of the parking lots;
    - [{"name":"Irwindale","updated":-1536,"disabledtotal":7,"disabledfree":6,"free":327,"total":349},{"name":"Monrovia","updated":-1534,"disabledtotal":8,"disabledfree":8,"free":313,"total":343}]
  - <b>history/</b>: returns the history information (number of free spaces per parking lot);
      - [{"id":36,"name":"APU/Citrus College","free":145},{"id":37,"name":"Irwindale","free":336},{"id":38,"name":"Monrovia","free":318}]
- Message sent to the topic Kafka:
  - Events Topic: Message composed of "Park <PARK_NAME> <EVENT>";
  - Events are sent when a park gets all seats and handicapped spaces free, when it gets reduced capacity, and when it gets full.
  
#### Alarms Service
Responsible for consuming the new events from the Events topic and making them available through the endpoints.
- Technology: Springboot 
- Port: 52040
- Assumptions:
  - Kafka: 192.168.160.18:9092
- Changes to run locally: 
  - Change Kafka to: 172.0.0.16:9092
- Endpoints to get the events received:
  - <b>events/</b>: returns all received events;
    - ["Park APU/Citrus College for disabled is totally free!", "Park Monrovia for disabled is totally free!", "Park Irwindale for disabled is totally free!"]
  - <b>events/last/</b>: returns the last event received; Note: each event can only be read once. If this event has already been read, no event is returned.
    - ["Park APU/Citrus College for disabled is totally free!"]
    
#### Management Service
Responsible for receiving requests from the web application. Serves as a mediator between the web application requests and the various services. For a request, it gets the necessary information from the other services and builds the message that will be returned.
- Technology: Springboot
- Port: 52042
- Assumptions:
  - VehiclesService: http://192.168.160.87:52038/ 
  - ParkingLotsService: http://192.168.160.87:52043/ 
  - AlarmsService: http://192.168.160.87:52040/ 
  - StaticInfo: http://192.168.160.87:52044/   
  - Frontend CrossOrigin: http://192.168.160.87:52045 
- Changes to run locally:
  - Change BASE_URL of VehiclesService to: http://172.0.0.20:8086/ 
  - Change BASE_URL of ParkingLotsService to: http://172.0.0.20:8083/ 
  - Change BASE_URL of AlarmsService to: http://172.0.0.20:8084/ 
  - Change BASE_URL's StaticInfo's BASE to: http://172.0.0.20:8085/   
  - Change Frontend CrossOrigin: http://localhost:3000 
- Endpoints to get static information:
  - <b>/parkinglots</b>: returns up-to-date information about parking lots and their capacity;
    - [{"name":"Irwindale","updated":-1529,"disabledtotal":7,"disabledfree":6,"free":330,"total":349},{"name":"Monrovia","updated":-1531,"disabledtotal":8,"disabledfree":8,"free":311,"total":343}]
  - <b>/agencies</b>: returns up-to-date information for all agencies, which includes the stops on those agencies' routes, and vehicles;
    - [{"vehicles":[{"id":"110","route_name":"Metro A Line (blue)","latitude":33.847853,"longitude":-118.210824,"seconds_since_report":117,"heading":180. 0}],"stops":[{"display_name":"San Pedro Street Station","latitude":34.0268099,"longitude":-118.2555,"predictions":[{"route_name":"Metro A Line (blue)","minutes":3,"seconds":190}]}]}]
  - <b>/agencies/{agency}</b>: returns up-to-date information for a particular agency, which includes that agency's route stops, and vehicles.
    - [{"vehicles":[{"id":"110","route_name":"Metro A Line (blue)","latitude":33.847853,"longitude":-118.210824,"seconds_since_report":117,"heading":180. 0}],"stops":[{"display_name":"San Pedro Street Station","latitude":34.0268099,"longitude":-118.2555,"predictions":[{"route_name":"Metro A Line (blue)","minutes":3,"seconds":190}]}]}]
  - <b>/events</b>: returns all received events related to the parking lots;
    - ["Park APU/Citrus College for disabled is totally free!", "Park Monrovia for disabled is totally free!"]
  - <b>/events/last</b>: returns the last event related to the parking lots.
    - ["Park Irwindale for disabled is totally free!"]
  - <b>/history</b>: returns the historical information, which includes the average number of vehicles per route and the average number of free spaces per parking lot;
    - {"routes_names":["Metro A Line (blue)", "Metro B Line (red)", "Metro C Line (green)", "Metro L Line (gold)", "Metro D Line (purple)", "Metro E Line (expo)"], "avg_vehicles": [8,4,4,8,2,7],"parks_names":["Irwindale","Monrovia","APU/Citrus College"],"avg_parks":[337,318,166]}

#### Frontend
The approach was to create an interface that was mostly pleasant and intuitive, so that any kind of user could use it without any kind of difficulty. The interface includes a map of the city of Los Angeles which includes several information about the subways and their stations, a set of bar charts where historical information is presented, a table listing the parking lots and their capacity, and finally, alerts were used in order to present a warning if any of the parking lots triggered one of the defined events.
- Technology: React JS
- Libraries and Components:
  - react-map-gl
  - react-chartjs-2
  - react-notifications-component
- Getting the information from the API: 
  - MetroService.js class, using the "axios" library, to return, through a function, the information coming from the API. Seven functions were thus created: getParks(), getAll(), getBus(), getMetro(). getHistory(), getEvents() and getLastEvent().
- Pages:
  - The information presented on the various pages is updated every 5 seconds in order to provide credible, enhanced, but mostly first-hand and live information to users.
  - Homepage (Home.jsx class):  
    - It presents some information about the project, and what features are present in it. You can highlight features such as "View information about the subways running in the city of Los Angeles", "View information about the subway stations in the city of Los Angeles", "View information about the Parking Lots, such as No. of available spaces", "View information about the average number of meters per route and the average number of free spaces per parking lot".
  - Live Information (PaginaMetroComponent.jsx class):
    - Contains a map of the city of Los Angeles with real-time information regarding the meters running in the city (such as their route, latitude and longitude, and how long ago it had been updated), and also information about the metro stations in the city (such as the name of the station, its latitude and longitude, and the expected arrival of the next meters at that station). This information is displayed via a pop-up when you click on the desired station or subway. In order to be able to return this information live, we used the getMetro() function, defined in the MetroService.js class.
  - Car Parks (Historic.jsx class):
    - Displays the existing parking lots in the city of Los Angeles (taking into account our API, since there are many more parks in the city of Los Angeles than are displayed on this page). Information like the name of the park, the total no. of parking spaces, the no. of parking spaces available, the total no. of parking spaces for people with disabilities and the no. of parking spaces available for people with disabilities are presented. In order to be able to return this information, we used the getParks() function defined in the MetroService.js class.
  - History (class Historico.js):
    - Displays, through bar graphs, information such as the average number of meters per route in the city of Los Angeles and the average number of free spaces per parking lot. In order to be able to return this information, we used the getHistory() function defined in the MetroService.js class.
  - Notifications (class Alerts.jsx):
    - Displays information about the events, that is, information that had been presented through the alert pop-up about the availability of a particular parking lot. It's noteworthy the fact that this alert pop-up with information, is displayed in any page of the site, not only in this page of Notifications. In order to be able to return this information, we used the function getEvents() which gets the events that occurred so far, and the function getLastEvent() in order to get only the last event that occurred to be displayed in the alert pop-up. Both functions are defined in the MetroService.js class.
  - The pages that present information about buses and bus stops (ListPlaneComponent.jsx and AllMapa.jsx classes) are hidden from the interface because it was not possible to efficiently present all the information provided by the API.

## Execution
### Preparation
To run locally, changes must be made to the different services. Those changes are described in the implementation details.</br>
The file ``docker-compose-local.yml`` must be used. So, the name of the file must be changed to ```docker-compose.yml ```</br> 

For all springboot projects, the following command must be executed:</br>
```
mvn clean install
``` 

Then, the following command must be executed:</br>
```
docker-compose build
```
### Execution
To run the following command must be executed: </br>
```
docker-compose up
```

