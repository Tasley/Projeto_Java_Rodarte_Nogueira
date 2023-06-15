import React, { useState } from 'react';


const App = () => {
  const [output, setOutput] = useState('');

  const handleListAll = () => {
    fetch("http://localhost:8080/notas/todos")
      .then(response => response.json())
      .then(data => {
        let outputHTML = `
        <table className="styled-table centered-table">
            <thead>
              <tr>
                <th>Identificação</th>
                <th>Nome</th>
                <th>Sexo</th>
                <th>Data de Nascimento</th>
                <th>Nota 1º Trimestre</th>
                <th>Nota 2º Trimestre</th>
                <th>Nota 3º Trimestre</th>
              </tr>
            </thead>
            <tbody>
        `;
  
        data.forEach(nota => {
          outputHTML += `
            <tr>
              <td>${nota.id}</td>
              <td>${nota.nome}</td>
              <td>${nota.sexo}</td>
              <td>${nota.dataNascimento}</td>
              <td>${nota.notaTrimestreUm}</td>
              <td>${nota.notaTrimestreDois}</td>
              <td>${nota.notaTrimestreTres}</td>
            </tr>
          `;
        });
  
        outputHTML += `
            </tbody>
          </table>
        `;
  
        setOutput(outputHTML);
      });
  };

  const handleImportData = () => {
    fetch("http://localhost:8080/notas/extrair-excel", { method: "PATCH" })
      .then(response => response.text())
      .then(data => {
        setOutput(data);
      });
  };

  const handleOrganizedTable = () => {
    fetch("http://localhost:8080/notas/tabela-organizada")
      .then(response => response.json())
      .then(data => {
        let outputHTML = `
        <table className="styled-table centered-table">
          <tr>
            <th>Nome</th>
            <th>Média de Notas</th>
            <th>Idade</th>
          </tr>
        `;

        data.forEach(nota => {
          outputHTML += `
            <tr>
              <td>${nota.nome}</td>
              <td>${nota.media}</td>
              <td>${nota.idade}</td>
            </tr>
          `;
        });

        outputHTML += "</table>";
        setOutput(outputHTML);
      });
  };

  const handleExportExcel = () => {
    fetch("http://localhost:8080/notas/exportar-excel")
      .then(response => response.text())
      .then(data => {
        setOutput(data);
      });
  };

  return (
    <div>
      <h1>Notas App</h1>
      <button onClick={handleListAll}>Listar todos os alunos</button>
      <button onClick={handleImportData}>Importar dados do Excel</button>
      <button onClick={handleOrganizedTable}>Tabela organizada</button>
      <button onClick={handleExportExcel}>Exportar tabela organizada para o Excel</button>
      <div id="output" dangerouslySetInnerHTML={{ __html: output }}></div>
    </div>
  );
};

export default App;