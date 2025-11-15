🛒 Product Recommendation Service

Sistema de Recomendação de Produtos utilizando Java 21 e Spring Boot 3, com foco em microsserviços, testes automatizados e boas práticas de arquitetura.

📊 Diagrama do Sistema

<img width="1536" height="1024" alt="Diagrama" src="https://github.com/user-attachments/assets/3eaa708b-371d-4b4f-8b2f-bc2d763c1c8e" />

 **Gateway** → Entrada única das APIs
- **user-service** → Gestão de usuários e autenticação
- **recommendation-service** → Sistema de recomendação de produtos
- **order-service** → Gestão de pedidos
- **payment-service** → Processamento de pagamentos (em construção)
- **notification-service** → Notificações para usuários (em construção)
---

## 💻 Tecnologias e Frameworks

**Backend & Frameworks:**

- Java 21  
- Spring Boot 3  
- Spring Web → REST APIs  
- Spring Data JPA → CRUD via repositórios  
- Spring Security + JWT → Autenticação e roles no user-service  

**Banco de Dados:**

- H2 Database → usado em memória nos testes  
- PostgreSQL → produção, cada serviço com seu banco próprio  

**Testes:**

- JUnit 5 → testes unitários  
- Mockito → mock de dependências  
- Testcontainers → PostgreSQL real em container para testes de integração  

**Boas Práticas:**

- SOLID & Clean Code  
- Arquitetura em camadas (Controller, Service, Repository, DTO, Entity)  
- Separação clara de responsabilidades  
- DTOs para comunicação entre camadas  

**DevOps & Observabilidade:**

- Spring DevTools → reload automático no desenvolvimento  
- Logs configurados (Hibernate SQL + parâmetros)  
- Docker → empacotamento de microsserviços  
- CI/CD GitHub Actions → pipeline em construção  

---

## 🏗 Arquitetura de Microsserviços

- API Gateway com Spring Cloud Gateway  
- Serviços independentes com banco próprio  
- Comunicação via REST APIs  
- Configuração preparada para expansão com novos serviços  

---

## 📂 Estrutura do Projeto

- **review-service** → Avaliações de produtos  
- **user-service** → Gestão de usuários e autenticação  
- **notification-service** → Notificações para usuários  
- **order-service** → Gestão de pedidos  
- **payment-service** → Processamento de pagamentos  
- **gateway-service** → Entrada para APIs  

---

## ✨ Funcionalidades

- Cadastro e autenticação de usuários  
- Avaliações e comentários de produtos  
- Recomendações de produtos baseadas em histórico e avaliações  
- Notificações automáticas de produtos e promoções  
- Processamento de pedidos e pagamentos  

---

## 🧪 Testes

 Testes manuais / exploratórios (Postman)  
 Testes unitários (JUnit, Mockito)  
 Testes de integração (MockMvc, Testcontainers + H2/PostgreSQL)  
 Testes de API e segurança (MockMvc + JWT)  
 Testes de performance (conceitual: JMeter, k6, Gatling)  

---

## 🚀 Como Rodar o Projeto

1. Clonar o repositório:

```bash
git clone git@github.com:lucasleao-dev/product-recommendation-service.git
cd product-recommendation-service
Rodar serviços individualmente via IDE (Spring Boot) ou terminal:


cd order-service
./mvnw spring-boot:run
Configurar variáveis de ambiente (ex: DATABASE_URL, JWT_SECRET) se necessário.

Testar endpoints via Postman ou curl.

📦 Próximos Serviços
payment-service → criação de endpoints para pagamento

notification-service → envio de notificações via email e push

🔗 Links úteis
Documentação do Spring Boot

Spring Security + JWT

Testcontainers

⚡ Autor
Lucas Souza Leão – Desenvolvedor Fullstack e Analista de Sistemas
