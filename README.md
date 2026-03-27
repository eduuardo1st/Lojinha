# Lojinha - Simulação de API de Loja Online

Este repositório contém o projeto **Lojinha**, desenvolvido como parte da atividade prática da disciplina de **Arquitetura de Software (GPE17M50279)**. O objetivo principal é simular o funcionamento de uma API para uma loja online, aplicando conceitos fundamentais de modelagem UML e padrões de projeto.

## 🚀 Sobre o Projeto

O projeto consiste em um sistema em Java que gerencia o fluxo básico de uma loja, desde o cadastro de clientes e produtos até o processamento de pedidos e pagamentos. A arquitetura foi pensada para demonstrar a organização de pacotes e a separação de responsabilidades.

## 🛠️ Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Maven**: Gerenciador de dependências e automação de build.
- **Padrões de Projeto (Design Patterns)**: Implementação de soluções consolidadas para problemas recorrentes.

## 📂 Estrutura do Projeto

A estrutura de pacotes está organizada da seguinte forma:

- `model`: Contém as entidades de domínio do sistema.
  - `Cliente`: Representa o usuário comprador.
  - `Produto`: Detalhes dos itens disponíveis na loja.
  - `Pedido`: Gerencia a lista de itens e o estado da compra.
  - `ItemPedido`: Associação entre produtos e quantidades em um pedido.
  - `Pagamento`: Define as regras e estados financeiros.
- `service`: Concentra a lógica de negócio e integrações.
  - `LojinhaService`: Orquestra as operações principais da loja.
  - `SistemaPagamentoExterno`: Simulação de integração com gateways de pagamento.
- `principal`: Classe de entrada (`Main`) para execução e testes do sistema.

## 🏗️ Padrões de Projeto Aplicados

- **Singleton**: Utilizado na classe `SistemaPagamentoExternoSingleton` para garantir que exista apenas uma instância do serviço de pagamento externo em toda a aplicação, otimizando o uso de recursos.

## ⚙️ Como Executar

1. Certifique-se de ter o **JDK 11+** e o **Maven** instalados.
2. Clone o repositório:
   ```bash
   git clone https://github.com/eduuardo1st/Lojinha.git
   ```
3. Navegue até a pasta do projeto e execute o build:
   ```bash
   mvn clean install
   ```
4. Execute a classe principal no pacote `principal`.
