# language: pt
Funcionalidade: Configuração Inicial do Dojo BDD

  Cenário: Validar que o ambiente de testes está funcional
    Dado que o ambiente de desenvolvimento está configurado
    Quando eu executo a suite de testes
    Então o sistema deve retornar uma mensagem de sucesso