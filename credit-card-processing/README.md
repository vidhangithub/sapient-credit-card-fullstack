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

## How to test:
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
## Containerisation :
Docker file has been created . To build docker image locally below are the prerequisites and steps
- Docker must be installed locally . Check more info https://docs.docker.com/get-docker/
- If docker is installed please execute below commands
  - cd credit-card-processing  [Go to folder]
  - docker build -t credit-card-processing .  [Image build command]
  - docker run -d -p8080:8080 --name cc-processing-app credit-card-processing [Creating container from image and run it ]
  - Once container running successfully . Application can be accessed at http://localhost:8080
    - username = vidhan
    - password= chandra

## Containerisation  orchestration:
APIs have also been tested in local minikube provided k8s cluster 
- k8s manifest files deployment.yaml ( it also consists Service api-resource), ingress.yaml have been created. 
- To test it locally below are the prerequisites and steps
  - Local k8s cluster setup should be there
  - cd credit-card-processing  [Go to folder]
  - kubectl apply -f deployment.yaml [Command to create deployment and service resources, images would fetched form docker registry https://hub.docker.com/repository/docker/vidhanchandrauk/credit-card-processing]
  - kubectl apply -f ingress.yaml [command to create ingress resource to expose service outside cluster using cluster-ip]
  - minikube ip [command to get minikube ip]
  - Above ip should be registered as host(your desired host name) in your hosts file (C:\Windows\System32\drivers\etc\hosts) as below 
    - In my case it's 172.31.62.24  vidhan-sap-test.com
    - if everything works fine your APIs would be exposed at http://vidhan-sap-test.com/saptest/api/v1/cc-process/credit-cards 