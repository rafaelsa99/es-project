import React, { Component } from 'react';
import FlightService from './PlaneService';
    
class Events extends Component {
    intervalID;
    constructor(){
        super();
        this.state = {
            events :[]
        }
    }

    componentDidMount(){
        this.getData();
    }
    componentWillUnmount() {
        clearTimeout(this.intervalID);
    }
    getData(){
        FlightService.getEvents().then((res) => {
            this.setState({events: res.data});
            this.intervalID = setTimeout(this.getData.bind(this), 5000);
        }
        );
        }
        render() {
            return (
               <div>
                    <h2 className="text-center">Events</h2>
                    
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Messages</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.events.map(
                                        events=>
                                        <tr key={events}>
                                            <td>{events}</td>
                                            
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
    
    export default Events;