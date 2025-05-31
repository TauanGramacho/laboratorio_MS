const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const port = 3000;

app.use(bodyParser.json());

// Endpoint de exemplo para inserir laboratório
app.post('/laboratorios', (req, res) => {
  const lab = req.body;
  console.log('Laboratório recebido:', lab);
  // Aqui você faria a inserção no banco de dados

  res.status(201).send({ message: 'Laboratório criado com sucesso!', lab });
});

app.listen(port, () => {
  console.log(`Microserviço rodando em http://localhost:${port}`);
});
