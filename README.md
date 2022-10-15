## key-processor

![technology Java](https://img.shields.io/badge/technology-java-blue.svg)
![technology Spring Boot](https://img.shields.io/badge/technology-spring-boot.svg)
![technology MySQL](https://img.shields.io/badge/technology-mysql.svg)

[//]: # (![technology MongoDB]&#40;https://img.shields.io/badge/technology-mongo-db.svg&#41;)

### Prerequisites

* [Git](https://git-scm.com/)
* [Java](https://www.java.com/)
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot) 
* [MySQL](https://www.mysql.com/)
* [Docker](https://www.docker.com/products/docker-desktop)
* [SemVer](http://semver.org/)
* [K8s](https://kubernetes.io/)

### Simple design solution

### Instructions
1. To run this project locally first run the docker-compose.yml file of this project, that will initiate a docker container with mysql and another docker container with adminer
2. After this, run this application
3. access http://localhost:8089/swagger-ui/index.html for tests
4. access http://localhost:8080/ user root pass 12345 for sql commands


### Critérios de aceite

Inclusão
1. Deve registrar em banco de dados as informações imputadas
![image](https://user-images.githubusercontent.com/40812575/196010229-057fd6e2-fd48-42d3-9923-5500b0c4e3a6.png)

2) Deve gerar um código de registro único (id), independentemente do tipo de chave
   registrado (celular, e-mail, CPF, CNPJ etc...)
   a) A chave (ID) deve ser no formato UUID.
   ![image](https://user-images.githubusercontent.com/40812575/196010438-75a2fd3e-b559-4a61-9a52-10a4fafc9b9b.png)
   
3) Limitar em até 5 chaves por conta para pessoa física e 20 chaves para pessoa jurídica

4) Não deve permitir o registro de chaves duplicadas. O valor informado no campo VALOR
   CHAVE, não deve existir para outro correntista do banco.
   ![image](https://user-images.githubusercontent.com/40812575/196010358-27d9ee34-6293-4594-935f-1f4d35016a55.png)

   
5) Deve ser registrado a data e hora em que a chave foi registrada
![image](https://user-images.githubusercontent.com/40812575/196010400-8ff6870a-0140-4556-862a-417ce5008e3a.png)


6) Deve validar as regras de cadastro seguindo os tipos e regras abaixo:
   a) Celular:
   i) Deve validar se valor já existe cadastrado
   ii) Deve possuir o código pais
   (1) Deve ser numérico (não aceitar letras)
   (2) Deve ser de até dois dígitos
   (3) Deve iniciar com o símbolo “+”
   iii) Deve possuir DDD
   (1) Deve ser numérico (não aceitar letras)
   (2) Deve ser de até três dígitos
   iv) Número com nove dígitos
   (1) Deve ser numérico (não aceitar letras)
   b) E-mail:
   i) Deve validar se valor já existe cadastrado
   ii) Deve conter o símbolo “@”
   iii) Pode conter valores alfanuméricos
   iv) Maximo de 77 caracteres
   
   
   
   c) CPF:
   i) Deve validar se valor já existe cadastrado
   ii) Deve conter no máximo 11 dígitos
   iii) Deve fazer validação de CPF válido
   iv) Deve aceitar somente números
   
   
   
   d) CNPJ:
   i) Deve validar se valor já existe cadastrado
   ii) Deve conter no máximo 14 dígitos
   iii) Deve fazer validação de CNPJ válido
   iv) Deve aceitar somente números

Corporativo | Interno
e) Chave aleatória:
i) Deve validar se valor já existe cadastrado
ii) Deve aceitar no máximo 36 caracteres alfanuméricos.
f) TIPO CONTA
i) Somente permite os valores (corrente ou poupança)
ii) Deve ser informado obrigatoriamente
iii) Não deve permitir estourar 10 caracteres

g) NÚMERO AGÊNCIA
i) Deve permitir somente valores numéricos
ii) Deve ser informado obrigatoriamente
iii) Não deve permitir estourar 4 digitos

h) NÚMERO CONTA
i) Deve permitir somente valores numéricos
ii) Deve ser informado obrigatoriamente
iii) Não deve permitir estourar 8 digitos
7) Deve respeitar a obrigatoriedade dos campos (tabela 1)
8) Deve exibir mensagem de erro (texto livre), caso regra não seja respeitada
9) Retornar http code 200 caso inclusão seja realizada com sucesso. Retornar no corpo da
   resposta o id gerado [item 2)] deste critério de aceite.
10) Retornar http code 422 caso inclusão não respeite as regras de validação.
