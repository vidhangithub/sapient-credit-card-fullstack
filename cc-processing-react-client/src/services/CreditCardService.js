import axios from 'axios';

const CREDIT_CARD_API_BASE_URL = "http://localhost:18444/api/v1/credit-card";

class CreditCardService {

    getCreditCards(){
        return axios.get(CREDIT_CARD_API_BASE_URL, {
            auth:{
                username: "user",
                password: "password"
            }
        });
    }

    createCreditCard(creditCard){
        return axios.post(CREDIT_CARD_API_BASE_URL, creditCard, {
            auth:{
                username: "user",
                password: "password"
            }
        })
    }

    getCrediCardByNumber(creditCardNumber){
        return axios.get(CREDIT_CARD_API_BASE_URL + '/' + creditCardNumber, {
            auth:{
                username: "user",
                password: "password"
            }
        });
    }

}

export default new CreditCardService()