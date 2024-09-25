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
3. docker run -it -p 8080:8080  kaviya1996/welcome:2.0

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
   


Deploy application using Helm:
Helm helps you manage Kubernetes applications — Helm Charts help you define, install, 
and upgrade even the most complex Kubernetes application. 
In laymen terms, Helm has a template for the k8s manifest files under templates folder, we just have to update 
the values.yaml file with our custom values and modify any yaml file if needed.
We can have different charts for different releases like dev, test, prod.
Managing the releases becomes easy by using Helm chart, if we want to update any values directly change in values.yaml 
and upgrade the release. we can roll back to previous release easily. Helm remembers everything.
1. KillerCoda environment with Kubernetes and Helm installed
2. Create helm chart - **helm create welcome-app** -> this will generate welcome-app folder structure in our directory
3. Create multiple version(tag) of your java application and push it into docker hub
4. Edit values.yaml to have our application details. Update image name, tag, service type and port
5. **helm install welcome-app /root/welcome**
6. Once installation is done use below commands to port forward your traffic
   export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=welcome,app.kubernetes.io/instance=welcome-app" -o jsonpath="{.items[0].metadata.name}")
   export CONTAINER_PORT=$(kubectl get pod --namespace default $POD_NAME -o jsonpath="{.spec.containers[0].ports[0].containerPort}")
   echo "Visit http://127.0.0.1:8080 to use your application"
   kubectl --namespace default port-forward $POD_NAME 8080:$CONTAINER_PORT
7. Use **kubectl get all**/po/rs/service to see the cluster created by helm using image mentioned
8. curl http://localhost:8080 - To access the application in new tab
9. helm list - give welcome-app chart  
10. update the values.yaml then do - **helm upgrade welcome-app /root/welcome-app**
11. **helm list** - give welcome-app with Revision 2
12. create values.test.yaml with properties related to test in the same folder as values.yaml
13. **Create helm create welcome-test**
14. **helm install welcome-test /root/welcome-app -f values.test.yaml**
15. **kubectl get pods** will return pods for both dev and test
16. **helm uninstall welcome-app** - to delete a release
17. updated values.test.yaml to have 2 replicas
18. **helm upgrade welcome-test /root/welcome-app -f values.test.yaml**
19. **helm ls** - revision 2.0
20. **helm rollback welcome-test 1** 
21. Now **kubectl get pods** will have 3 replicas running
22. **helm history welcome-test**
   


