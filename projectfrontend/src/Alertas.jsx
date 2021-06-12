import ReactMapGL from 'react-map-gl';
import {useState} from 'react';
import React, { Component }from 'react';
import MetroService from './MetroService';
import {Marker, Popup} from 'react-map-gl';
import './App.css';
import styled from 'styled-components';
import ee from 'event-emitter';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";


const Container = styled.div`
    background-color: #444;
    color: white;
    padding: 16px;
    position: absolute;
    top: ${props => props.top}px;
    right: 16px;
    z-index: 999;
    transition: top 0.5s ease;
`;

const emitter = new ee();

export const notify = (msg) => {
    emitter.emit('notification', msg);
}
  class Alertas extends Component {
    intervalID;
    constructor(props){
        super(props);
        this.state = {
            top: -100,
            alertas :[],
            msg: '',
        };
        this.timeout = null;
        emitter.on('notification', (msg) =>{
            this.onShow(msg);        
        });
    }


    onShow = (msg) =>{
        if(this.timeout){
            clearTimeout(this.timeout);
            this.setState({ top: -100}, ()=>{
                this.timeout = setTimeout(() => {
                    this.showNotification(msg);
                }, 500);
            });
        }else {
            this.showNotification(msg);
        }
    }

    showNotification = (msg) => {
        this.setState({
            top: 16,
            msg
        }, () => {
            this.timeout = setTimeout(() => {
                this.setState({
                    top: -100,
                });
            }, 3000);
        
        });
    }

    
    
    render() {
            
                return (
               
                   <React.Fragment>
                        <button className="btn btn-primary" onClick={this.onShow}>Clique para Notificar!</button>
                        <Container top={this.state.top}>Lugar Disponível no Parque 2! <i className="fa fa-bell"></i> </Container>
                   </React.Fragment>
                    
                 
                
                
            );
        }
    }
    
    export default Alertas;