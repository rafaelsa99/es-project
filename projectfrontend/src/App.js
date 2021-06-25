import './App.css';
import Nav from './Nav';
import ListPlaneComponent from './ListPlaneComponent';
import Historic from './Historic';
import Historico from './Historico';
import Home from './Home.jsx';
import PaginaMetroComponent from './PaginaMetroComponent';
import AllMapa from './AllMapa';
import Alertas, { notify } from './Alertas';
import styled from 'styled-components';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";

const Container = styled.div`
`;

function App() {
  return (
    
    <Router>
    <div className="App">
      <Container>
    <Nav />

    <Switch>
    
      <Route path="/" exact component={Home} />
      <Route path="/autocarro" component={ListPlaneComponent}/>
      <Route path="/historico" component={Historico}/>
      <Route path="/parques" component={Historic}/>
      <Route path="/metro" component={PaginaMetroComponent}/>
      <Route path="/alertas" component={Alertas}/>
      

    </Switch>
    </Container>
    
    </div>
    </Router>
    
  );
}
  
export default App;
