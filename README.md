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

- `public PaymentVerifier checkPaymentAmount(long workerId, int workedDays)`

Verifica o pagamento que o trabalhador tem a receber

- `public Map<String, String> mapWorkerId(long workerId)`

Mapeia o id do trabalhador

- `public EmployerWorker generateWorkerRestTemplate(Map<String, String> workerIds)`

Efetua a geração de um Rest Template do domínio EmployerWorker

### Config Classes

#### RestClientConfig

#### Métodos da Classe RestClientConfig

- `@Bean
   public RestTemplate restTemplate()`

Gera um Bean para a classe restTemplate, que para este caso, é utilizada para realizar chamadas  em serviçoso REST, facilitando a forma
de operar com os métodos HTTP (GET, POST, PUT, DELETE, etc.)

### Resource Classes

#### PaymentController

`path = "/payments"`

#### Métodos:

- ` @GetMapping(path = "/{workerId}/worker-days/{workerDays}")`

Lança as informações do trabalhador junto com o resultado da checagem de quanto ele irá receber.






