# language: pt
Funcionalidade: Alocação de Recursos
  Como um funcionário do IT Academy
  Eu quero poder reservar notebooks, salas e laboratórios
  Para que os alunos possam aprender

  Contexto:
    Dado que existem recursos e funcionários cadastrados no sistema

  Cenário: Alocação bem-sucedida de um recurso disponível
    Dado que o funcionário "10002020" deseja alocar o notebook "CEPES1983"
    Quando ele solicita a reserva para a data "2026-06-12"
    Então o sistema deve confirmar a alocação com sucesso