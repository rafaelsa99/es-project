import './App.css';
import Nav from './Nav';
import ListPlaneComponent from './ListPlaneComponent';
import Historic from './Historic';
import Events from './Events';
import Home from './Home.jsx';
import PaginaMetroComponent from './PaginaMetroComponent';
import AllMapa from './AllMapa';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";


function App() {
  return (
    <Router>
    <div className="App">
    <Nav />

    <Switch>
      <Route path="/" exact component={Home}/>
      <Route path="/autocarro" component={ListPlaneComponent}/>
      <Route path="/live" component={AllMapa}/>
      <Route path="/parques" component={Historic}/>
      <Route path="/metro" component={PaginaMetroComponent}/>
      

    </Switch>
    
    
    </div>
    </Router>
  );
}
  
export default App;
