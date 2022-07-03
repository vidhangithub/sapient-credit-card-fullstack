## Environment:
- Java version: 8
- Maven version: 3.8.1
- Spring Boot version: 2.7.1

## Requirements:
Two REST Endpoints must be implemented
-	"Add" will create a new credit card for a given name, card number, and limit
-	Card numbers should be validated using Luhn 10
-	New cards start with a £0 balance
-	for cards not compatible with Luhn 10, return an error
-	"Get all" returns all cards in the system

## Validations:
-	All input and output will be JSON
-	Credit card numbers may vary in length, up to 19 characters
-	Credit card numbers will always be numeric

## How to make server up  from command  prompt:
- Git clone -> git clone https://github.com/vidhangithub/sapient-credit-card-fullstack.git
- cd credit-card-processing
- mvn package
- mvn spring-boot:run

## How to test
- GET url - http://localhost:8080/api/v1/cc-process/credit-cards
- POST url - http://localhost:8080/api/v1/cc-process/credit-card  please refer below for input payload
- As basic auth has been enabled please select Basic auth from Authorization drop down and pass
    - username = vidhan
    - password= chandra
## payload  for post:
Example of credit card data JSON object:
```
 {
        "cardNumber": "5228675657891109",
        "name": "Mr Devidutt",
        "creditLimit": 15000.0
}
```
