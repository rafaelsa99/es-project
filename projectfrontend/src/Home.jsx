import React, {Component} from "react";
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';

class Home extends React.Component {
    
    render() {
      const headletter = {
        fontSize: 20 
      }
        return (
            <div id="home">
                
                <div class="bgded overlay light" style={{backgroundImage:"url('template/images/exelametro.jpg')"}}>
  
                  <div class="wrapper row0">
                  <div id="topbar" class="hoc clear"> 
     
      
      
                  </div>
                  </div>
              
                  <div id="pageintro" class="hoc clear" > 
    
                  <article>
                  <p style={headletter} >LAMetro é um sistema que exibe informação sobre os metros e estações em tempo real. 
                  É possível ver as rotas e paragens disponíveis e a posição de cada autocarro/metro. Além disso, será possível ver a informação sobre a ocupação de alguns parques de estacionamento.<br></br></p>
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
                          <footer><a href="#">Verificar &raquo;</a></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/buslametro.jpg" alt=""/></a>
                          <p>Visualizar Autocarros!</p>
                          <p></p>
                          <footer><a href="#">Verificar &raquo;</a></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/porto.jpg" alt="" /></a>
                          <p>Visualizar Parques Estacionamento!</p>
                          <p></p>
                          <footer><a href="#">Verificar &raquo;</a></footer>
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
                          <article><a href="#"><i class="fa fa-bus"></i></a>
                          <p><a href="#">Autocarros e Paragens</a></p>
                            <p>Verificar informação sobre os Autocarros da cidade e das suas paragens!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-train"></i></a>
                          <p><a href="#">Metro e Estações</a></p>
                            <p>Verificar informação sobre os Metros da cidade e das suas Estações!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-info-circle"></i></a>
                          <p><a href="#">Parques de Estacionamento</a></p>
                            <p>Pesquisar informações sobre os Parques de Estacionamento (Nº lugares disponíveis, ocupados)!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-arrows"></i></a>
                          <p><a href="#">Rotas e Trajetórias</a></p>
                            <p>É possível observar a trajetória dos Autocarros e dos Metros da cidade, bem como ver a rota qu estes vão percorrer!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-calendar"></i></a>
                          <p><a href="#">Prever Tempo de Viagem</a></p>
                            <p>É possível verificar o tempo de duração das viagens dos autocarros em cada paragem!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-check-square-o"></i></a>
                          <p><a href="#">Ajudar o Utilizador</a></p>
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