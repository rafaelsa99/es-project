import React from 'react';
import './App.css';

import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";

function Nav() {
    

    return (
        
        <nav>
            <link href="template/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all"></link>
            <div class="wrapper row1"></div>
                  <header id="header" class="hoc clear"> 
                    
                    <div id="logo" class="fl_left">
                      <h1><Link to="/">LAMetro</Link></h1>
                    </div>
                    <nav id="mainav" class="fl_right">
                      <ul class="clear">
                        <li class="active"><Link to="/">Página Inicial</Link></li>
                        
                        <li ><Link to="/metro">Informação Live</Link></li>
                        <li ><Link to="/parques">Parques</Link></li>
                        <li ><Link to="/historico">Histórico</Link></li>
                      </ul>
                    </nav>
      
                    </header>
            
        </nav>
    )
}
export default Nav;