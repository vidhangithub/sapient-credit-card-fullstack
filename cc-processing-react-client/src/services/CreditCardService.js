import axios from 'axios';

const GET_ALL_CREDIT_CARD_API_URL = "http://localhost:8080/api/v1/cc-process/credit-cards";
const CREATE_CREDIT_CARD_API_URL = "http://localhost:8080/api/v1/cc-process/credit-card";

class CreditCardService {

    getCreditCards(){
        return axios.get(GET_ALL_CREDIT_CARD_API_URL, {
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
    };

}

export default new CreditCardService()