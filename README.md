## key-processor

![technology Java](https://img.shields.io/badge/technology-java-blue.svg)
![technology Spring Boot](https://img.shields.io/badge/technology-spring-boot.svg)
![technology MySQL](https://img.shields.io/badge/technology-mysql.svg)
![technology Swagger](https://img.shields.io/badge/tetchnology-swagger-io.svg)

### Prerequisites

* [Git](https://git-scm.com/)
* [Java](https://www.java.com/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com/)
* [Docker](https://www.docker.com/products/docker-desktop)
* [SemVer](http://semver.org/)
* [Swagger](https://swagger.io/)
* [K8s](https://kubernetes.io/)

### Instructions
1. To run this project locally first run the docker-compose.yml file of this project, that will initiate a docker container with mysql and another docker container with adminer
2. After this, run this application
3. Access http://localhost:8089/swagger-ui/index.html for tests
4. Access http://localhost:8080/ with user root and pass 12345 for sql commands
5. There is a dockerfile for this application, you also can create an image and add to docker-compose.yml

### Tests
#### Functional tests:
- Swagger + MySQL + Adminer;
- Account agency that begin with 5 or 6 will be considered PF, the rest will be considered PJ;

#### Unit tests:
- Mockito + JUnit5;
- RestAssured;

 
### Issues 
- [x] creation using email
- [x] creation using aleatory key
- [x] creation using cpf
- [x] creation using cnpj
- [ ] creation using celular
- [x] update
- [x] inactivation using id
- [x] get by id
- [x] get by type
- [x] get by client name
- [x] get by client last name 
- [x] get by agency
- [x] get by account
- [x] get by dynamic query
- [x] swagger
- [x] db using docker
- [x] dockerfile for this application
- [x] 90% of unit tests
- [x] k8s
- [x] actuator and prometheus
- [ ] grafana
- [ ] api documentation
- [ ] db documentation
- [ ] code documentation
- [ ] tests with rest-assured
