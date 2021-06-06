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
                  <h3 class="heading" style={headletter} >LAMetro é um sistema que exibe informação sobre autocarros/metro em tempo real. 
                  É possível ver as rotas e paragens disponíveis e a posição de cada autocarro/metro. Além disso, será possível ver a informação sobre a ocupação de alguns parques de estacionamento.<br></br></h3>
                  <footer><a class="btn" href="#">Descobre já!</a></footer>
                  </article>
              
                  </div>
                </div>
                    <div class="wrapper row3">
                    <main class="hoc container clear"> 
                      
                      <div class="group excerpt">
                        <article class="one_third first"><a class="imgover btmspace-30" href="#"><img src="template/images/exelametro.jpg" alt=""/></a>
                          <h6 class="heading">Visualizar Metros</h6>
                          <p></p>
                          <footer><a href="#">Verificar &raquo;</a></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/buslametro.jpg" alt=""/></a>
                          <h6 class="heading">Visualizar Autocarros</h6>
                          <p></p>
                          <footer><a href="#">Verificar &raquo;</a></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/porto.jpg" alt="" /></a>
                          <h6 class="heading">Visualizar Parques Estacionamento</h6>
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
                        <h6 class="heading">Sistema LAMetro</h6>
                        <p>Os utilizadores podem encontrar informações sobre:</p>
                      </div>
                      <ul class="nospace group overview">
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-bus"></i></a>
                            <h6 class="heading"><a href="#">Autocarros e Paragens</a></h6>
                            <p>Verificar informação sobre os Autocarros da cidade e das suas paragens!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-train"></i></a>
                            <h6 class="heading"><a href="#">Metro e Estações</a></h6>
                            <p>Verificar informação sobre os Metros da cidade e das suas Estações!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-info-circle"></i></a>
                            <h6 class="heading"><a href="#">Parques de Estacionamento</a></h6>
                            <p>Pesquisar informações sobre os Parques de Estacionamento (Nº lugares disponíveis, ocupados)!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-arrows"></i></a>
                            <h6 class="heading"><a href="#">Rotas e Trajetórias</a></h6>
                            <p>É possível observar a trajetória dos Autocarros e dos Metros da cidade, bem como ver a rota qu estes vão percorrer!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-calendar"></i></a>
                            <h6 class="heading"><a href="#">Prever Tempo De Viagem</a></h6>
                            <p>É possível verificar o tempo de duração das viagens dos autocarros em cada paragem!</p>
                            
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-check-square-o"></i></a>
                            <h6 class="heading"><a href="#">Ajudar o Utilizador</a></h6>
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