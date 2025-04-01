API de Agendamento de Barbearia - dBarber
Este README descreve as rotas, expectativas e resultados da API de agendamento para o sistema de barbearia dBarber. O sistema foi desenvolvido usando Spring Boot no backend e se conecta ao frontend Angular.

Endpoints de Cliente
1. Criar um novo cliente
POST /user/save

Corpo da requisição:

json
Copiar
Editar
{
  "name": "Nome do Cliente",
  "email": "email@dominio.com",
  "phone": "12345678901"
}
Resposta esperada:

Código de status 200 OK em caso de sucesso.

Corpo:

json
Copiar
Editar
{
  "id": 1,
  "name": "Nome do Cliente",
  "email": "email@dominio.com",
  "phone": "12345678901"
}
2. Editar um cliente
PUT /user/edit/{id}

Parâmetros:

id (Path): ID do cliente a ser editado.

Corpo da requisição:

json
Copiar
Editar
{
  "name": "Nome Editado",
  "email": "novoemail@dominio.com",
  "phone": "98765432100"
}
Resposta esperada:

Código de status 200 OK em caso de sucesso.

Corpo:

json
Copiar
Editar
{
  "id": 1,
  "name": "Nome Editado",
  "email": "novoemail@dominio.com",
  "phone": "98765432100"
}
3. Excluir um cliente
DELETE /user/delete/{id}

Parâmetros:

id (Path): ID do cliente a ser excluído.

Resposta esperada:

Código de status 204 No Content em caso de sucesso (sem corpo de resposta).

4. Consultar dados de um cliente
GET /user/{id}

Parâmetros:

id (Path): ID do cliente a ser consultado.

Resposta esperada:

Código de status 200 OK em caso de sucesso.

Corpo:

json
Copiar
Editar
{
  "id": 1,
  "name": "Nome do Cliente",
  "email": "email@dominio.com",
  "phone": "12345678901"
}
Endpoints de Horário
1. Marcar um horário
POST /horario/marcar

Corpo da requisição:

json
Copiar
Editar
{
  "clienteId": 1,
  "startAt": "2025-04-01T10:00:00+00:00",
  "endAt": "2025-04-01T11:00:00+00:00"
}
Resposta esperada:

Código de status 200 OK em caso de sucesso.

Corpo:

json
Copiar
Editar
{
  "id": 1,
  "startAt": "2025-04-01T10:00:00+00:00",
  "endAt": "2025-04-01T11:00:00+00:00",
  "client": {
    "id": 1,
    "name": "Nome do Cliente",
    "email": "email@dominio.com",
    "phone": "12345678901"
  }
}
Validações:

O horário de início não pode ser após o horário de término.

Não é permitido marcar horários que se sobreponham a outro agendamento existente.

2. Editar um horário
PUT /horario/editar/{id}

Parâmetros:

id (Path): ID do horário a ser editado.

Corpo da requisição:

json
Copiar
Editar
{
  "startAt": "2025-04-01T11:00:00+00:00",
  "endAt": "2025-04-01T12:00:00+00:00"
}
Resposta esperada:

Código de status 200 OK em caso de sucesso.

Corpo:

json
Copiar
Editar
{
  "id": 1,
  "startAt": "2025-04-01T11:00:00+00:00",
  "endAt": "2025-04-01T12:00:00+00:00",
  "client": {
    "id": 1,
    "name": "Nome do Cliente",
    "email": "email@dominio.com",
    "phone": "12345678901"
  }
}
Validações:

O horário de início não pode ser após o horário de término.

Não é permitido alterar para um horário que se sobreponha a outro agendamento existente.

3. Excluir um horário
DELETE /horario/excluir/{id}

Parâmetros:

id (Path): ID do horário a ser excluído.

Resposta esperada:

Código de status 204 No Content em caso de sucesso (sem corpo de resposta).

Endpoints de Autenticação
1. Login
POST /auth/login

Corpo da requisição:

json
Copiar
Editar
{
  "email": "email@dominio.com",
  "name": "Nome do Cliente"
}
Resposta esperada:

Código de status 200 OK em caso de sucesso.

Corpo:

json
Copiar
Editar
{
  "id": 1,
  "name": "Nome do Cliente"
}
Caso as credenciais estejam incorretas, a resposta será:

Código de status 401 Unauthorized.

Corpo:

json
Copiar
Editar
"Credenciais inválidas"
Considerações
Todos os endpoints têm CORS habilitado, permitindo o acesso apenas ao frontend hospedado em http://localhost:4200.

As validações para horários garantem que não sejam feitos agendamentos conflitantes. Além disso, o horário de início deve ser antes do horário de término.

A API retorna mensagens de erro detalhadas em caso de falha (por exemplo, cliente não encontrado, horário indisponível, etc.).

Esse README fornece uma descrição detalhada das principais rotas da API para manipulação de clientes e agendamentos.
