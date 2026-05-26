# 🐾 Sistema Pet Shop — Paraíso dos Pets

Sistema desktop de gerenciamento para pet shop desenvolvido em **Java com Swing**, com interface gráfica para login, cadastro de animais e agendamento de serviços.

---

## 📋 Funcionalidades

- **Tela de Abertura** — apresentação do sistema com os serviços oferecidos
- **Autenticação** — login com validação de usuário e senha
- **Cadastro de Animais** — adicionar, consultar e excluir animais e seus donos
- **Agendamento de Serviços** — agendar, consultar e excluir agendamentos de banho, tosa e consulta veterinária

---

## 🔗 Diagrama de Navegação

```
TelaAbertura
    └── [Entrar] → TelaLogin
                      └── [Login válido] → TelaPrincipal
                                              └── [Agendamento →] → TelaAgendamento
                                                                        └── [← Voltar] → TelaPrincipal
```

---

## 🚀 Como Executar

### Pré-requisitos

- **Java JDK 8** ou superior instalado
- IDE com suporte a Java (Eclipse, IntelliJ IDEA, NetBeans) **ou** terminal com `javac`

### Pelo terminal

```bash
# Compilar todos os arquivos
javac sistema_do_petshop/*.java

# Executar
java sistema_do_petshop.Main
```

### Pela IDE

1. Importe o projeto como um projeto Java simples
2. Certifique-se de que todos os arquivos estão no pacote `sistema_do_petshop`
3. Execute a classe `Main.java`

---

## 🔐 Credenciais Padrão

| Campo   | Valor         |
|---------|---------------|
| Usuário | `admin`       |
| Senha   | `123`         |

> As credenciais são definidas diretamente em `Main.java` e podem ser alteradas no código.

---

## 🧩 Modelagem das Classes

| Classe        | Responsabilidade                                              |
|---------------|---------------------------------------------------------------|
| `Usuario`     | Armazena credenciais e realiza autenticação                   |
| `Cliente`     | Representa o dono do animal (nome, telefone, e-mail)         |
| `Animal`      | Representa o pet com referência ao seu dono (`Cliente`)       |
| `Servico`     | Define tipo, preço e descrição de um serviço                  |
| `Agendamento` | Associa um `Animal` a um `Servico` em uma data e horário      |

---

## 🛠️ Tecnologias

- **Java SE** — linguagem principal
- **Java Swing** — interface gráfica
- **ArrayList** — armazenamento em memória das listas de animais e agendamentos

---

## ⚠️ Observações

- Os dados são armazenados **apenas em memória** durante a execução. Ao fechar o programa, todas as informações são perdidas.
- Não há integração com banco de dados nesta versão.
- A lista de animais cadastrados na `TelaPrincipal` é passada diretamente para a `TelaAgendamento`, mantendo os dados consistentes durante a sessão.
