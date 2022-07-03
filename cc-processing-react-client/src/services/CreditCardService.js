import axios from 'axios';

const CREATE_CREDIT_CARD_API_URL = "http://localhost:8080/api/v1/cc-process/credit-card";
const GETALL_CREDIT_CARDs_API_URL = "http://localhost:8080/api/v1/cc-process/credit-cards";

class CreditCardService {

    getCreditCards(){
        return axios.get(GETALL_CREDIT_CARDs_API_URL, {
            auth:{
                username: "vidhan",
                password: "chandra"
            }
        });
    }

    createCreditCard(creditCard){
        return axios.post(CREATE_CREDIT_CARD_API_URL, creditCard, {
            auth:{
                username: "vidhan",
                password: "chandra"
            }
        })
    }

    getCrediCardByNumber(creditCardNumber){
        return axios.get(CREATE_CREDIT_CARD_API_URL + '/' + creditCardNumber, {
            auth:{
                username: "vidhan",
                password: "chandra"
            }
        });
    }

}

export default new CreditCardService()