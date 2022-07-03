## Environment:
- Java version: 8
- Maven version: 3.8.1
- Spring Boot version: 2.7.1

## Data:
Example of credit card data JSON object:
```
 {
        "cardNumber": "5228675657891109",
        "name": "Mr Devidutt",
        "creditLimit": 15000.0
}
```

## Requirements:
Two REST Endpoints must be implemented
-	"Add" will create a new credit card for a given name, card number, and limit
-	Card numbers should be validated using Luhn 10
-	New cards start with a £0 balance
-	for cards not compatible with Luhn 10, return an error
-	"Get all" returns all cards in the system

##Validations
-	All input and output will be JSON
-	Credit card numbers may vary in length, up to 19 characters
	Credit card numbers will always be numeric
