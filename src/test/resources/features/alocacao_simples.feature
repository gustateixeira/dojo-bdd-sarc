# language: pt
Funcionalidade: Alocação de Recursos Básica
  Como um funcionário da empresa
  Quero poder reservar recursos disponíveis
  Para que eu possa realizar meu trabalho presencialmente

  Contexto:
    Dado que os seguintes recursos estão cadastrados no sistema:
      | Tipo       | Identificador | Detalhe       |
      | Notebook   | NTB-001       | Dell Latitude |
      | Sala       | 404           | 10 Lugares    |
    E que o funcionário "João da Silva" (Matrícula: 1234) está autenticado

  Cenário: Reservar uma sala disponível para um dia específico
    Quando o funcionário tenta alocar a "Sala 404" para a data "15/10/2026"
    Então a alocação deve ser concluída com sucesso
    E o status da "Sala 404" no dia "15/10/2026" deve constar como "Ocupada por João da Silva"

  Cenário: Impedir alocação dupla no mesmo dia
    Dado que a "Sala 404" já está reservada para o dia "15/10/2026"
    Quando o funcionário tenta alocar a "Sala 404" para a data "15/10/2026"
    Então o sistema deve rejeitar a alocação
    E retornar a mensagem de erro "Recurso já alocado para esta data."