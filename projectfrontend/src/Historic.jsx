import React, { Component } from 'react';
import MetroService from './MetroService';
import './App.css';
import ReactNotification from 'react-notifications-component'
import {store} from  'react-notifications-component'
import 'react-notifications-component/dist/theme.css'
    
class Historic extends Component {
    intervalID;
    constructor(){
        super();
        this.state = {
            parks :[],
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
        MetroService.getParks().then((res) => {
            this.setState({parks: res.data});
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
               <div id="parkinglots">
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
                    <p className="text-center" style={headletter} >Lista de Parques da zona metropolitana de Los Angeles</p>
                    <p><b>Número Total de Parques:</b> 3</p>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Nome do Parque</th>
                                    <th>Nº Total Lugares</th>
                                    <th>Nº Lugares Disponíveis</th>
                                    <th>Nº Total Lugares Para Pessoas com Deficiência</th>
                                    <th>Nº Lugares Disponíveis Para Pessoas com Deficiência</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                    this.state.parks.map(
                                        parks=>
                                        <tr key={parks.name}>
                                            <td>{parks.name}</td>
                                            <td>{parks.total}</td>
                                            <td>{parks.free}</td>
                                            <td>{parks.disabledtotal}</td>
                                            <td>{parks.disabledfree}</td>
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
    
    export default Historic;