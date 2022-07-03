import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ListCreditCardsComponent from './components/ListCreditCardsComponent';
import HeaderComponent from './components/HeaderComponent';
import CreateCreditCardComponent from './components/CreateCreditCardComponent';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Routes> 
                          <Route path = "/" component = {ListCreditCardsComponent}></Route>
                          <Route path = "/credit-cards" component = {ListCreditCardsComponent}></Route>
                          <Route path = "/add-card" component = {CreateCreditCardComponent}></Route>
                    </Routes>
                </div>
              
        </Router>
    </div>
  );
}

export default App;
