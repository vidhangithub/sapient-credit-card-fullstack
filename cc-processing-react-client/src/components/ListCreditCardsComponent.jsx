import React, { Component } from 'react'
import CreditCardService from '../services/CreditCardService'
import CreateCreditCardComponent from '../components/CreateCreditCardComponent'

class ListCreditCardsComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                creditCards: []
        }
    }

    componentDidMount(){
        CreditCardService.getCreditCards().then((res) => {
            this.setState({ creditCards: res.data});
        });
    }

    render() {
        return (
            <div>
                <CreateCreditCardComponent/>
                 <br></br>
                 <h5 className="text-left">Existing Cards</h5>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Card Number </th>
                                    <th> Name </th>
                                    <th> Balance </th>
                                    <th> Limit </th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.creditCards.map(
                                        creditCard => 
                                        <tr key = {creditCard.cardNumber}>
                                            <td>{creditCard.cardNumber}</td>
                                             <td> { creditCard.name} </td>   
                                             <td> {creditCard.balance}</td>
                                             <td> {creditCard.creditLimit}</td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                 </div>
            </div>
        )
    }
}

export default ListCreditCardsComponent
