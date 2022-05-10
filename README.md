# SISTEMA UNIVERSITARIO

Atividade em grupo da Let's Code realizade pelo grupo amarelo, repassado para o grupo azul.
[Projeto Raiz](https://github.com/universidade-crescer/sistema-universitario)

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

## 🔃 Manipulação das Rotas de Usuários:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ | --------------------------- | ------------------------------------ |
| GET          | `/usuarios`                 | Retorna todos os usuários            |
| GET          | `/usuarios/{id}`            | Retorna as informações do usuário    |
| POST         | `/usuarios`                 | Cria/cadastra um novo usuário        |
| PUT          | `/usuarios/{idUser}/{email}`| Altera email de um usuário           |
| PUT          | `/usuarios/{idUser}/{senha}`| Altera senha de um usuário           |     
| DELETE       | `/usuarios/{id}`            | Deleta um usuário                    |

## 🔃 Manipulação das Rotas de Endereço:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ | --------------------------- | ------------------------------------ |
| GET          | `/endereco/{id}`            | Retorna as informações do endereço   |
| POST         | `/endereco`                 | Cria/cadastra um novo endereço       |
| PUT          | `/endereco/{id}`            | Altera informações de um endereço    |   
| DELETE       | `/endereco/{id}`            | Deleta um endereço                   |

## 🔃 Manipulação das Rotas de Alunos:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ | --------------------------- | ------------------------------------ |
| GET          | `/aluno/todos-alunos`       | Retorna todos os alunos              |
| GET          | `/aluno/aluno-ativo`        | Retorna alunos ativos                |
| POST         | `/aluno`                    | Cria/cadastra um novo aluno          |
| PUT          | `/aluno/{id}`               | Altera informações de um aluno       |
| DELETE       | `/aluno/deletar-aluno/{id}` | Adiciona status de inativo ao aluno  |

## 🔃 Manipulação das Rotas de Professores:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ | --------------------------- | ------------------------------------ |
| GET          | `/professor/listar-todos`   | Retorna todos os professores         |
| GET          | `/professor/listar-ativos`  | Retorna professores ativos           |
| GET          | `/professor/encontrar/{id}` | Retorna informações de um professor  |
| POST         | `/professor/cadastrar`      | Cria/cadastra um novo professor      |
| PUT          | `/professor/atualizar/{id}` | Altera informações do professor      |
| DELETE       | `/professor/deletar/{id}`   | Adiciona status inativo ao professor |

## 🔃 Manipulação das Rotas de Cursos:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ | --------------------------- | ------------------------------------ |
| GET          | `/cursos/todos-cursos`      | Retorna todos os cursos              |
| GET          | `/cursos/{id}`              | Retorna as informações do curso      |
| POST         | `/cursos/novo-curso`        | Cria/cadastra um novo curso          |
| POST         | `/cursos/add-disciplina`    | Adiciona o relacionamento de curso   |
|@RequestParam |`("idCurso")("idDisciplina")`| com uma disciplina                   |
| PUT          | `/cursos/{id}`              | Altera informações de um curso       |
| DELETE       | `/cursos/deletar/{id}`      | Deleta um curso                      |
| DELETE       | `/cursos/del-disciplina`    | Deleta o relacionamento de curso     |
|@RequestParam |`("idCurso")("idDisciplina")`| com uma disciplina                   |

## 🔃 Manipulação das Rotas de Disciplinas:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ | --------------------------- | ------------------------------------ |
| GET          | `/disciplina`               | Retorna todas as disciplina          |
| POST         | `/disciplina`               | Cria/cadastra uma nova disciplina    |
| PUT          | `/disciplina/{id}`          | Altera informações de uma disciplina |
| DELETE       | `/disciplina/{id}`          | Deleta uma disciplina                |
| DELETE       | `/disciplina/deletar-profe` | Deleta o relacionamento              |
|              | `ssor/{idProf}/{idDisci}`   | do professor(a) com a disciplina     |


## 🔃 Manipulação das Rotas de Turmas:

| Método HTTP  | Endpoint                    | Descrição                            |
| ------------ |  -------------------------- | ------------------------------------ |
| GET          | `/turma`                    | Retorna todas as turmas              |
| GET          | `/turma/{id}`               | Retorna as informações da turma      |
| POST         | `/turma/salvar-turma`       | Cria/cadastra uma nova turma         |
| POST         | `/turma/add-turma-aluno`    | Adiciona o relacionamento de         |
|              | `/{idTurma}/{idAluno}`      | aluno a uma turma                    |
| POST         | `/turma/add-turma-professor`| Adiciona o relacionamento de         |
|              | `/{idTurma}/{idProfessor}`  | professor a uma turma                |
| POST         |`/turma/add-turma-disciplina`| Adiciona o relacionamento de         |
|              | `/{idTurma}/{idDisciplina}` | disciplina a uma turma               |
| PUT          | `/turma/update-turma/{id}`  | Altera informações de uma turma      |
| DELETE       | `/turma/remove-turma/{id}`  | Deleta uma turma                     |
| DELETE       | `/turma/remove-turma-aluno` | Deleta o relacionamento              |
|              | `/{idTurma}/{idAluno}`      | de aluno com a turma                 |
| DELETE       | `/turma/remove-turma-profe` | Deleta o relacionamento              |
|              | `ssor/{idTurma}/{idProf}`   | de professor com a turma             |
| DELETE       | `/turma/remove-turma-disci` | Deleta o relacionamento              |
|              | `plina/{idTurma}/{idDisc}`  | de disciplina com a turma            |

## 🚧 Melhorias para o futuro (Em construção):

* Terminar toda a modelagem do projeto.
* Adicionar mais tabelas ao banco de dados.
* Implementar o front-end na aplicação.

