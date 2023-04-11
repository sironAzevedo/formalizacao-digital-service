### Sobre a aplicação
Essa é uma aplicação de estudo de como penso que seja realizado uma formalização digital, atualmente utilizado em instituição financeira para contratação de produtos como por exemplo Cartão de credito

### Documentation

O projet contém as seguintes tecnologia:
* Spring Boot 2
* Java 17
* Maven
* Docker
* MongoDB
* [Wiremock](https://wiremock.org/)
* Lombok
* Unit tests com Junit e Mockito

URL swagger local:
```
http://localhost:8080/swagger-ui/index.html#/
```

### Executando a aplicação:
Sólicito executar o arquivo docker-compose presente no projeto dentro da pasta docker, pois a aplicação se conecta ao kafka para produzir e consumir mensagens para executaro fluxo de formalização.

* #### Docker-compose:
```
Comando: docker-compose up -d
```

* #### App:
```
mvn spring-boot:run
```

#### Kafka:
Após a execução do docker-compose o mesmo fará o pull das imagens zookeeper, kafka e kafdrop

* Criando tópico no kafka:
  * Como sugestão, utilizar o kafdrop para a criação do seguintes topic:
  * Obs: Para acessar o kafdrop url: http://localhost:19000/
  * Topic:
    * tp-card-processed
    * tp-card-processing
    * tp-cpf-validated
    * tp-cpf-validation

### Mongo DB:
Após a execução do docker-compose o mesmo fará o pull das imagens mongo e mongo-express(SGBD para o mongo).

A aplicação é responsavél por criar o database formalização a custumer, responsavél por guardar os dados do cliente no mongo e para visualizar as tabelas 
acessar a url: http://localhost:8081/ 

### Wiremock:
Estamos utilizando o Wiremock para simular uma integração com o serviço de endereço


### Arquitetura:   
![Alt text](/arch/arch.png "Optional title")  

 