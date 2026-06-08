# Sistema de Reserva de Recursos - Laboratório de BDD com Cucumber

Este repositório foi estruturado no formato Prof. CI para servir como base prática no ensino de BDD (Behavior-Driven Development) utilizando Cucumber-JVM e Java 21. O objetivo é guiar os alunos no entendimento de como especificações executáveis podem guiar o desenvolvimento de regras de negócio e prevenir regressões em um pipeline automatizado de Integração Contínua.

---

## Contexto do Exercício Integrador 2

O sistema consiste em uma plataforma de reserva de recursos corporativos (notebooks, salas e laboratórios), que podem ser alocados pelos funcionários de uma empresa. 

### Entidades e Informações do Sistema
* **Notebook:** Número de Patrimônio, Data de aquisição e Descrição.
* **Sala:** Número, Lugares e Presença de Projetor (Sim/Não).
* **Laboratório:** Nome, Quantidade de computadores e Descrição da configuração dos computadores.
* **Funcionário:** Matrícula, Nome Completo, Cargo e Data de admissão.

### Regras de Negócio Iniciais
* Instâncias desses recursos devem estar cadastradas previamente no sistema (mínimo de 3 instâncias para cada entidade).
* A alocação é feita para uma data específica e por todo o dia, não podendo haver mais de uma alocação do mesmo recurso por dia.
* Somente um usuário poderá fazer a alocação de um determinado recurso para um respectivo dia.
* Um usuário poderá reservar um notebook e uma sala para o mesmo dia, mas não poderá reservar quaisquer outras combinações de recursos (como um laboratório junto com outro recurso).

---

## Tecnologias Utilizadas

* **Java 21:** Uso de funcionalidades modernas da linguagem, como Pattern Matching para o operador `instanceof`.
* **Cucumber JVM 7.15:** Framework para automação de testes baseados em comportamento (BDD).
* **JUnit 5 (Platform Suite Engine):** Para orquestração e execução da suíte de testes do Cucumber.
* **GitHub Actions:** Configuração do pipeline Prof. CI para validação automática dos Pull Requests.

---

## Estrutura do Projeto

O projeto está dividido de forma clara entre o código de produção (`main`) e o código de testes/especificações (`test`), seguindo os padrões recomendados:

```text
src/
├── main/
│   └── java/
│       └── cepes/itacademy/sarc/
│           ├── domain/         # Modelos de domínio (Funcionário, Recursos e Alocação)
│           ├── exception/      # Exceção base de regras de negócio
│           ├── repository/     # Interfaces e implementações de repositórios em memória
│           └── service/        # Camada de serviços com as regras de alocação
└── test/
    ├── java/
    │   ├── cepes/itacademy/runner/  # Classe executora (CucumberRunnerTest)
    │   └── steps/                   # Definição de passos em Java (Step Definitions)
    └── resources/
        └── features/                # Arquivos Gherkin com cenários de especificação
```

---

## Adaptações para o Exercício de BDD

Para criar uma dinâmica educacional eficiente, o repositório original foi disponibilizado com algumas particularidades:

* **Dados Pré-populados:** Os repositórios em memória (`InMemoryFuncionarioRepository` e `InMemoryRecursoRepository`) já iniciam instanciados com 3 exemplos de cada entidade, conforme exigido pelo enunciado.
* **Código Propositalmente Incompleto**
* **Exemplo Base Funcional:** O arquivo `alocacao.feature` e a classe `AlocacaoSteps.java` contêm apenas o cenário de "Caminho Feliz" (alocação bem-sucedida de um recurso disponível) configurado e passando.

---

## Exercício Proposto

Os grupos deverão aplicar os conceitos de BDD e Engenharia de Software para corrigir e garantir a integridade do sistema seguindo os passos abaixo:

1. **Criar um Fork:** Cada grupo deve realizar um fork deste repositório para o seu perfil no GitHub.
2. **Escrever as Especificações (Gherkin):** Identificar as regras de negócio violadas no enunciado e criar os cenários de falha correspondentes dentro do arquivo `alocacao.feature`.
3. **Automação dos Testes (Step Definitions):** Executar o runner para obter os snippets do Cucumber, adicioná-los à classe `AlocacaoSteps.java` e implementar as asserções de erro necessárias. Os testes devem falhar inicialmente (fase Red).
4. **Implementar as Regras de Negócio:** Alterar a classe `AlocacaoService` para aplicar as validações obrigatórias. Recomenda-se a criação de exceções específicas que herdem de `RegraNegocioException` para manter o design de código limpo.
5. **Validar Localmente:** Executar o `CucumberRunnerTest` até que todos os cenários (sucesso e falhas) fiquem verdes.
6. **Submeter Pull Request (Prof. CI):** Abrir um Pull Request de volta para o repositório original. O pipeline automatizado do GitHub Actions será disparado para validar se as implementações do grupo atendem aos critérios de aceitação exigidos pelo professor.

---

## Créditos e Desenvolvimento

Este projeto de arquitetura educacional e seu ambiente de testes integrado foram "vibe-codados" com o auxílio do Gemini e impulsionados pelo autocomplete do IntelliJ IDEA.
