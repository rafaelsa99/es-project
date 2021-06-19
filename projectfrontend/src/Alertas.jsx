import React, { Component } from 'react';
import MetroService from './MetroService';
import ReactNotification from 'react-notifications-component'
import {store} from  'react-notifications-component'
import 'react-notifications-component/dist/theme.css'

class Events extends Component {
    intervalID;
    constructor(){
        super();
        this.state = {
            events :[],
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
        MetroService.getEvents().then((res) => {
            this.setState({events: res.data});
        }
        );
        MetroService.getLastEvent().then((res) => {
            this.setState({last: res.data});
            this.intervalID = setTimeout(this.getData.bind(this), 5000);
        }
        );
        }
        render() {

            const headletter = {
                fontSize: 20
        }

            return (
               <div>
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
                    <p className="text-center" style={headletter} >Notificações</p>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                   <td>Eventos dos Parques de Estacionamento</td>
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