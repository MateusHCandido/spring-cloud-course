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


## hr-worker

### Main Class

#### HrWorkerApplication

Dentro da classe, onde é iniciada a aplicação do Spring Boot. 
Nela também se encontra a anotação `@EnableEurekaClient`, que habilita as funções do cliente da aplicação


### Domain Classes

#### EmployeeWorker

#### Atributos:
- workerId (tipo Long)
- nameOfWorker (tipo String)
- dailyIncome (tipo Double)


### Repository Classes

#### WorkerRepository
`public interface WorkerRepository extends JpaRepository<EmployeeWorker, Long>`


### Resource Classes

#### WorkerController

`paht = /workers`

#### Métodos da Classe WorkerController
-  `@GetMapping
    public ResponseEntity<List<EmployeeWorker>> listAllWorkers()`

Método do tipo GET que serve para listar todos os trabalhadores cadastrados.

- `@GetMapping(path = "/{workerId}")
  public ResponseEntity<EmployeeWorker> findWorkerById(@PathVariable Long workerId)`

Método do tipo GET que efetua a busca de um trabalhador através do seu ID.


## hr-payroll

### Domain Classes

#### PaymentVerifier

#### Atributos:
- nameOfWorker (tipo String)
- dailyIncome (tipo Double)
- workedDays (tipo Integer)

#### Métodos:
- getTotalReceivable()
Calcula o quanto ganha por dia e quantos dias foram trabalhados

#### EmployeeWorker
#### Atributos:
- nameOfWorker (tipo String)
- dailyIncome (tipo Double)
- workedDays (tipo Integer)

### Services Classes

#### PaymentCalculator

#### Métodos da Classe PaymentCalculator

- `public PaymentVerifier checkPaymentDetails(long workerId, int workedDays)`

Método do tipo GET que checa os dados do trabalhador e apresenta quanto deverá receber baseado no quanto recebe por dia 
trabalhado e quantos dias trabalhou.

- `public EmployeeWorker generateWorkerFeignClient(long workerId)`

Método que está utilizando o Feign client para realizar uma requisição HTTP GET para a API do serviço hr-worker, com o 
objetivo de buscar informações sobre um trabalhador.

### Config Classes

#### RestClientConfig

#### Métodos da Classe RestClientConfig

- `@Bean
   public RestTemplate restTemplate()`

Gera um Bean para a classe restTemplate, que para este caso, é utilizada para realizar chamadas  em serviçoso REST, 
facilitando a forma de operar com os métodos HTTP (GET, POST, PUT, DELETE, etc.)

### Config Interfaces

#### WorkerFeignClient
``@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerFeignClient``
#### Métodos da Interface WorkerFeignClient

``@GetMapping(path = "/{workerId}")
ResponseEntity<EmployeeWorker> findWorkerById(@PathVariable Long workerId);``

Efetua a busca do trabalhador através do seu id. Esse método corresponde ao método do serviço
de busca por id do micro serviço hr-worker

### Resource Classes

#### PaymentController

`path = "/payments"`

#### Métodos:

- ` @GetMapping(path = "/{workerId}/worker-days/{workerDays}")`

Lança as informações do trabalhador junto com o resultado da checagem de quanto ele irá receber.


### Main Class

#### HrPayrollApplication

Dentro da classe, onde é iniciada a aplicação do Spring Boot. Para que seja habilitada a capacidade de o Feign,
foi habilitada a anotação `@EnableFeignClients`. 
 Nela também se encontra a anotação `@EnableEurekaClient`, que habilita as funções do cliente da aplicação


## hr-eureka-server

### Main Class

#### HrEurekaServerApplication

Dentro da classe principal, contém a anotação `@EnableEurekaClient` que serve para habilitar funcionalidade de registro
e descoberta de serviços em um servidor Eureka em uma aplicação Spring.

### Informações sobre as anotações utilizadas

##### Informação sobre a anotação @FeignClient
A anotação `@FeignClient` utilizada no Spring Cloud para criar uma interface de cliente HTTP de forma declarativa, sem a
necessidade de escrever manualmente as chamadas de API HTTP. É possível configurar várias opções, como nome do serviço 
remoto, URL do serviço e autenticação, e o Spring Cloud cria automaticamente um cliente HTTP capaz de fazer chamadas 
para o serviço remoto a partir dos métodos definidos na interface anotada. Essa anotação é útil em arquiteturas de 
microsserviços para facilitar a comunicação entre vários serviços.

##### Informação sobre a anotação @EnableFeignClients

`@EnableFeignClients` é uma anotação em Spring Boot que ativa a capacidade de usar o Feign, um cliente HTTP declarativo,
em uma aplicação. Com essa anotação, é possível criar proxies de clientes HTTP simplesmente definindo interfaces e 
anotações no código, ao invés de precisar escrever código boilerplate para cada chamada HTTP. O Feign também suporta 
balanceamento de carga e fallbacks para outras instâncias de serviço em caso de falhas. A anotação `@EnableFeignClients`
permite que a aplicação escaneie pacotes específicos para encontrar as interfaces Feign e configurá-las automaticamente.

##### Informação sobre a anotação @RibbonClient

A anotação `@RibbonClient` é uma ferramenta utilizada em aplicações de microsserviços que precisam lidar com o 
balanceamento de carga. Ela funciona automaticamente, distribuindo a carga de trabalho entre várias instâncias do mesmo 
serviço. Isso é feito com base em um algoritmo de balanceamento de carga configurado, que pode ser escolhido pelo 
desenvolvedor.

#### Informação sobre a anotação @EnableEurekaClient

A anotação @EnableEurekaClient é usada para habilitar a funcionalidade de registro e descoberta de serviços em um 
servidor Eureka em uma aplicação Spring.
