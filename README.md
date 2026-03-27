# Lojinha - API de Simulação para E-commerce

Este repositório apresenta o projeto **Lojinha**, uma API desenvolvida em Java para simular operações de um e-commerce. O projeto foi concebido como atividade prática da disciplina de Arquitetura de Software (GPE17M50279), com ênfase na aplicação de conceitos de modelagem UML e padrões de projeto.

## 📂 Estrutura do Projeto

A organização do projeto segue uma estrutura modular, dividida em pacotes que representam as camadas lógicas da aplicação:

| Pacote      | Descrição                                                                                             |
| :---------- | :---------------------------------------------------------------------------------------------------- |
| `model`     | Contém as classes que representam as entidades de domínio do negócio, como `Cliente`, `Produto`, `Pedido`, `ItemPedido` e `Pagamento`. Essas classes encapsulam os dados e o comportamento fundamental do sistema. |
| `service`   | Responsável por abrigar a lógica de negócio e as regras de orquestração. Inclui classes como `LojinhaService`, que coordena as operações da loja, e `SistemaPagamentoExterno`, que simula a interação com um gateway de pagamento externo. |
| `principal` | Contém a classe `Main` (ponto de entrada da aplicação), utilizada para inicializar o sistema e demonstrar suas funcionalidades. |

## 🏛️ Principais Decisões Arquiteturais

A arquitetura do projeto foi guiada por princípios de **separação de responsabilidades** e **baixo acoplamento**, visando facilitar a manutenção, a escalabilidade e a testabilidade do código. A divisão em pacotes `model`, `service` e `principal` reflete essa preocupação, isolando as preocupações de domínio, lógica de negócio e inicialização da aplicação, respectivamente.

## 🎯 Aplicação do Padrão Singleton

O padrão de projeto **Singleton** foi aplicado na classe `SistemaPagamentoExternoSingleton`. Esta decisão arquitetural garante que haja **apenas uma instância** do serviço de pagamento externo em toda a aplicação. Os benefícios dessa abordagem incluem:

- **Controle de Recursos**: Evita a criação desnecessária de múltiplas conexões ou instâncias de serviços que consomem muitos recursos, como um gateway de pagamento.
- **Ponto de Acesso Global**: Fornece um ponto de acesso único e centralizado para o serviço de pagamento, simplificando a sua utilização por diferentes partes da aplicação.
- **Consistência**: Garante que todas as interações com o sistema de pagamento externo ocorram através da mesma instância, mantendo a consistência dos dados e do estado.

## ⚙️ Como Executar

1.  Certifique-se de ter o **JDK 17** e o **Maven** instalados.
2.  Clone o repositório:
    ```bash
    git clone https://github.com/eduuardo1st/Lojinha.git
    ```
3.  Navegue até a pasta do projeto e execute o build:
    ```bash
    mvn clean install
    ```
4.  Execute a classe principal no pacote `principal`.
