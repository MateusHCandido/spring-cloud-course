# Microservices com Spring Boot e Spring Cloud


### Sobre o projeto

Aprendendo sobre estrutura e criação de aplicações de micro serviço, efetuando a criação de um serviço de trabalhadores
onde o micro serviço poderá ser escalável conforme a necessidade.

 Dentro da aplicação será criado um serviço de folha de pagamento, onde sua funcionalidade séra basicamente realizar o
cálculo de pagamento do usuário, onde para ser registrado o serviço de folha de pagamento será necessário acessar o
micro serviço de trabalhadores.

Para autorização será utilizado o protocolo OAuth os tokens do JWT.

 Os micro serviços serão registrados no Eureka, um Server Discovery que permite a implementação de arquiteturas de
microservices. Ele atua como um registro centralizado onde as instâncias de serviços podem se registrar e consultar
informações sobre outros serviços registrados.
 Para auxiliar nas múltiplas requisições, existe o balanceamento e carga, que, para essa aplicação será utilizada
o Ribbon.

 Para servir como roteamento de requisições para os microservices, o API Gateway Zuul será utilizado.
  Pela dependência gerada, devido ao fato de todos os microservices ficarem dependentes da API de roteamento que, em
caso de problemas podem resultar em uma falha geral da aplicação, o famoso SPOF (Single Point Of Failure). Contudo, por
questões didáticas, irei utiliza-lo.

 Conforme for aprendendo mais, irei implementando esta documentação.


### Tecnologias utilizadas
* Feign
* Ribbon
* Hystrix
* OAuth
* Discovery Server Eureka
* API Gateway Zuul
* Java 11
* Spring Boot versão 2.3.4
* Spring Cloud
* Spring Security
* Lombok
* Docker


## [hr-worker](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-worker)

Microservice onde está composta a consulta da base de dados de trabalhadores


## [hr-payroll](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-payroll)

Microservice responsável pelo cálculo dos dias trabalhados e do quanto o trabalhador ganha por dia trabalhado


## [hr-eureka-server](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-eureka-server)

Microsserviço responsável pelo serviço de descoberta das aplicações. Basicamente, é um servidor usado para implementar
padrões de descoberta dos serviços, permitinddo que os microservices se comuniquem. Dentro do servidor também apresenta
a listagem de todos os serviços registrados e também suas instâncias atuais.
 O servidor Eureka é altamente escalável e tolerante a falhas, ou seja, ele permite que novos serviços sejam facilemente
adicionados ou removidos dentro do registro de serviços.


## [hr-api-gateway-zuul](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-api-gateway-zuul)


Este microservice serve como ferramente de roteamento e proxy reverso que atua como um gateway, ou seja, um roteamente, 
para a comunicação entre os microserviços. Ele fornece um ponto central de entrada para as solicitações por parte do
cliente e por ser usado para distribuir o tráfego de entrada para diferentes instâncias de um mesmo serviço ou para 
serviços diferentes.
 "O Zuul permite a implementação de política de segurança, roteamento dinâmico de solicitações, balanceamento de carga,
cache entre outras funcionalidades."


## [hr-user](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-user)

Microserviço responsável por tomar frente da geração de perfís onde contém permissões de acesso. Sendo feito o use de 
solicitações e autenticações do usuário para utilizar os serviços dos demais microservices, através de sua conexão com o
microservice hr-oauth.

## [hr-oauth](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-oauth)

Microserviço que utiliza protocolocos para autenticação e autorização das aplicações WEB e também de APIs. No caso desse
microservice, ele está utilizando o Oauth e o JWT para realizar essas ações.

## [hr-config-server](https://github.com/MateusHCandido/worker-service-infrastructure/tree/main/hr-config-server)

Esse microserviço foi criado com o propósito de estar chamando as configurações de um servidor remoto, onde ele irá verificar
as configurações da aplicação, referente a banco de dados, usernames, password, chaves não registradas na aplicação, mas
sim em uma alocação remota. No caso desta aplicação foi inserida em um repositório privado no meu github.


##### Considerações finais sobre o projeto criado

 Pelo fato de estar realizando o projeto através do curso, onde estava em fase de aprendizado. Foi deixado de lado, em alguns momentos as composições de boas práticas de programação, como testes, camadas de serviço, dto's e também seleções melhores para nomes de objetos e variáveis.
  Em próximos projetos, será apresentado uma melhor forma englobar um projeto utilizando as mesmas ferramentas junto às boas práticas.
