import React, {Component} from "react";
import MetroService from './MetroService';
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';
import ReactNotification from 'react-notifications-component'
import {store} from  'react-notifications-component'
import 'react-notifications-component/dist/theme.css'

class Home extends React.Component {
  intervalID;
  constructor(){
      super();
      this.state = {
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
            <div id="home">
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
                <div class="bgded overlay light" style={{backgroundImage:"url('template/images/exelametro.jpg')"}}>
  
                  <div class="wrapper row0">
                  <div id="topbar" class="hoc clear"> 
     
      
      
                  </div>
                  </div>
              
                  <div id="pageintro" class="hoc clear" > 
    
                  <article>
                  <p style={headletter} >LAMetro é um sistema que exibe informação sobre os metros e estações em tempo real. 
                  É possível ver as paragens disponíveis e a posição de cada metro na cidade. Além disso, é possível ver a informação sobre a ocupação de alguns parques de estacionamento.<br></br></p>
                  <footer><btns><Link to="/metro" className="btn btn-primary">Descobre já!</Link></btns></footer>
                  </article>
              
                  </div>
                </div>
                    <div class="wrapper row3">
                    <main class="hoc container clear"> 
                      
                      <div class="group excerpt">
                        <article class="one_third first"><a class="imgover btmspace-30" href="#"><img src="template/images/exelametro.jpg" alt=""/></a>
                          <p>Visualizar Metros!</p>
                          <p></p>
                          <footer><Link to="/metro">Ver Metros</Link></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/buslametro.jpg" alt=""/></a>
                          <p>Visualizar Histórico!</p>
                          <p></p>
                          <footer><Link to="/historico">Ver Histórico</Link></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/porto.jpg" alt="" /></a>
                          <p>Visualizar Parques Estacionamento!</p>
                          <p></p>
                          <footer><Link to="/parques">Ver Parques</Link></footer>
                        </article>
                      </div>
                    
                      
                      <div class="clear"></div>
                    </main>
                  </div>

                  <div class="wrapper row2 bgded overlay" >
                    
                    <section class="hoc container clear"> 
                      
                      <div class="sectiontitle">
                        <p>Sistema LAMetro</p>
                        <p>Os utilizadores podem encontrar informações sobre:</p>
                      </div>
                      <ul class="nospace group overview">
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-subway"></i></a>
                          <p><Link to="/metro">Metros</Link></p>
                            <p>É possível ver informação sobre os metros a circular na cidade!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-check-square-o"></i></a>
                          <p><Link to="/metro">Estações de Metro</Link></p>
                            <p>É possível ver informação sobre as estações de metro da cidade!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-car"></i></a>
                          <p><Link to="/parques">Parques de Estacionamento</Link></p>
                            <p>É possível ver informações sobre os Parques de Estacionamento (Nº lugares disponíveis, ocupados)!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-history"></i></a>
                          <p><Link to="/historico">Histórico</Link></p>
                            <p>É possível ver informação acerca do número médio de metros por rota e do número médio de lugares livres por parque de estacionamento!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-calendar"></i></a>
                          <p><Link to="/parques">Previsões de Chegada</Link></p>
                            <p>É possível ver as previsões de chegada dos próximos metros às estações!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-info-circle"></i></a>
                          <p><Link to="/parques">Ajudar o Utilizador</Link></p>
                            <p>Permite ajudar o utilizador a poupar tempo na procura deste tipo de informação!</p>
                            
                          </article>
                        </li>
                      </ul>
                      
                      
                    </section>
                  </div>
      
      
          
          
            </div>

  
  

        );
    }
}

export default Home;