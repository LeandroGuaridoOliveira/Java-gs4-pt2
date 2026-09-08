# 🛒 Mercado Express MVC - Checkpoint 4 (Parte 2)

[![Java 17](https://img.shields.io/badge/Java-17%20LTS-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot 3.3.2](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Template%20Engine-Thymeleaf-green.svg)](https://www.thymeleaf.org/)
[![Spring Security 6](https://img.shields.io/badge/Security-Spring%20Security%206-blue.svg)](https://spring.io/projects/spring-security)
[![Bootstrap 5.3](https://img.shields.io/badge/Front--End-Bootstrap%205.3-purple.svg)](https://getbootstrap.com/)
[![Deploy Render](https://img.shields.io/badge/Deploy-Render-black.svg)](https://render.com/)

Aplicação Web completa desenvolvida em **Java com Spring Boot MVC**, **Thymeleaf**, **Spring Security** e persistência **Spring Data JPA** para gerenciamento de estoque de produtos em um **Mercado Express** (produtos de limpeza, hortifruti, vestuário/meias, brinquedos e mercearia).

Este projeto atende rigorosamente a todos os requisitos do **Checkpoint 4 (Parte 2)** da disciplina de **Desenvolvimento de Sistemas em Java** do curso de **Tecnologia em Análise e Desenvolvimento de Sistemas (TDS) - FIAP**.

---

## 👥 Identificação da Equipe e Projeto

- **Curso**: Tecnologia em Análise e Desenvolvimento de Sistemas (TDS) - FIAP
- **Turma**: 2TDSPV
- **Professor**: Dr. Marcel Stefan Wagner
- **IDE Utilizada**: **IntelliJ IDEA**
- **Integrantes**:
  1. **Leandro Guarido de Oliveira** - RM: 561760
  2. **Kaiky Costa** - RM: 564578
  3. **Gabriel Costa Solano** - RM: 562325

---

## 🌐 Links do Projeto

- **Repositório GitHub (Parte 2)**: [https://github.com/LeandroGuaridoOliveira/Java-gs4-parte2.git](https://github.com/LeandroGuaridoOliveira/Java-gs4-parte2.git)
- **Repositório da Parte 1 (API REST + HATEOAS)**: [https://github.com/LeandroGuaridoOliveira/Java-gs4.git](https://github.com/LeandroGuaridoOliveira/Java-gs4.git)
- **Deploy em Produção**: [https://mercado-express-mvc.onrender.com](https://mercado-express-mvc.onrender.com)
- **Plataforma de Hospedagem**: Render (Web Service conteinerizado via Docker)

---

## 📌 Tecnologias Utilizadas

- **Linguagem & Plataforma**: Java 17 LTS
- **Framework Principal**: Spring Boot 3.3.2 (Maven)
- **Spring MVC**: Padrão Model-View-Controller com roteamento e injeção de dependências.
- **Thymeleaf**: Template engine server-side com layout fragments e integração de segurança (`thymeleaf-extras-springsecurity6`).
- **Spring Security 6**: Controle granular de acesso baseado em roles (`ADMIN` e `USER`), CSRF protection e tela de login customizada.
- **Spring Data JPA & Hibernate**: ORM mapeando a tabela `TDS_MVC_TB_MERCADO`.
- **Validação de Dados**: Jakarta Bean Validation (`@NotBlank`, `@Positive`, `@Size`).
- **Lombok**: Redução de código boilerplate (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`).
- **Banco de Dados**: Suporte duplo e transparente:
  - **H2 Database**: Banco relacional em memória com seed automático (`DatabaseSeeder`) para desenvolvimento e testes ágeis sem necessidade de VPN.
  - **Oracle Database**: Driver oficial `ojdbc11` pré-configurado para conexão ao banco de dados Oracle da FIAP.
- **Front-end Moderno**: Bootstrap 5.3 + Bootstrap Icons + CSS customizado com design responsivo, cards, badges setoriais e modais dinâmicos.

---

## 🔒 Segurança (Spring Security): Rotas Públicas vs. Privadas

A aplicação implementa uma política de segurança robusta distinguindo acesso livre para clientes e catálogo, e acesso restrito para operações administrativas:

| Tipo de Rota | Endpoint | Método | Permissão / Role | Descrição |
| :--- | :--- | :--- | :--- | :--- |
| **Pública** | `/` | `GET` | Livre (`permitAll`) | Página inicial / Dashboard do mercado com métricas. |
| **Pública** | `/produtos` | `GET` | Livre (`permitAll`) | Catálogo de produtos com filtro de busca por nome. |
| **Pública** | `/produtos/detalhes/{id}` | `GET` | Livre (`permitAll`) | Ficha técnica detalhada com dados do produto. |
| **Pública** | `/login` | `GET` | Livre (`permitAll`) | Tela personalizada de autenticação. |
| **Pública** | `/css/**`, `/js/**` | `GET` | Livre (`permitAll`) | Recursos estáticos e estilos da interface. |
| **Privada** | `/produtos/novo` | `GET` | `USER` ou `ADMIN` | Formulário para cadastro de novos produtos. |
| **Privada** | `/produtos/salvar` | `POST` | `USER` ou `ADMIN` | Persistência com validação de dados no banco. |
| **Privada** | `/produtos/editar/{id}` | `GET` | `USER` ou `ADMIN` | Formulário preenchido para edição de item. |
| **Privada** | `/produtos/atualizar/{id}` | `POST` | `USER` ou `ADMIN` | Atualização do registro no banco de dados. |
| **Privada** | `/produtos/excluir/{id}` | `GET` | Apenas `ADMIN` | Exclusão do registro com modal de confirmação. |

### 🔑 Credenciais para Teste e Avaliação

| Usuário | Senha | Perfil (Role) | Nível de Acesso |
| :--- | :--- | :--- | :--- |
| `admin` | `admin123` | `ROLE_ADMIN`, `ROLE_USER` | **Acesso Total**: Visualizar, Cadastrar, Editar e Excluir produtos. |
| `usuario` | `user123` | `ROLE_USER` | **Operador**: Visualizar, Cadastrar e Editar produtos (sem permissão de exclusão). |

---

## 🗄️ Modelo de Dados (`TDS_MVC_TB_MERCADO`)

| Coluna | Tipo BD | Atributo Java | Restrições / Validações |
| :--- | :--- | :--- | :--- |
| `ID` | `NUMBER(19)` | `Long id` | Chave Primária, Sequence `SQ_TDS_MVC_MERCADO` |
| `NOME` | `VARCHAR2(100)` | `String nome` | Obrigatório, min 2 e max 100 caracteres |
| `TIPO` | `VARCHAR2(50)` | `String tipo` | Obrigatório, max 50 caracteres (ex: Produto de Limpeza) |
| `SETOR` | `VARCHAR2(50)` | `String setor` | Obrigatório, max 50 caracteres (ex: Limpeza, Hortifruti) |
| `TAMANHO` | `VARCHAR2(30)` | `String tamanho` | Opcional, max 30 caracteres (ex: 1.6kg, 500ml, 39-43) |
| `PRECO` | `NUMBER(10,2)` | `Double preco` | Obrigatório, valor estritamente positivo |

---

## 🖥️ Demonstração do CRUD na Interface Web

Todas as operações básicas do CRUD foram implementadas através de **links e botões** intuitivos:

### 1. READ (Leitura / Consulta)
- **Catálogo Geral (`/produtos`)**: Tabela responsiva com badges estilizados para cada setor (Limpeza, Hortifruti, Vestuário, Brinquedos), formatação monetária padrão brasileiro (`R$ 0,00`), contador de itens e campo de pesquisa por nome em tempo real.
- **Detalhes do Produto (`/produtos/detalhes/{id}`)**: Página dedicada com a ficha completa do produto, tamanho, setor e identificador do banco de dados.

### 2. CREATE (Criação / Cadastro)
- **Formulário de Cadastro (`/produtos/novo`)**: Acessível pelo botão **"Novo Produto"** na barra de ferramentas. Exige autenticação prévia (redireciona para `/login` caso não esteja autenticado).
- **Validação com Feedback em Tempo Real**: Mensagens amigáveis em vermelho caso o usuário submeta campos em branco ou preço negativo.
- **Alertas de Sucesso**: Ao salvar, o usuário é redirecionado com uma notificação verde de sucesso.

### 3. UPDATE (Atualização / Edição)
- **Formulário de Edição (`/produtos/editar/{id}`)**: Acionado pelo botão amarelo com ícone de lápis. Os dados atuais são carregados automaticamente nos campos para alteração segura.

### 4. DELETE (Exclusão Segura)
- **Exclusão com Modal de Confirmação (`/produtos/excluir/{id}`)**: Acionado pelo botão vermelho com ícone de lixeira. Abre uma janela modal do Bootstrap exibindo o nome exato do produto para evitar exclusões acidentais.
- **Restrição de Acesso**: Somente usuários com perfil `ADMIN` têm autorização para confirmar a exclusão.

---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos
- JDK 17+ instalado
- Git

### 1. Clonar o Repositório
```bash
git clone https://github.com/LeandroGuaridoOliveira/Java-gs4-parte2.git
cd Java-gs4-parte2
```

### 2. Executar via Maven Wrapper
No Windows:
```cmd
mvnw.cmd spring-boot:run
```
No Linux / macOS:
```bash
./mvnw spring-boot:run
```

A aplicação iniciará na porta **`8082`**:
- Interface Principal: [http://localhost:8082](http://localhost:8082)
- Catálogo de Produtos: [http://localhost:8082/produtos](http://localhost:8082/produtos)
- Console H2: [http://localhost:8082/h2-console](http://localhost:8082/h2-console) (JDBC URL: `jdbc:h2:mem:mercadomvcdb`, Usuário: `sa`, Senha em branco)

### 3. Executar os Testes Automatizados
```bash
mvnw.cmd test
```
*Total de 11 testes unitários e de integração validando rotas públicas, restrições do Spring Security, formulários de validação e operações de CRUD.*

---

## 🖼️ Configuração do Spring Initializr

Dependências configuradas para a Parte 2:
1. **Spring Web** (Spring MVC)
2. **Thymeleaf** (Template Engine)
3. **Spring Security** (Autenticação e Autorização)
4. **Spring Data JPA** (Persistência relacional)
5. **Validation** (Bean Validation)
6. **Lombok**
7. **Oracle Driver**
8. **H2 Database**

![Spring Initializr Config](./spring_initializr.png)

---

## 📹 Roteiro Sugerido para o Vídeo de Apresentação (~5 Minutos)

Para a entrega do vídeo exigido pelo professor:

1. **Abertura (0:00 - 0:45)**:
   - Apresentação dos integrantes (Leandro, Kaiky, Gabriel) e menção à turma 2TDSPV e professor Dr. Marcel Stefan Wagner.
   - Breve introdução: este projeto é a Parte 2 do CP4, focado em Spring MVC, Thymeleaf e Spring Security.
2. **Demonstração das Rotas Públicas (0:45 - 1:45)**:
   - Acessar a Home (`/`) e mostrar o dashboard com métricas de estoque e preços médios.
   - Navegar para `/produtos`: demonstrar o catálogo público e o filtro de busca por nome.
   - Acessar os detalhes de um produto (`/produtos/detalhes/{id}`).
3. **Controle de Acesso com Spring Security (1:45 - 2:30)**:
   - Tentar clicar em "Novo Produto" enquanto deslogado: demonstrar o redirecionamento automático para a tela `/login`.
   - Efetuar o login com o usuário administrador (`admin` / `admin123`).
   - Mostrar o navbar atualizado com a identificação do usuário e o perfil `ADMIN`.
4. **Demonstração Completa do CRUD (2:30 - 4:15)**:
   - **Create**: Cadastrar um novo produto (ex: "Uva Thompson", "Hortifruti", "Frutas e Verduras", "500g", R$ 14.90). Mostrar a validação de erros caso deixe campos em branco e a mensagem de sucesso ao salvar.
   - **Read**: Mostrar o produto recém-cadastrado na tabela com seu badge específico.
   - **Update**: Clicar no botão de edição, alterar o preço e salvar.
   - **Delete**: Clicar no botão de exclusão, mostrar o modal de confirmação dinâmico e confirmar a exclusão.
5. **Encerramento e Deploy (4:15 - 5:00)**:
   - Mostrar a aplicação em funcionamento no link de produção do Render.
   - Mostrar o botão de logout e considerações finais.

---

## 📄 Estrutura de Arquivos da Entrega (.zip)

O arquivo `.zip` para envio no Teams do professor contém:
```
mercado-express-mvc.zip
├── integrantes.txt          (Nomes, RMs, turma, links do Git e Deploy)
├── spring_initializr.png     (Print das dependências)
├── pom.xml                  (Configuração Maven)
├── Dockerfile               (Receita de build para deploy)
├── README.md                (Documentação completa)
└── src/                     (Código fonte Java e templates Thymeleaf)
```