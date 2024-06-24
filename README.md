# Hopin

O Hopin é um projeto desenvolvido para fins de estudo do Spring Boot, com o objetivo de replicar funcionalidades básicas do Uber.

## Stack
[![My Skills](https://skillicons.dev/icons?i=java,spring,postgres,docker&theme=dark)](https://skillicons.dev) 
<img src="https://github.com/lucasramallo/picpay-backend-challenge/assets/108425719/f4bd08c4-a579-4fe0-942d-c5f7b8a036f3" alt="Descrição da imagem" width="48">

## Sobre o projeto

Simulação de uma API para um aplicativo de transporte que permita:

- Registro e autenticação de usuários e motoristas.
- Solicitação e conclusão de corridas.
- Processamento de pagamentos e gerenciamento de faturas.
- Comunicação em tempo real entre usuários e motoristas.

## 3. Funcionalidades do Sistema

### 3.1 Registro e Autenticação

- **Registro de Usuários**: Usuários podem criar contas usando email, número de telefone e senha.
- **Registro de Motoristas**: Motoristas podem criar contas e enviar documentação para verificação.
- **Autenticação**: Login e logout para usuários e motoristas, com suporte para autenticação via redes sociais (Google, Facebook).

### 3.2 Gerenciamento de Perfil

- **Perfil do Usuário**: Visualizar e editar informações pessoais, histórico de corridas.
- **Perfil do Motorista**: Visualizar e editar informações pessoais, histórico de corridas, status de disponibilidade.

### 3.3 Solicitação de Corridas

- **Solicitação de Corrida**: Usuários podem solicitar corridas especificando o ponto de partida e destino.

### 3.4 Pagamentos e Faturamento

- **Processamento de Pagamentos**: Integração com gateways de pagamento para cobrar usuários e pagar motoristas.
- **Faturas**: Geração de faturas detalhadas para usuários e motoristas.

### 3.5 Comunicação

- **Mensagens em Tempo Real**: Chat entre usuários e motoristas antes e durante a corrida.
- **Notificações**: Notificações push para atualizações sobre o status da corrida, pagamento, etc.

## 4. Requisitos Não Funcionais

### 4.1 Segurança

- Implementação de autenticação e autorização seguras.
- Criptografia de dados sensíveis.
- Conformidade com regulamentações de proteção de dados (por exemplo, GDPR).

### 4.5 Manutenibilidade

- Código bem documentado e modular.
- Testes automatizados (unitários, integração).
