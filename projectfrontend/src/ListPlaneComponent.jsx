import ReactMapGL from 'react-map-gl';
import {useState} from 'react';
import React, { Component }from 'react';
import FlightService from './PlaneService';
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
        const [selectedPlane, setSelectedPlane] = useState(null);
        return <Component {...props} viewport={[viewport, setViewport]} seleted={[selectedPlane, setSelectedPlane]} />;
    }
    }

    class ListPlaneComponent extends Component {
        intervalID;
        constructor(){
            super();
            this.state = {
                planes :[]
            }
        }   
    
        componentDidMount(){
            this.getData();
        }
        componentWillUnmount() {
            clearTimeout(this.intervalID);
        }
        getData(){
            FlightService.getPlanes().then((res) => {
                this.setState({planes: res.data});
                this.intervalID = setTimeout(this.getData.bind(this), 5000);
            }
            
            ); 
        }
        render() {
            const [viewport, setViewport] = this.props.viewport;
            const [selectedPlane, setSelectedPlane] = this.props.seleted;
            return (
                <div>
                    <h1 className="text-center">Planes in Metropolitan Los Angeles</h1>
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
                        this.state.planes.map(planes=> ( 
                        <Marker 
                            key={planes.callsign} 
                            latitude={planes.latitude}
                            longitude={planes.longitude}
                        >
                          <button class="marker-btn"
                            onClick={e => {
                                e.preventDefault();
                                setSelectedPlane(planes);
                            }}>
                            <img src="/plane_white.svg" alt="Plane Icon"/>
                          </button>
                        </Marker>
                     ))} 
                        
                    {selectedPlane ? (
                        <Popup latitude={selectedPlane.latitude} longitude={selectedPlane.longitude}
                        onClose={() => {
                            setSelectedPlane(null);
                        }}>
                            <div>
                                <h4>{selectedPlane.callsign}</h4>
                                <p><b>Origin Country: </b>{selectedPlane.origin_country}</p>
                                <p><b>Geometric Altitude: </b>{selectedPlane.geo_altitude} meters</p>
                                <p><b>Velocity: </b>{selectedPlane.velocity} m/s</p>
                                <p><b>Latitude: </b>{selectedPlane.latitude}</p>
                                <p><b>Longitude: </b>{selectedPlane.longitude}</p>
                            </div>
                        </Popup>
                    ) : null}

                    </ReactMapGL>
                    <h5><b>Total of planes:</b> {this.state.planes.length}</h5>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Callsign</th>
                                    <th>Origin Country</th>
                                    <th>Geometric Altitude (meters)</th>
                                    <th>Velocity (m/s)</th>
                                    <th>Latitude</th>
                                    <th>Longitude</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.planes.map(
                                        planes=>
                                        <tr key={planes.callsign}>
                                            <td>{planes.callsign}</td>
                                            <td>{planes.origin_country}</td>
                                            <td>{planes.geo_altitude}</td>
                                            <td>{planes.velocity}</td>
                                            <td>{planes.latitude}</td>
                                            <td>{planes.longitude}</td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                    
                    </div>
                
            );
            
        }
    }
    
    export default Map(ListPlaneComponent);