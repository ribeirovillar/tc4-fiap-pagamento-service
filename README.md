# FIAP Pagamento Service

API REST para processamento de pagamentos, baseada em Spring Boot e Clean Architecture.

---

## 🚀 Tecnologias

- **Java 24**
- **Spring Boot 3.5.4**
- **PostgreSQL**
- **Flyway** (migrações SQL)
- **MapStruct** (DTO ↔ Domínio ↔ Persistência)
- **Lombok**
- **Jacoco** (cobertura de testes)
- **Arquitetura Limpa (Clean Architecture)**

---

## 📋 Endpoints REST

| Método | Endpoint             | Descrição                    |
|--------|----------------------|------------------------------|
| GET    | `/payments`          | Listar todos os pagamentos   |
| POST   | `/payments`          | Processar novo pagamento     |

### Exemplo de processamento de pagamento

```json
POST /payments
{
  "orderId": "123e4567-e89b-12d3-a456-426614174000",
  "customerName": "Demostenis Villar",
  "customerCpf": "06847362754",
  "cardNumber": "4111111111111111",
  "paymentAmount": 125.50
}
```

### Resposta

```json
{
  "id": "456e7890-e89b-12d3-a456-426614174001",
  "orderId": "123e4567-e89b-12d3-a456-426614174000",
  "customerName": "Demostenis Villar",
  "customerCpf": "06847362754",
  "cardNumber": "4111111111111111",
  "paymentAmount": 125.50
}
```

---

## 🏃 Como Rodar

### 1. Docker Compose (App + Banco)

```bash
docker-compose up --build
```
- API: http://localhost:8085/payments
- PostgreSQL: localhost:5436

### 2. Local com Maven

1. Suba o banco:
   ```bash
   docker-compose up -d fiap-pagamento-postgres
   ```
2. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
3. API: http://localhost:8085/payments

---

## 🧪 Testes & Cobertura

- Testes automatizados:
  ```bash
  ./mvnw clean verify
  ```
- Relatório Jacoco:
  ```
  target/site/jacoco/index.html
  ```

---

## 🗄️ Banco de Dados

- **Desenvolvimento:** PostgreSQL (porta 5436)
- **Database:** `paymentdb`
- **Migrações automáticas:** via Flyway (`src/main/resources/db/migration`)

---

## 🧱 Estrutura de Pastas

- `domain`        - modelo rico do domínio (Payment)
- `usecase`       - casos de uso da aplicação
- `gateway`       - interface e implementação (database, etc)
- `controller`    - REST API (entrada/saída)
- `mapper`        - conversores (DTO ↔ domínio ↔ persistência)
- `configuration` - configurações do Spring
- `exception`     - tratamento de exceções

---

## 💳 Funcionalidades

- Processamento de pagamentos com cartão de crédito
- Armazenamento seguro de transações
- Consulta de histórico de pagamentos
- Validação de dados de entrada
- Integração com arquitetura de microserviços

---

## 🔗 Integração

Este serviço faz parte do ecossistema FIAP e pode ser integrado com:
- **Cliente Service** (porta 8080)
- **Produto Service** (porta 8081)
- **Estoque Service** (porta 8082)
- **Pedido Service** (porta 8083)
