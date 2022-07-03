import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListCreditCardsComponent from './components/ListCreditCardsComponent';
import HeaderComponent from './components/HeaderComponent';
import CreateCreditCardComponent from './components/CreateCreditCardComponent';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" component = {ListCreditCardsComponent}></Route>
                          <Route path = "/credit-cards" component = {ListCreditCardsComponent}></Route>
                          <Route path = "/add-card" component = {CreateCreditCardComponent}></Route>
                    </Switch>
                </div>
              
        </Router>
    </div>
  );
}

export default App;
