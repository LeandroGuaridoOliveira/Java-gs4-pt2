# ðŸ›’ Mercado Express MVC - Checkpoint 4 (Parte 2)

[![Java 17](https://img.shields.io/badge/Java-17%20LTS-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot 3.3.2](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Template%20Engine-Thymeleaf-green.svg)](https://www.thymeleaf.org/)
[![Spring Security 6](https://img.shields.io/badge/Security-Spring%20Security%206-blue.svg)](https://spring.io/projects/spring-security)
[![Bootstrap 5.3](https://img.shields.io/badge/Front--End-Bootstrap%205.3-purple.svg)](https://getbootstrap.com/)
[![Deploy Render](https://img.shields.io/badge/Deploy-Render-black.svg)](https://render.com/)

AplicaÃ§Ã£o Web completa desenvolvida em **Java com Spring Boot MVC**, **Thymeleaf**, **Spring Security** e persistÃªncia **Spring Data JPA** para gerenciamento de estoque de produtos em um **Mercado Express** (produtos de limpeza, hortifruti, vestuÃ¡rio/meias, brinquedos e mercearia).

Este projeto atende rigorosamente a todos os requisitos do **Checkpoint 4 (Parte 2)** da disciplina de **Desenvolvimento de Sistemas em Java** do curso de **Tecnologia em AnÃ¡lise e Desenvolvimento de Sistemas (TDS) - FIAP**.

---

## ðŸ‘¥ IdentificaÃ§Ã£o da Equipe e Projeto

- **Curso**: Tecnologia em AnÃ¡lise e Desenvolvimento de Sistemas (TDS) - FIAP
- **Turma**: 2TDSPV
- **Professor**: Dr. Marcel Stefan Wagner
- **IDE Utilizada**: **IntelliJ IDEA**
- **Integrantes**:
  1. **Leandro Guarido de Oliveira** - RM: 561760
  2. **Kaiky Costa** - RM: 564578
  3. **Gabriel Costa Solano** - RM: 562325

---

## ðŸŒ Links do Projeto

- **RepositÃ³rio GitHub (Parte 2)**: [https://github.com/LeandroGuaridoOliveira/Java-gs4-pt2.git](https://github.com/LeandroGuaridoOliveira/Java-gs4-pt2.git)
- **RepositÃ³rio da Parte 1 (API REST + HATEOAS)**: [https://github.com/LeandroGuaridoOliveira/Java-gs4.git](https://github.com/LeandroGuaridoOliveira/Java-gs4.git)
- **Deploy em ProduÃ§Ã£o**: [https://mercado-express-mvc.onrender.com](https://mercado-express-mvc.onrender.com)
- **Plataforma de Hospedagem**: Render (Web Service conteinerizado via Docker)

---

## ðŸ“Œ Tecnologias Utilizadas

- **Linguagem & Plataforma**: Java 17 LTS
- **Framework Principal**: Spring Boot 3.3.2 (Maven)
- **Spring MVC**: PadrÃ£o Model-View-Controller com roteamento e injeÃ§Ã£o de dependÃªncias.
- **Thymeleaf**: Template engine server-side com layout fragments e integraÃ§Ã£o de seguranÃ§a (`thymeleaf-extras-springsecurity6`).
- **Spring Security 6**: Controle granular de acesso baseado em roles (`ADMIN` e `USER`), CSRF protection e tela de login customizada.
- **Spring Data JPA & Hibernate**: ORM mapeando a tabela `TDS_MVC_TB_MERCADO`.
- **ValidaÃ§Ã£o de Dados**: Jakarta Bean Validation (`@NotBlank`, `@Positive`, `@Size`).
- **Lombok**: ReduÃ§Ã£o de cÃ³digo boilerplate (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`).
- **Banco de Dados**: Suporte duplo e transparente:
  - **H2 Database**: Banco relacional em memÃ³ria com seed automÃ¡tico (`DatabaseSeeder`) para desenvolvimento e testes Ã¡geis sem necessidade de VPN.
  - **Oracle Database**: Driver oficial `ojdbc11` prÃ©-configurado para conexÃ£o ao banco de dados Oracle da FIAP.
- **Front-end Moderno**: Bootstrap 5.3 + Bootstrap Icons + CSS customizado com design responsivo, cards, badges setoriais e modais dinÃ¢micos.

---

## ðŸ”’ SeguranÃ§a (Spring Security): Rotas PÃºblicas vs. Privadas

A aplicaÃ§Ã£o implementa uma polÃ­tica de seguranÃ§a robusta distinguindo acesso livre para clientes e catÃ¡logo, e acesso restrito para operaÃ§Ãµes administrativas:

| Tipo de Rota | Endpoint | MÃ©todo | PermissÃ£o / Role | DescriÃ§Ã£o |
| :--- | :--- | :--- | :--- | :--- |
| **PÃºblica** | `/` | `GET` | Livre (`permitAll`) | PÃ¡gina inicial / Dashboard do mercado com mÃ©tricas. |
| **PÃºblica** | `/produtos` | `GET` | Livre (`permitAll`) | CatÃ¡logo de produtos com filtro de busca por nome. |
| **PÃºblica** | `/produtos/detalhes/{id}` | `GET` | Livre (`permitAll`) | Ficha tÃ©cnica detalhada com dados do produto. |
| **PÃºblica** | `/login` | `GET` | Livre (`permitAll`) | Tela personalizada de autenticaÃ§Ã£o. |
| **PÃºblica** | `/css/**`, `/js/**` | `GET` | Livre (`permitAll`) | Recursos estÃ¡ticos e estilos da interface. |
| **Privada** | `/produtos/novo` | `GET` | `USER` ou `ADMIN` | FormulÃ¡rio para cadastro de novos produtos. |
| **Privada** | `/produtos/salvar` | `POST` | `USER` ou `ADMIN` | PersistÃªncia com validaÃ§Ã£o de dados no banco. |
| **Privada** | `/produtos/editar/{id}` | `GET` | `USER` ou `ADMIN` | FormulÃ¡rio preenchido para ediÃ§Ã£o de item. |
| **Privada** | `/produtos/atualizar/{id}` | `POST` | `USER` ou `ADMIN` | AtualizaÃ§Ã£o do registro no banco de dados. |
| **Privada** | `/produtos/excluir/{id}` | `GET` | Apenas `ADMIN` | ExclusÃ£o do registro com modal de confirmaÃ§Ã£o. |

### ðŸ”‘ Credenciais para Teste e AvaliaÃ§Ã£o

| UsuÃ¡rio | Senha | Perfil (Role) | NÃ­vel de Acesso |
| :--- | :--- | :--- | :--- |
| `admin` | `admin123` | `ROLE_ADMIN`, `ROLE_USER` | **Acesso Total**: Visualizar, Cadastrar, Editar e Excluir produtos. |
| `usuario` | `user123` | `ROLE_USER` | **Operador**: Visualizar, Cadastrar e Editar produtos (sem permissÃ£o de exclusÃ£o). |

---

## ðŸ—„ï¸ Modelo de Dados (`TDS_MVC_TB_MERCADO`)

| Coluna | Tipo BD | Atributo Java | RestriÃ§Ãµes / ValidaÃ§Ãµes |
| :--- | :--- | :--- | :--- |
| `ID` | `NUMBER(19)` | `Long id` | Chave PrimÃ¡ria, Sequence `SQ_TDS_MVC_MERCADO` |
| `NOME` | `VARCHAR2(100)` | `String nome` | ObrigatÃ³rio, min 2 e max 100 caracteres |
| `TIPO` | `VARCHAR2(50)` | `String tipo` | ObrigatÃ³rio, max 50 caracteres (ex: Produto de Limpeza) |
| `SETOR` | `VARCHAR2(50)` | `String setor` | ObrigatÃ³rio, max 50 caracteres (ex: Limpeza, Hortifruti) |
| `TAMANHO` | `VARCHAR2(30)` | `String tamanho` | Opcional, max 30 caracteres (ex: 1.6kg, 500ml, 39-43) |
| `PRECO` | `NUMBER(10,2)` | `Double preco` | ObrigatÃ³rio, valor estritamente positivo |

---

## ðŸ–¥ï¸ DemonstraÃ§Ã£o do CRUD na Interface Web

Todas as operaÃ§Ãµes bÃ¡sicas do CRUD foram implementadas atravÃ©s de **links e botÃµes** intuitivos:

### 1. READ (Leitura / Consulta)
- **CatÃ¡logo Geral (`/produtos`)**: Tabela responsiva com badges estilizados para cada setor (Limpeza, Hortifruti, VestuÃ¡rio, Brinquedos), formataÃ§Ã£o monetÃ¡ria padrÃ£o brasileiro (`R$ 0,00`), contador de itens e campo de pesquisa por nome em tempo real.
- **Detalhes do Produto (`/produtos/detalhes/{id}`)**: PÃ¡gina dedicada com a ficha completa do produto, tamanho, setor e identificador do banco de dados.

### 2. CREATE (CriaÃ§Ã£o / Cadastro)
- **FormulÃ¡rio de Cadastro (`/produtos/novo`)**: AcessÃ­vel pelo botÃ£o **"Novo Produto"** na barra de ferramentas. Exige autenticaÃ§Ã£o prÃ©via (redireciona para `/login` caso nÃ£o esteja autenticado).
- **ValidaÃ§Ã£o com Feedback em Tempo Real**: Mensagens amigÃ¡veis em vermelho caso o usuÃ¡rio submeta campos em branco ou preÃ§o negativo.
- **Alertas de Sucesso**: Ao salvar, o usuÃ¡rio Ã© redirecionado com uma notificaÃ§Ã£o verde de sucesso.

### 3. UPDATE (AtualizaÃ§Ã£o / EdiÃ§Ã£o)
- **FormulÃ¡rio de EdiÃ§Ã£o (`/produtos/editar/{id}`)**: Acionado pelo botÃ£o amarelo com Ã­cone de lÃ¡pis. Os dados atuais sÃ£o carregados automaticamente nos campos para alteraÃ§Ã£o segura.

### 4. DELETE (ExclusÃ£o Segura)
- **ExclusÃ£o com Modal de ConfirmaÃ§Ã£o (`/produtos/excluir/{id}`)**: Acionado pelo botÃ£o vermelho com Ã­cone de lixeira. Abre uma janela modal do Bootstrap exibindo o nome exato do produto para evitar exclusÃµes acidentais.
- **RestriÃ§Ã£o de Acesso**: Somente usuÃ¡rios com perfil `ADMIN` tÃªm autorizaÃ§Ã£o para confirmar a exclusÃ£o.

---

## âš™ï¸ Como Executar o Projeto Localmente

### PrÃ©-requisitos
- JDK 17+ instalado
- Git

### 1. Clonar o RepositÃ³rio
```bash
git clone https://github.com/LeandroGuaridoOliveira/Java-gs4-pt2.git
cd Java-gs4-pt2
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

A aplicaÃ§Ã£o iniciarÃ¡ na porta **`8082`**:
- Interface Principal: [http://localhost:8082](http://localhost:8082)
- CatÃ¡logo de Produtos: [http://localhost:8082/produtos](http://localhost:8082/produtos)
- Console H2: [http://localhost:8082/h2-console](http://localhost:8082/h2-console) (JDBC URL: `jdbc:h2:mem:mercadomvcdb`, UsuÃ¡rio: `sa`, Senha em branco)

### 3. Executar os Testes Automatizados
```bash
mvnw.cmd test
```
*Total de 11 testes unitÃ¡rios e de integraÃ§Ã£o validando rotas pÃºblicas, restriÃ§Ãµes do Spring Security, formulÃ¡rios de validaÃ§Ã£o e operaÃ§Ãµes de CRUD.*

---

## ðŸ–¼ï¸ ConfiguraÃ§Ã£o do Spring Initializr

DependÃªncias configuradas para a Parte 2:
1. **Spring Web** (Spring MVC)
2. **Thymeleaf** (Template Engine)
3. **Spring Security** (AutenticaÃ§Ã£o e AutorizaÃ§Ã£o)
4. **Spring Data JPA** (PersistÃªncia relacional)
5. **Validation** (Bean Validation)
6. **Lombok**
7. **Oracle Driver**
8. **H2 Database**

![Spring Initializr Config](./spring_initializr.png)

---

## ðŸ“¹ Roteiro Sugerido para o VÃ­deo de ApresentaÃ§Ã£o (~5 Minutos)

Para a entrega do vÃ­deo exigido pelo professor:

1. **Abertura (0:00 - 0:45)**:
   - ApresentaÃ§Ã£o dos integrantes (Leandro, Kaiky, Gabriel) e menÃ§Ã£o Ã  turma 2TDSPV e professor Dr. Marcel Stefan Wagner.
   - Breve introduÃ§Ã£o: este projeto Ã© a Parte 2 do CP4, focado em Spring MVC, Thymeleaf e Spring Security.
2. **DemonstraÃ§Ã£o das Rotas PÃºblicas (0:45 - 1:45)**:
   - Acessar a Home (`/`) e mostrar o dashboard com mÃ©tricas de estoque e preÃ§os mÃ©dios.
   - Navegar para `/produtos`: demonstrar o catÃ¡logo pÃºblico e o filtro de busca por nome.
   - Acessar os detalhes de um produto (`/produtos/detalhes/{id}`).
3. **Controle de Acesso com Spring Security (1:45 - 2:30)**:
   - Tentar clicar em "Novo Produto" enquanto deslogado: demonstrar o redirecionamento automÃ¡tico para a tela `/login`.
   - Efetuar o login com o usuÃ¡rio administrador (`admin` / `admin123`).
   - Mostrar o navbar atualizado com a identificaÃ§Ã£o do usuÃ¡rio e o perfil `ADMIN`.
4. **DemonstraÃ§Ã£o Completa do CRUD (2:30 - 4:15)**:
   - **Create**: Cadastrar um novo produto (ex: "Uva Thompson", "Hortifruti", "Frutas e Verduras", "500g", R$ 14.90). Mostrar a validaÃ§Ã£o de erros caso deixe campos em branco e a mensagem de sucesso ao salvar.
   - **Read**: Mostrar o produto recÃ©m-cadastrado na tabela com seu badge especÃ­fico.
   - **Update**: Clicar no botÃ£o de ediÃ§Ã£o, alterar o preÃ§o e salvar.
   - **Delete**: Clicar no botÃ£o de exclusÃ£o, mostrar o modal de confirmaÃ§Ã£o dinÃ¢mico e confirmar a exclusÃ£o.
5. **Encerramento e Deploy (4:15 - 5:00)**:
   - Mostrar a aplicaÃ§Ã£o em funcionamento no link de produÃ§Ã£o do Render.
   - Mostrar o botÃ£o de logout e consideraÃ§Ãµes finais.

---

## ðŸ“„ Estrutura de Arquivos da Entrega (.zip)

O arquivo `.zip` para envio no Teams do professor contÃ©m:
```
mercado-express-mvc.zip
â”œâ”€â”€ integrantes.txt          (Nomes, RMs, turma, links do Git e Deploy)
â”œâ”€â”€ spring_initializr.png     (Print das dependÃªncias)
â”œâ”€â”€ pom.xml                  (ConfiguraÃ§Ã£o Maven)
â”œâ”€â”€ Dockerfile               (Receita de build para deploy)
â”œâ”€â”€ README.md                (DocumentaÃ§Ã£o completa)
â””â”€â”€ src/                     (CÃ³digo fonte Java e templates Thymeleaf)
```
