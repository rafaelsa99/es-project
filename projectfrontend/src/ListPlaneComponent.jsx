import ReactMapGL from 'react-map-gl';
import {useState} from 'react';
import React, { Component }from 'react';
import MetroService from './MetroService';
import {Marker, Popup} from 'react-map-gl';

    function Map(Component){
        return function WrappedComponent(props){
        const [viewport, setViewport] = useState({
            latitude: 33.94250107,
            longitude:  -118.4079971,
            width: '100vw',
            height: '100vh',
            zoom: 9.5
        });
        const [selectedVehicle, setSelectedVehicle] = useState(null);
        return <Component {...props} viewport={[viewport, setViewport]} seleted={[selectedVehicle, setSelectedVehicle]} />;
    }
    }

    class ListPlaneComponent extends Component {
        intervalID;
        constructor(){
            super();
            this.state = {
                vehicles :[]
            }
        }   
    
        componentDidMount(){
            this.getData();
        }
        componentWillUnmount() {
            clearTimeout(this.intervalID);
        }
        getData(){
            MetroService.getVehicles().then((res) => {
                this.setState({vehicles: res.data});
                this.intervalID = setTimeout(this.getData.bind(this), 5000);
            }
            
            ); 
        }
        render() {
            const [viewport, setViewport] = this.props.viewport;
            const [selectedVehicle, setSelectedVehicle] = this.props.seleted;
            return (
                <div id="listcomponent">
                    <h1 className="text-center">Metro/Bus in Metropolitan Los Angeles </h1>
                    <ReactMapGL 
                    mapStyle={'mapbox://styles/mapbox/dark-v9'}
                    mapboxApiAccessToken={
                        "pk.eyJ1IjoidmVzdGVyciIsImEiOiJja25idHlzd2oxdjg4MnBwOXpldzRtazYzIn0.YuVQQPP_eM-SSCvbvB2QlA"
                        }
                    {...viewport}
                    onViewportChange={viewport => {
                        setViewport(viewport);
                    }}
                    >
                    {
                        this.state.vehicles.map(vehicles=> ( 
                        <Marker 
                            key={vehicles.callsign} 
                            latitude={vehicles.latitude}
                            longitude={vehicles.longitude}
                        >
                          <button class="marker-btn"
                            onClick={e => {
                                e.preventDefault();
                                setSelectedVehicle(vehicles);
                            }}>
                            <img src="/Bus.png" alt="Bus Icon"/>
                          </button>
                        </Marker>
                     ))} 
                        
                    {selectedVehicle ? (
                        <Popup latitude={selectedVehicle.latitude} longitude={selectedVehicle.longitude}
                        onClose={() => {
                            setSelectedVehicle(null);
                        }}>
                            <div>
                                
                                <p><b>Latitude: </b>{selectedVehicle.latitude}</p>
                                <p><b>Longitude: </b>{selectedVehicle.longitude}</p>
                            </div>
                        </Popup>
                    ) : null}

                    </ReactMapGL>
                    <body>
                    <a href="template/index2.html">template</a>

                    </body>
                   
                    
                    </div>
                
            );
            
        }
    }
    
    export default Map(ListPlaneComponent);