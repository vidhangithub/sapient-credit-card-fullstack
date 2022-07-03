## How to run the bundled application:
- Git clone -> git clone https://github.com/vidhangithub/sapient-credit-card-fullstack.git
  - Build React App
    - cd cc-processing-react-client
    - npm run build
  - Build Spring Boot App
    - cd credit-card-processing
    - mvn package
    - mvn spring-boot:run
- Bundled Application can be accessed on http://localhost:8080 
  - when prompted please pass username 'vidhan' and password 'chandra'
  
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
