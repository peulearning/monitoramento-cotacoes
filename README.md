# 📈 Monitoramento de Cotações Financeiras (Backend Java)

**Status:** Em Desenvolvimento 🚧 | **Versão:** 1.0.0

Uma API RESTful robusta, desenvolvida em Java com Spring Boot 3, projetada para monitorar, armazenar e analisar dados históricos de cotações de moedas em tempo real. Este projeto serve como base para um dashboard financeiro e demonstra um backend bem estruturado, seguindo princípios de **Clean Architecture** e **SOLID**.

## ✨ Funcionalidades Principais

* **API RESTful Segura:** Endpoints para consulta de cotações históricas e análises.
* **Integração Automatizada:** Agendamento (`@Scheduled`) para buscar e persistir cotações de APIs externas (Ex: Câmbio USD/BRL).
* **Persistência Histórica:** Armazenamento dos dados em **PostgreSQL** com **Spring Data JPA**.
* **Autenticação JWT:** Implementação de segurança com **Spring Security** e **JSON Web Tokens (JWT)**.
* **Recurso de Contribuição:** Endpoint exclusivo para geração de **QR Code Pix Estático**, demonstrando integração com serviços de pagamento (simulado).
* **Monitoramento:** Uso de **Spring Actuator** para verificação da saúde da aplicação.

## 📐 Arquitetura do Projeto (Clean Architecture & SOLID)

A estrutura do projeto adota a Clean Architecture para garantir a separação de responsabilidades, testabilidade e manutenção.

| Camada | Descrição | Princípios SOLID Aplicados |
| :--- | :--- | :--- |
| **`domain`** | Contém Entidades (regras de negócio puras), Casos de Uso (a lógica principal) e Interfaces. **Independente** de frameworks. | **SRP** (Single Responsibility Principle) e **DIP** (Dependency Inversion Principle). |
| **`application`** | Adapta os Casos de Uso para o contexto da aplicação Spring (serviços agendados, transacionais). | **OCP** (Open/Closed Principle) - Aberto para extensão, fechado para modificação. |
| **`infrastructure`** | Contém implementações concretas de interfaces do `domain`, como Repositórios (JPA), Controladores (REST) e Adapters para APIs externas. | **ISP** (Interface Segregation Principle) - interfaces específicas. |

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (versão 17 estável)
* **Framework:** Spring Boot 3
* **Segurança:** Spring Security + JWT
* **Banco de Dados:** PostgreSQL (Produção) e H2 (Testes)
* **Persistência:** Spring Data JPA
* **Integração:** Spring WebClient
* **Documentação:** Swagger/OpenAPI

## 🚀 Como Executar o Projeto

### Pré-requisitos
1.  JDK 17 ou superior.
2.  Docker (Opcional, para rodar o PostgreSQL).
3.  Maven.

### Passos
1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/seunome/monitoramento-cotacoes.git](https://github.com/seunome/monitoramento-cotacoes.git)
    cd monitoramento-cotacoes
    ```
2.  **Configuração do Banco de Dados:**
    * Crie um banco de dados PostgreSQL.
    * Configure as credenciais em `src/main/resources/application.properties` (ou `.yml`).
3.  **Execução:**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  **Acesso à Documentação:**
    * Swagger UI estará disponível em: `http://localhost:8080/swagger-ui.html`

## 🔐 Endpoints da API

| Método | Endpoint | Descrição | Proteção |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Gera o token JWT. | Livre |
| `GET` | `/api/cotacoes/latest` | Retorna a cotação mais recente. | JWT |
| `GET` | `/api/cotacoes/historico` | Histórico de cotações por período. | JWT |
| `GET` | `/api/pix/contribuicao` | Gera o payload/imagem do QR Code Pix. | Livre (ou JWT, se preferir) |

---
## 💡 Pague-me um Café (Contribute)

Gostou do projeto? Considere me pagar um café via Pix! Essa funcionalidade é totalmente construída por esta API (demonstrando a integração com a geração de QR Code).

**Chave Pix (Exemplo):** `peuhenry2018@gmail.com`

---
