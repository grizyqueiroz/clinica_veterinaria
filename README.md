# 🏥 Sistema de Gerenciamento para Clínica Veterinária

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.10-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Sistema web desenvolvido em Spring Boot para gerenciamento completo de clínicas veterinárias, implementando 9 padrões de projeto GoF (Gang of Four).

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Padrões de Projeto Implementados](#padrões-de-projeto-implementados)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [API Endpoints](#api-endpoints)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Autores](#autores)

## 🎯 Sobre o Projeto

Este projeto foi desenvolvido como trabalho acadêmico para a disciplina de **Engenharia de Software III** da **FATEC MOGI DAS CRUZES**, demonstrando a aplicação prática de padrões de projeto em um sistema real de gerenciamento veterinário.

### Objetivos
- Informatizar processos de clínicas veterinárias
- Demonstrar implementação de 9 padrões GoF
- Aplicar arquitetura MVC com Spring Boot
- Criar interface web responsiva e intuitiva

## ✨ Funcionalidades

- 🐕 **Gerenciamento de Pets**: Cadastro, edição e exclusão de animais
- 📅 **Agendamento de Consultas**: Agendamento e controle de consultas veterinárias
- 💳 **Controle de Pagamentos**: Registro e acompanhamento de pagamentos
- 📊 **Dashboard**: Visualização de estatísticas e relatórios
- 🔍 **Busca e Filtros**: Localização rápida de informações
- 📱 **Interface Responsiva**: Compatível com dispositivos móveis

## 🏗️ Padrões de Projeto Implementados

O sistema implementa 9 padrões de projeto do Gang of Four (GoF):

1. **Builder Pattern** - Construção flexível de objetos Pet
2. **Factory Pattern** - Criação de diferentes tipos de pets
3. **Singleton Pattern** - Conexão única com banco de dados
4. **Facade Pattern** - Interface simplificada para operações complexas
5. **Adapter Pattern** - Adaptação de diferentes formatos de dados
6. **DAO Pattern** - Abstração do acesso a dados
7. **Strategy Pattern** - Diferentes estratégias de pagamento
8. **Observer Pattern** - Notificação automática de eventos
9. **Command Pattern** - Encapsulamento de requisições

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 11** - Linguagem de programação
- **Spring Boot 2.7.18** - Framework web
- **Spring MVC** - Padrão arquitetural
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Maven** - Gerenciamento de dependências

### Frontend
- **Thymeleaf** - Template engine
- **Bootstrap 5.3.2** - Framework CSS
- **Font Awesome 6.4.0** - Ícones
- **jQuery 3.7.1** - Biblioteca JavaScript

## ⚙️ Pré-requisitos

- Java 11 ou superior
- Apache Maven 3.6+
- Navegador web moderno

## 🚀 Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/grizyqueiroz/clinica_veterinaria.git
cd clinica_veterinaria
```

2. **Compile o projeto**
```bash
mvn clean compile
```

3. **Execute o sistema**
```bash
mvn spring-boot:run
```

4. **Acesse o sistema**
```
http://localhost:9090
```

## 📖 Como Usar

### Cadastrando um Pet
1. Acesse "Pets" no menu
2. Clique em "Novo Pet"
3. Preencha os dados do animal
4. Clique em "Salvar"

### Agendando uma Consulta
1. Acesse "Consultas" no menu
2. Clique em "Nova Consulta"
3. Selecione o pet
4. Defina data, horário e veterinário
5. Clique em "Agendar"

### Registrando um Pagamento
1. Acesse "Pagamentos" no menu
2. Clique em "Novo Pagamento"
3. Selecione o pet e serviço
4. Informe o valor
5. Clique em "Processar"

## 📁 Estrutura do Projeto

```
Clinica_Vet/
├── src/
│   ├── main/
│   │   ├── java/com/clinicavet/web/
│   │   │   ├── controllers/     # Controladores MVC
│   │   │   ├── models/          # Entidades JPA
│   │   │   ├── services/        # Lógica de negócio
│   │   │   ├── patterns/        # Implementações GoF
│   │   │   └── ClinicaVeterinariaWebApplication.java
│   │   └── resources/
│   │       ├── templates/       # Templates Thymeleaf
│   │       ├── static/          # CSS, JS, imagens
│   │       └── application.properties
│   └── test/                    # Testes unitários
├── pom.xml                      # Dependências Maven
├── README.md                    # Este arquivo
├── LICENSE                      # Licença MIT
└── .gitignore                   # Arquivos ignorados pelo Git
```

## 🔌 API Endpoints

### Pets
- `GET /pets` - Lista todos os pets
- `POST /pets` - Cadastra novo pet
- `GET /pets/{id}` - Busca pet por ID
- `PUT /pets/{id}` - Atualiza pet
- `DELETE /pets/{id}` - Remove pet

### Consultas
- `GET /consultas` - Lista todas as consultas
- `POST /consultas` - Agenda nova consulta
- `GET /consultas/{id}` - Busca consulta por ID

### Pagamentos
- `GET /pagamentos` - Lista todos os pagamentos
- `POST /pagamentos` - Registra novo pagamento

## 🤝 Contribuição

1. Faça um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Autores

- **Griziely Cristina da Silva Queiroz** - [GitHub](https://github.com/grizyqueiroz)
- **Luis Gustavo Placce Alves** 

### Instituição - Curso - Disciplina
- **FATEC MOGI DAS CRUZES**
- **Tecnólogo em Análise e Desenvolvimento de Sistemas**
- **Engenharia de Software III**

---

⭐ Se este projeto te ajudou, deixe uma estrela no repositório!
