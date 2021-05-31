import React, {Component} from "react";
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';
import ScriptTag from 'react-script-tag';

class Home extends React.Component {

    
    
    render() {
      const headletter = {
        fontSize: 20 
      }
        return (
    <html lang="en">
        
        <body >
                
            <div id="home">
                
                    <div class="bgded overlay light" style={{backgroundImage:"url('template/images/exelametro.jpg')"}}>
               
              
                  <div id="pageintro" class="hoc clear" >
                  
    
                  <article>
                  <h3 class="heading" style={headletter} >LAMetro é um sistema que exibe informação sobre autocarros/metro em tempo real. 
                  É possível ver as rotas e paragens disponíveis e a posição de cada autocarro/metro. Além disso, será possível ver a informação sobre a ocupação de alguns parques de estacionamento.<br></br></h3>
                  </article>
              
                  </div>
                </div>
                    <div class="wrapper row3">
                    <main class="hoc container clear"> 
                      
                      <div class="group excerpt">
                        <article class="one_third first"><a class="imgover btmspace-30" href="#"><img src="template/images/exelametro.jpg" alt=""/></a>
                          <h6 class="heading">Visualizar Metros</h6>
                          <footer><a href="#">See Mapping &raquo;</a></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/buslametro.jpg" alt=""/></a>
                          <h6 class="heading">Visualizar Autocarros</h6>
                          <footer><a href="#">See Mapping&raquo;</a></footer>
                        </article>
                        <article class="one_third"><a class="imgover btmspace-30" href="#"><img src="template/images/porto.jpg" alt="" /></a>
                          <h6 class="heading">Definir outras coisas</h6>
                          <footer><a href="#">See Mapping &raquo;</a></footer>
                        </article>
                      </div>
                    
                      
                      <div class="clear"></div>
                    </main>
                  </div>

                  <div class="wrapper row2 bgded overlay" >
                    
                    <section class="hoc container clear"> 
                      
                      <div class="sectiontitle">
                        <h6 class="heading">Porta consequat cras imperdiet</h6>
                        <p>Lobortis erat at sodales libero malesuada vel curabitur maximus</p>
                      </div>
                      <ul class="nospace group overview">
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-headphones"></i></a>
                            <h6 class="heading"><a href="#">Arcu eleifend venenatis</a></h6>
                            <p>Sed non vulputate arcu nullam eu massa vitae urna vulputate efficitur in et&hellip;</p>
                            <footer><a href="#">View Details &raquo;</a></footer>
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-asl-interpreting"></i></a>
                            <h6 class="heading"><a href="#">Metus porta tincidunt</a></h6>
                            <p>Mauris eu tristique aenean dapibus facilisis risus sed vestibulum sed vitae&hellip;</p>
                            <footer><a href="#">View Details &raquo;</a></footer>
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-area-chart"></i></a>
                            <h6 class="heading"><a href="#">Lacus pulvinar euismod</a></h6>
                            <p>Pulvinar nulla elementum massa ut fermentum accumsan nulla luctus at risus&hellip;</p>
                            <footer><a href="#">View Details &raquo;</a></footer>
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-balance-scale"></i></a>
                            <h6 class="heading"><a href="#">Efficitur aliquet morbi</a></h6>
                            <p>Vitae est eu felis ultrices tristique morbi eget libero ultricies semper enim&hellip;</p>
                            <footer><a href="#">View Details &raquo;</a></footer>
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-comments-o"></i></a>
                            <h6 class="heading"><a href="#">Eget tempor interdum</a></h6>
                            <p>Et malesuada fames ac ante ipsum primis in faucibus duis volutpat gravida nisi&hellip;</p>
                            <footer><a href="#">View Details &raquo;</a></footer>
                          </article>
                        </li>
                        <li class="one_third">
                          <article><a href="#"><i class="fa fa-gears"></i></a>
                            <h6 class="heading"><a href="#">Cursus egestas curabitur</a></h6>
                            <p>Aliquam lorem lorem ac iaculis eros pulvinar nec vivamus vestibulum nisl sem&hellip;</p>
                            <footer><a href="#">View Details &raquo;</a></footer>
                          </article>
                        </li>
                      </ul>
                      <footer class="center"><a class="btn" href="#">Quis vehicula nulla</a></footer>
                      
                    </section>
                  </div>
      

          
            </div>
        
        </body>
        
    
  
    </html>
            

        );
    }
}

export default Home;