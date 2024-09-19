Simple Java application based on Spring Boot.
Endpoints available: 
/ - Welcome message
/quote - Random quote
/story - Random story

Steps to clone repo:
1. git clone https://github.com/KaviyaMani/random-quotes.git or download it as zip and extract
2. Open the project in Intellij
3. Once maven resolved dependencies. Run mvn install
4. This will create jar under target folder

Dockerize the application:
1. docker build -t repo/welcome:latest .
2. docker push repo/welcome

Create Cluster in local:
Faced multiple errors related to permission and system requirements with installation of kubeadm, kind and minikube.
Proceeding with Killercoda with empty Kubeadm cluster with two 2GB nodes

Using Killercoda -
1. Killercoda Playgrounds -> Kubernetes 1.30 -> create 1 master node and 1 worker node
2. kubectl get nodes - To check the nodes
3. kubectl get pods - No pods in default namespace
4. kubectl get pods -n kube-system - Kube system management pods to manage orchestration. It is kept in separate namespace for isolation
5. Create deployment
   Create file using vi deployment.yaml -> i -> paste the content from k8s/deployment.yaml -> esc -> wq -> enter
   kubectl apply -f deployment.yaml
   kubectl get deployments - deployment will be created with name quotes
   kubectl get pods - 2 pods will be running
6. Create service to expose our application
   Create file using vi service.yaml -> i -> paste the content from k8s/service.yaml -> esc -> wq -> enter
   kubectl apply -f service.yaml
   kubectl get service - service will be created with name quotes-service
7. Access the application
   ****with NodePort: Access the endpoints using Node IP and Port
   kubectl port-forward service/quotes-service 80:80
   Open new terminal in the same cluster curl http://localhost:80 -> Hello!!! message will appear
   curl http://localhost:80/quote - random quote will appear
   OR
   "curl localhost:32642" with node port 
   OR
   "curl 172.30.1.2:32642" with node ip and node port
   
   ****with cluster IP - static IP associated with service at the time of creation, and it won't change. Service will 
                           listen to the Ip's pods are running. we can see them by describe service with key Endpoints
   curl 10.103.102.226:8080 - can access using clusterIP and application port
8. Liveness and Readiness probes
   configured containers to have liveness and readiness probes in k8s/deployment.yaml 
   
   


