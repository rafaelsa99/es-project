import React, { Component } from 'react';
import MetroService from './MetroService';
import './App.css';
import { Bar ,Pie, Line,  defaults } from 'react-chartjs-2';
import ReactNotification from 'react-notifications-component'
import {store} from  'react-notifications-component'
import 'react-notifications-component/dist/theme.css'



class Historico extends Component {
  intervalID;
  constructor(){
      super();
      this.state = {
          history :[],
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
      MetroService.getHistory().then((res) => {
          this.setState({history: res.data});
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
      <p className="text-center" style={headletter} >Histórico de Informações da zona metropolitana de Los Angeles</p>

      <Bar 
        data={{
          labels: this.state.history.routes_names,
          datasets: [
            {
              label: "Média de Veículos por Rota",
              data:this.state.history.avg_vehicles,
              backgroundColor: 'rgba(54, 162, 235, 0.2)',
              borderColor: 'rgba(54, 162, 235, 1)',
              borderWidth: 1,
            },
           
          ],
        }}
        height={50}
        width={300}
        options={{
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true,
                },
              },
            ],
          },
          legend: {
            labels: {
              fontSize: 25,
            },
          },
        }}
        
      />

      <Bar 
        data={{
          labels: this.state.history.parks_names,
          datasets: [
            {
              label: "Média de Lugares Livres por Parque de Estacionamento",
              data: this.state.history.avg_parks,
              backgroundColor: 'rgba(255, 206, 86, 0.2)',
              borderColor: 'rgba(255, 206, 86, 1)',
              borderWidth: 1,
            },
           
          ],
        }}
        height={50}
        width={300}
        options={{
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true,
                },
              },
            ],
          },
          legend: {
            labels: {
              fontSize: 25,
            },
          },
        }}
        
      />
    </div>
  )
}
}
    
    export default Historico;