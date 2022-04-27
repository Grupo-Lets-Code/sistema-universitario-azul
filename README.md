# SISTEMA UNIVERSITARIO

Atividade em grupo da Let's Code realizade pelo grupo amarelo.

## 💻 Sobre o projeto: 

O objetivo deste projeto é a construção de um sistema para universidade. Contendo cadastro de alunos e professores, pare ter auxílio as suas disciplinas e turmas, e ter uma organização com um diário de classe.



## 👨‍💻 Alunos:

- [Allan Lopreti](https://www.linkedin.com/in/allan-lopreti/)
- [Allan Felintro](https://www.linkedin.com/in/allanfelintro/)
- [Caio Paiva](https://www.linkedin.com/in/caio-s-paiva/)
- [Marcos Nofre](https://www.linkedin.com/in/marcos-nofre-79b29b20a/)
- [Rafael Dorta](https://www.linkedin.com/in/rafael-alves-dorta-6642b41b9/)
- [Wendel Henrique](https://www.linkedin.com/in/wendel-henrique-bispo-de-jesus-b940221b7/)

## 🛠️ Modelagem:


<img src = "./universitario/universitario/assets/modelagem endpoints.png"  width="350" height="300"/>

A primeira modelagem foi para definir a estrutura do projeto e os endpoints


<img src = "./universitario/universitario/assets/Modelagem BD Sistema Universitário.png"  width="350" height="300"/>

Modelagem original do projeto para Sistema Universitário onde adequamos ao MVP (Mínimo Produto Viável) para nossa primeira entrega ao cliente focando na gestão de turmas


<img src = "./universitario/universitario/assets/BD UNIVERSIDADE CRESCER.png"  width="350" height="300"/>


## 🔃 Manipulação das Rotas de Alunos:

| Método HTTP  | Endpoint                | Descrição                            |
| ------------ | ----------------------- | ------------------------------------ |
| GET          | `/aluno`                | Retorna todos os alunos              |
| POST         | `/aluno`                | Cria/cadastra um novo aluno          |
| PUT          | `/aluno/{id}`           | Altera informações de um aluno       |


## 🔃 Manipulação das Rotas de Disciplinas:

| Método HTTP  | Endpoint                | Descrição                            |
| ------------ | ----------------------- | ------------------------------------ |
| GET          | `/disciplina`           | Retorna todas as disciplina          |
| POST         | `/disciplina`           | Cria/cadastra uma nova disciplina    |
| PUT          | `/disciplina/{id}`      | Altera informações de uma disciplina |
| DELETE       | `/disciplina/{id}`      | Deleta uma disciplina                |


## 🔃 Manipulação das Rotas de Turmas:

| Método HTTP  | Endpoint                | Descrição                            |
| ------------ | ----------------------- | ------------------------------------ |
| GET          | `/turma`                | Retorna todas as turmas              |
| POST         | `/turma`                | Cria/cadastra uma nova turma         |
| DELETE       | `/turma/{id}`           | Deleta uma turma                     |


## 🚧 Melhorias para o futuro (Em construção):

* Terminar toda a modelagem do projeto.
* Adicionar mais tabelas ao banco de dados.
* Implementar o front-end na aplicação.

