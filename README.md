# Sistema de Reserva de Recursos - Laboratório de BDD com Cucumber

Este repositório foi estruturado no formato Prof. CI para servir como base prática no ensino de BDD (Behavior-Driven Development) utilizando Cucumber-JVM e Java 21. O objetivo é guiar os alunos no entendimento de como especificações executáveis podem guiar o desenvolvimento de regras de negócio e prevenir regressões em um pipeline automatizado de Integração Contínua.

---

## Contexto do Exercício Integrador 2

O sistema consiste em uma plataforma de reserva de recursos corporativos (notebooks, salas e laboratórios), que podem ser alocados pelos funcionários de uma empresa.

### Entidades e Informações do Sistema
* **Notebook:** Número de Patrimônio, Data de aquisição e Descrição.
* **Room (Sala):** Número, Lugares e Presença de Projetor (Sim/Não).
* **Lab (Laboratório):** Nome, Quantidade de computadores e Descrição da configuração dos computadores.
* **Collaborator (Funcionário):** Matrícula, Nome Completo, Cargo e Data de admissão.

### Regras de Negócio Iniciais
* Instâncias desses recursos devem estar cadastradas previamente no sistema (mínimo de 3 instâncias para cada entidade).
* A alocação é feita para uma data específica e por todo o dia, não podendo haver mais de uma alocação do mesmo recurso por dia.
* Somente um usuário poderá fazer a alocação de um determinado recurso para um respectivo dia.
* Um usuário poderá reservar um notebook e uma sala para o mesmo dia, mas não poderá reservar quaisquer outras combinações de recursos (como um laboratório junto com outro recurso).

---

## Tecnologias Utilizadas

* **Java 21:** Uso de funcionalidades modernas da linguagem, como Pattern Matching para o operador `instanceof`.
* **Cucumber JVM 7.15:** Framework para automação de testes baseados em comportamento (BDD) com suporte a especificações em inglês.
* **JUnit 5 (Platform Suite Engine):** Para orquestração e execução da suíte de testes do Cucumber via JUnit Platform.
* **GitHub Actions:** Configuração do pipeline Prof. CI para validação automática dos Pull Requests.

---

## Estrutura do Projeto

O projeto está dividido de forma clara entre o código de produção (`main`) e o código de testes/especificações (`test`), mapeado detalhadamente a seguir:

```text
src/
├── main/
│   └── java/
│       └── cepes/itacademy/sarc/
│           ├── domain/         # Modelos de domínio (Allocation, Collaborator, Lab, Notebook, Resource, Room)
│           ├── exception/      # Exceção base de regras de negócio (BusinessRuleException)
│           ├── repository/     # Interfaces e implementações em memória (AllocationRepository, InMemoryAllocationRepository, etc.)
│           └── service/        # Camada de serviços com as regras de alocação (AllocationService)
└── test/
    ├── java/
    │   ├── cepes/itacademy/sarc/
    │   │   ├── runner/         # Classe executora da suíte BDD (CucumberRunnerTest)
    │   │   └── service/        # Testes unitários/sociáveis da regra de negócio (AllocationServiceTest)
    │   └── steps/              # Definição de passos em Java (AllocationSteps)
    └── resources/
        ├── features/                 # Arquivos Gherkin com os cenários (allocation.feature)
        └── junit-platform.properties # Configurações da plataforma de testes
```

---

## Adaptações para o Exercício de BDD

Para criar uma dinâmica educacional eficiente, o repositório original foi disponibilizado com algumas particularidades:

* **Dados Pré-populados:** Os repositórios em memória (`InMemoryCollaboratorRepository` e `InMemoryResourceRepository`) já iniciam instanciados com 3 exemplos de cada entidade, simulando a persistência inicial exigida pelo enunciado.
* **Código Propositalmente Incompleto:** A classe `AllocationService` foi implementada de maneira ingênua. O método `allocateResource` apenas cria o objeto de alocação e o salva no repositório, permitindo que todas as regras de negócio complexas do enunciado sejam violadas.
* **Exemplo Base Funcional:** O arquivo `allocation.feature` e a classe `AllocationSteps.java` contêm apenas o cenário de "Caminho Feliz" (alocação bem-sucedida de um recurso disponível) configurado e passando com as especificações escritas em inglês.

