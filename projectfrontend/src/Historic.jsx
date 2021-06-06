import React, { Component } from 'react';
import MetroService from './MetroService';
import './App.css';
    
class Historic extends Component {
    intervalID;
    constructor(){
        super();
        this.state = {
            parks :[]
        }
    }

    componentDidMount(){
        this.getData();
    }
    getData(){
        MetroService.getParks().then((res) => {
            this.setState({parks: res.data});
        }
        );
        }
        render() {
        
        
       
                      
            return (
               <div id="parkinglots">
                    <h2 className="text-center">Lista dos PArques de Estacionamento da cidade de LA</h2>
                    <p><b>Número Total de Parques:</b> 3</p>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Nome do Parque</th>
                                    <th>Nº Lugares Disponíveis Para Pessoas com Deficiência</th>
                                    <th>Nº Total Lugares Para Pessoas com Deficiência</th>
                                    <th>Nº Lugares Disponíveis</th>
                                    <th>Nº Total Lugares</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                    this.state.parks.map(
                                        parks=>
                                        <tr key={parks.name}>
                                            <td>{parks.name}</td>
                                            <td>{parks.disabledfree}</td>
                                            <td>{parks.disabledtotal}</td>
                                            <td>{parks.free}</td>
                                            <td>{parks.total}</td>
                                        </tr>
                                    )
                                }
                            </tbody>
                            
                        </table>


                        <article class="one_third"><a class="legenda" href="#"><img src="legenda2.png" alt=""/></a>
                            </article>

                        
                    </div>
                 
                
                </div>
            );
        }
    }
    
    export default Historic;