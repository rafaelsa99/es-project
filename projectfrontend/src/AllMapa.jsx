import ReactMapGL from 'react-map-gl';
import {useState} from 'react';
import React, { Component }from 'react';
import MetroService from './MetroService';
import {Marker, Popup} from 'react-map-gl';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";


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

    class AllMapa extends Component {
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
            MetroService.getBus().then((res) => {
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
                    
                    <div className="btns" >
                        <Link to="/live" className="btn btn-primary">All</Link>
                        <Link to="/autocarro" className="btn btn-primary">Bus/Stops</Link>
                        <Link to="/metro" className="btn btn-primary">Metro/Stations</Link>
                        
                    </div>
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
                        this.state.vehicles.map(agency=> ( 
                        agency.vehicles.map(vehicles=> ( 
                        <Marker 
                            key={vehicles.id} 
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
                     ))))} 
                        
                    {selectedVehicle ? (
                        <Popup latitude={selectedVehicle.latitude} longitude={selectedVehicle.longitude}
                        onClose={() => {
                            setSelectedVehicle(null);
                        }}>
                            <div>
                                
                                <p><b>Latitude: </b>{selectedVehicle.latitude}</p>
                                <p><b>Longitude: </b>{selectedVehicle.longitude}</p>
                                <p><b>Atualizado à (s): </b>{selectedVehicle.seconds_since_report}</p>
                            </div>
                        </Popup>
                    ) : null}

                    </ReactMapGL>
                    
                   
                    
                    </div>
                
            );
            
        }
    }
    
    export default Map(AllMapa);