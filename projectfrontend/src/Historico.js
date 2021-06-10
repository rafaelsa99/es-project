import React, { Component } from 'react';
import MetroService from './MetroService';
import './App.css';
import { Bar ,Pie, Line,  defaults } from 'react-chartjs-2';
  



class Historico extends Component {
  intervalID;
  constructor(){
      super();
      this.state = {
          vehicles :[],
          stops:[]
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
      <p className="text-center" style={headletter} >Histórico de Informações da zona metropolitana de Los Angeles</p>

      <Line 
        data={{
          labels: ['Linha A', 'Linha B', 'Linha C', 'Linha D', 'Linha E', 'Linha F'],
          datasets: [
            {

              data: [12, 19, 3, 5, 2, 3],
              backgroundColor: [
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(54, 162, 235, 0.2)',
              ],
              borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(54, 162, 235, 1)',
              ],
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
          labels: ['Linha A', 'Linha B', 'Linha C', 'Linha D', 'Linha E', 'Linha F'],
          datasets: [
            {

              data: [12, 19, 3, 5, 2, 3],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
              ],
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