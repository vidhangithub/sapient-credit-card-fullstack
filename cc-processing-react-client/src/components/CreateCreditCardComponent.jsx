import React, { Component } from 'react'
import CreditCardService from '../services/CreditCardService';

class CreateCreditCardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: '',
            cardNumber: '',
            creditLimit: '',
            errorMessage: '',
            successMessage: ''
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeCardNumberHandler = this.changeCardNumberHandler.bind(this);
        this.changeLimitHandler = this.changeLimitHandler.bind(this);
        this.saveCreditCard = this.saveCreditCard.bind(this);
    }

    saveCreditCard = (e) => {
        this.setState({errorMessage: ''})
        this.setState({successMessage: ''})
        e.preventDefault();
        let creditCard = {name: this.state.name, cardNumber: this.state.cardNumber, creditLimit: this.state.creditLimit};
        console.log('creditCard => ' + JSON.stringify(creditCard));

        CreditCardService.createCreditCard(creditCard).then(res => {
           // this.props.history.push('/credit-cards');
           this.setState({successMessage: 'Success!'})
           window.location.reload();
        }).catch((err => {
            this.setState({errorMessage: err.response.data.message});
        }));
    }
    
    changeNameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    changeCardNumberHandler= (event) => {
        this.setState({cardNumber: event.target.value});
    }

    changeLimitHandler= (event) => {
        this.setState({creditLimit: event.target.value});
    }

    cancel(){
        this.props.history.push('/credit-cards');
    }

    getTitle(){ 
        return <h4 className="text-center">Add Card</h4>
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Name </label>
                                            <input placeholder="Name" name="name" className="form-control" 
                                                value={this.state.name} onChange={this.changeNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Card Number </label>
                                            <input placeholder="Numeric Card Number" name="cardNumber" className="form-control" 
                                                value={this.state.cardNumber} onChange={this.changeCardNumberHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Limit </label>
                                            <input placeholder="Credit Limit" name="creditLimit" className="form-control" 
                                                value={this.state.creditLimit} onChange={this.changeLimitHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveCreditCard}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                            { this.state.errorMessage &&
                                <h6 className="error"> { this.state.errorMessage } </h6>  }
                            { this.state.successMessage &&
                                <h6 className="success"> { this.state.successMessage } </h6>  }
                        </div>


                   </div>
            </div>
        )
    }
}

export default CreateCreditCardComponent
