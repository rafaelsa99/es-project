import React from 'react';
import './App.css';
import ScriptTag from 'react-script-tag';

import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";;

function Nav() {
    

    return (
    <body>
    
        
        <nav >
        <div class="bgded overlay light">
            <div class="wrapper row1"  ></div>
                  <header id="header" class="hoc clear" >
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
                <link href={"template/layout/styles/layout.css"} rel={"stylesheet"} type={"text/css"} media={"all"}/>
                  
                    
                    <div id="logo" class="fl_left" >
                      <h1 style={{ color: 'black' }}><Link to="/">LAMetro</Link></h1>
                    </div>
                    <nav id="mainav" class="fl_right">
                      <ul class="clear">
                        <li style={{ color: 'black' }}><Link to="/">Home</Link></li>
                        
                        <li ><Link to="/live">Live Data</Link></li>
                        <li ><Link to="/historic">Histórico</Link></li>
                      </ul>
                    </nav>
      
                    </header>
            </div>
            
        </nav>
        <ScriptTag  src="template/layout/scripts/jquery.min.js"/>
            <ScriptTag src="layout/scripts/jquery.backtotop.js"/>
            <ScriptTag src="layout/scripts/jquery.mobilemenu.js"/>
    </body>
    )
}
export default Nav;