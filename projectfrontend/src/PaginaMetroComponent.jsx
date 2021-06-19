import ReactMapGL from 'react-map-gl';
import {useState} from 'react';
import React, { Component }from 'react';
import MetroService from './MetroService';
import {Marker, Popup} from 'react-map-gl';
import ReactNotification from 'react-notifications-component'
import {store} from  'react-notifications-component'
import 'react-notifications-component/dist/theme.css'
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
        const [selectedStop, setSelectedStop] = useState(null);
        
        return <Component {...props} viewport={[viewport, setViewport]} seleted={[selectedVehicle, setSelectedVehicle]} seletedStop={[selectedStop, setSelectedStop]} />;
    }
    }

    class PaginaMetroComponent extends Component {
        intervalID;
        constructor(){
            super();
            this.state = {
                vehicles :[],
                stops:[],
                last :[]
            }
        }   
    
        componentDidMount(){
            this.getData();
        }
        componentWillUnmount() {
            clearTimeout(this.intervalID);
        }
        getData(){
            MetroService.getMetro().then((res) => {
                this.setState({vehicles: res.data});
                this.setState({stops: res.data});
            }
            
            ); 
            MetroService.getLastEvent().then((res) => {
                this.setState({last: res.data});
                this.intervalID = setTimeout(this.getData.bind(this), 5000);
            }
            );
        }
        render() {
            const [viewport, setViewport] = this.props.viewport;
            const [selectedVehicle, setSelectedVehicle] = this.props.seleted;
            const [selectedStop, setSelectedStop] = this.props.seletedStop;
            const headletter = {
                fontSize: 20
        }
            return (
                <div id="listcomponent">
                    {
                        this.state.last.map(event=>
                            store.addNotification({
                                title: "Notificação Parque de Estacionamento",
                                message: event, 
                                type: "info",
                                container: "top-right",
                                insert: "top",
                                animationIn: ["animated", "fadeIn"],
                                animationOut: ["animated", "fadeOut"],

                                dismiss: {
                                    duration: 2000
                                },

                                width: 400
                            })
                        )
                        }
                    <ReactNotification />
                    <p className="text-center" style={headletter} >Metro e Estações da zona metropolitana de Los Angeles</p>
                    
                    
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
                            <img src="/metro2.png" alt="Metro Icon"/>
                          </button>
                        </Marker>
                        ))))} 
                        {
                        this.state.stops.map(agency=> ( 
                        agency.stops.map(stops=> ( 
                        <Marker 
                            key={stops.display_name} 
                            latitude={stops.latitude}
                            longitude={stops.longitude}
                        >
                          <button class="marker-btn"
                            onClick={e => {
                                e.preventDefault();
                                setSelectedStop(stops);
                            }}>
                            <img src="/estacao2.png" alt="Stop Icon"/>
                          </button>
                        </Marker>
                     ))))} 
                        
                        {selectedVehicle ? (
                        <Popup latitude={selectedVehicle.latitude} longitude={selectedVehicle.longitude}
                        onClose={() => {
                            setSelectedVehicle(null);
                        }}>
                            <div>
                                <p><b>Rota: </b>{selectedVehicle.route_name}</p>
                                <p><b>Latitude: </b>{selectedVehicle.latitude}</p>
                                <p><b>Longitude: </b>{selectedVehicle.longitude}</p>
                                <p><b>Direção: </b>{selectedVehicle.heading}</p>
                                <p><b>Atualizado há: </b>{selectedVehicle.seconds_since_report} segundos</p>
                            </div>
                        </Popup>
                    ) : null}

                        {selectedStop ? (
                        <Popup latitude={selectedStop.latitude} longitude={selectedStop.longitude}
                        onClose={() => {
                            setSelectedStop(null);
                        }}>
                            <div>
                                <p><b>Estação: </b>{selectedStop.display_name}</p>
                                <p><b>Latitude: </b>{selectedStop.latitude}</p>
                                <p><b>Longitude: </b>{selectedStop.longitude}</p>
                                <p><b>Próximas Chegadas:</b></p>
                                {selectedStop.predictions.map(prediction=> (
                                    <p>{prediction.route_name}: {prediction.minutes} minutos</p>
                                ))} 
                            </div>
                        </Popup>
                    ) : null}

                    </ReactMapGL>
                    
                   
                    
                    </div>
                
            );
            
        }
    }
    
    export default Map(PaginaMetroComponent);