---

## Dinâmica do Coding Dojo Randori

Para a resolução deste desafio, utilizaremos a dinâmica do **Coding Dojo Randori**. Os alunos serão divididos em grupos de **4 integrantes**, onde todos trabalharão colaborativamente no mesmo código, alternando papéis em ciclos de tempo definidos.

### Papéis e Responsabilidades no Grupo

Cada membro do grupo assumirá um papel específico por turno. É obrigatório que todos passem por todos os papéis durante a atividade.

* **Piloto (1 integrante):**
    * **Responsabilidade:** É o único que toca no teclado e digita o código.
    * **Postura:** Deve expressar em voz alta o que está pensando e digitando. Ele não toma decisões sozinho; ele traduz em código as instruções e o consenso gerado pelo Copiloto e pela Plateia.

* **Copiloto (1 integrante):**
    * **Responsabilidade:** É o guia do Piloto. Fica focado no momento presente e no próximo passo imediato.
    * **Postura:** Ajuda o Piloto a pensar na sintaxe, aponta erros de digitação em tempo real e garante que o código está seguindo o comportamento descrito no cenário do Cucumber. Ele pensa junto com o Piloto para tirá-los do estado "Red" (teste falhando).

* **Plateia Ativa (2 integrantes):**
    * **Responsabilidade:** Observadores estratégicos focados no panorama geral (Design do código, Clean Code e Regras de Negócio).
    * **Postura:** Devem permanecer em silêncio enquanto o Piloto e o Copiloto estão codificando para não gerar poluição sonora. No entanto, podem intervir imediatamente caso o Piloto e o Copiloto fiquem travados, ou quando o teste passar (estado "Green"), ajudando a direcionar a fase de refatoração (*Refactor*).

### Rotação

Ao final de cada ciclo de tempo:

1. O **Piloto** sai do teclado e entra na **Plateia**.
2. O **Copiloto** assume o teclado e torna-se o novo **Piloto**.
3. Um membro da **Plateia** torna-se o novo **Copiloto**.
4. O ciclo reinicia com o novo cenário ou refatoração pendente.

---
## Exercício Proposto

Os grupos deverão aplicar os conceitos de BDD e Engenharia de Software para corrigir e garantir a integridade do sistema seguindo os passos abaixo:

1. **Criar um Fork:** Cada grupo deve realizar um fork deste repositório para o seu perfil no GitHub.
2. **Escrever as Especificações (Gherkin):** Identificar as regras de negócio violadas no enunciado e criar os cenários de falha correspondentes dentro do arquivo `allocation.feature` utilizando a sintaxe em inglês.
3. **Automação dos Testes (Step Definitions):** Executar o runner para obter os snippets do Cucumber, adicioná-los à classe `AllocationSteps.java` e implementar as asserções de erro necessárias utilizando o estado capturado. Os testes devem falhar inicialmente (fase Red).
4. **Implementar as Regras de Negócio:** Alterar a classe `AllocationService` para aplicar as validações obrigatórias. Recomenda-se a criação de exceções específicas que herdem de `BusinessRuleException` para manter o design de código limpo e aderente às boas práticas de OO.
5. **Validar Localmente:** Executar o `CucumberRunnerTest` até que todos os cenários (sucesso e falhas) fiquem verdes (fase Green).
6. **Submeter Pull Request (Prof. CI):** Abrir um Pull Request de volta para o repositório original. O pipeline automatizado do GitHub Actions será disparado para validar se as implementações do grupo atendem aos critérios de aceitação exigidos pelo professor.

---

## Créditos e Desenvolvimento

Este projeto de arquitetura educacional e seu ambiente de testes integrado foram "vibe-codados" com o auxílio do Gemini e impulsionados pelo autocomplete do IntelliJ IDEA.