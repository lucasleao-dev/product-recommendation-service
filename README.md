🛒 Product Recommendation Service

Sistema de Recomendação de Produtos utilizando Java 21 e Spring Boot 3, com foco em microsserviços, testes automatizados e boas práticas de arquitetura.

📊 Diagrama do Sistema
<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/1c678f81-fd77-48a1-92f3-dc8e047e73e6" />


💻 Tecnologias e Frameworks

Backend & Frameworks

Java 21

Spring Boot 3

Spring Web → REST APIs

Spring Data JPA → CRUD via repositórios

Spring Security + JWT → Autenticação e roles no user-service

Banco de Dados

H2 Database → usado em memória nos testes

PostgreSQL → produção, cada serviço com seu banco próprio

Testes

JUnit 5 → testes unitários

Mockito → mock de dependências

Testcontainers → PostgreSQL real em container para testes de integração

Boas Práticas

SOLID & Clean Code

Arquitetura em camadas (Controller, Service, Repository, DTO, Entity)

Separação clara de responsabilidades

DTOs para comunicação entre camadas

DevOps & Observabilidade

Spring DevTools → reload automático no desenvolvimento

Logs configurados (Hibernate SQL + parâmetros)

Docker → empacotamento de microsserviços

CI/CD GitHub Actions → pipeline em construção

🏗 Arquitetura de Microsserviços

API Gateway com Spring Cloud Gateway

Serviços independentes com banco próprio

Comunicação via REST APIs

Configuração preparada para expansão com novos serviços

📂 Estrutura do Projeto

review-service → Avaliações de produtos

user-service → Gestão de usuários e autenticação

notification-service → Notificações para usuários

order-service → Gestão de pedidos

payment-service → Processamento de pagamentos

gateway-service → Entrada para APIs

✨ Funcionalidades

Cadastro e autenticação de usuários

Avaliações e comentários de produtos

Recomendações de produtos baseadas em histórico e avaliações

Notificações automáticas de produtos e promoções

Processamento de pedidos e pagamentos

🧪 Testes:

Testes manuais / exploratórios (Postman)

Testes unitários (JUnit, Mockito)

Testes de integração (MockMvc, Testcontainers + H2/PostgreSQL)

Testes de API e segurança (MockMvc + JWT)

Testes de performance (conceitual: JMeter, k6, Gatling)

🚀 Como Rodar o Projeto
# Clone o repositório
git clone git@github.com:lucasleao-dev/product-recommendation-service.git

# Entre no diretório do serviço desejado
cd review-service

# Rode a aplicação
mvn spring-boot:run

# Execute os testes
mvn test

🔜 Próximos Passos

Implementar recomendações personalizadas com Machine Learning

Empacotar microsserviços com Docker

Criar documentação de API com Swagger/OpenAPI

Configurar CI/CD completo com GitHub Actions
