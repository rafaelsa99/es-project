import './App.css';
import Nav from './Nav';
import ListPlaneComponent from './ListPlaneComponent';
import Historic from './Historic';
import Events from './Events';
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
      <Route path="/" exact component={ListPlaneComponent}/>
      <Route path="/historic" component={Historic}/>
      <Route path="/events" component={Events}/>
    </Switch>
    
    
    </div>
    </Router>
  );
}

export default App;
