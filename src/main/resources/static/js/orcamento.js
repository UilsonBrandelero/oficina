document.addEventListener("DOMContentLoaded", function () {
  const orcamento = document.getElementById("listaOrcamento");
  async function fetchOrcamentos(url) {
    try {
      let data = await fetch(url);
      let response = await data.json();
      console.log(response);

      for (i = 0; i < response.length; i++) {
        let id = response[i].id;
        let cliente = response[i].cliente.nomeCliente;
        let valorTotal = response[i].valorDescontado;
        let data = new Date(response[i].dataOrcamento);
        console.log(id);
        console.log(cliente);
        console.log(valorTotal);
        console.log(data);

        orcamento.innerHTML += `
              <div class="card cartao" style="width: 18rem;">
                  <img src="https://cdn-icons-png.flaticon.com/512/126/126391.png" class="card-img-top" alt="...">
                  <div class="card-body">
                    <h5 class="card-title">${cliente}</h5>
                    <p class="card-text">ID ${id}</p>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item">Valor R$ ${valorTotal.toFixed(
                      2
                    )}</li>
                    <li class="list-group-item">Data ${data.toLocaleString(
                      "pt-BR",
                      {
                        timezone: "UTC",
                      }
                    )}</li>
                    
                  </ul>
                  <div class="card-body">
                    <a href="#" class="card-link">Card link</a>
                    <a href="#" class="card-link">Another link</a>
                  </div>
                </div>
                              `;
      }
    } catch (error) {
      console.error("Erro ao carregar orcmantos", error);
    }
  }
  fetchOrcamentos("http://localhost:8080/orcamento/listar_orcamentos");
  // Obter os botões de rádio
  const radios = document.querySelectorAll(
    'input[type="radio"][name="btnradio"]'
  );

  // Obter o contêiner onde o conteúdo será alterado
  const content = document.getElementById("listaOrcamento");

  // Função para alterar o conteúdo
  function changeContent() {
    if (document.getElementById("btnradio1").checked) {
      content.innerHTML = ``;
      fetchOrcamentos("http://localhost:8080/orcamento/listar_orcamentos");

      //content.innerHTML = `<h1>Deu Boa</h1>`;
    } else if (document.getElementById("btnradio2").checked) {
      content.innerHTML = ``;
      formularioOrcamento();
    } else if (document.getElementById("btnradio3").checked) {
      content.innerHTML = "<p>Conteúdo para Editar.</p>";
    } else if (document.getElementById("btnradio4").checked) {
      content.innerHTML = "<p>Conteúdo para Excluir.</p>";
    }
  }

  // Adicionar um ouvinte de evento a cada botão de rádio
  radios.forEach((radio) => {
    radio.addEventListener("change", changeContent);
  });

  // Chamar a função uma vez para definir o conteúdo inicial
  changeContent();

  function formularioOrcamento() {
    const formulario = document.getElementById('listaOrcamento');
    async function fetchClientes(url) {
    try {
        let data = await fetch(url);
        let response = await data.json();
        console.log(response);

        formulario.innerHTML = `
     <h3>Para qual Cliente deseja gerar um Orçamento?</h3>
        <div class="col-8">
          <div id="scroll" data-bs-spy="scroll" data-bs-target="navbar bg-body-tertiary px-3 mb-3" 
               data-bs-offset="0" data-bs-smooth-scroll="true" class="scrollspy-example" tabindex="0">
            <!-- Aqui os clientes serão inseridos dinamicamente -->
          </div>
        </div>
      `;
        const scroll = document.getElementById('scroll');
        for (i = 0; i < response.length; i++) {
          let id = response[i].id;
          let nome = response[i].nomeCliente;
          let telefone = response[i].telefoneCliente;
          let rua = response[i].enderecoCliente.rua;
          let bairro = response[i].enderecoCliente.bairro;
          let numero = response[i].enderecoCliente.numero;
          let cep = response[i].enderecoCliente.cep;
          let cidade = response[i].enderecoCliente.cidade;

          scroll.innerHTML += `
<div class="card cartaoCliente">
        <div class="card-header">
          Id: ${id}
        </div>
        <div class="card-body">
          <h5 class="card-title">${nome}</h5>
          <p class="card-text">${telefone}</p>
          <a href="#" class="btn btn-primary" id="cliente-${id}">Novo Orçamento</a>
        </div>
      </div>
`;
        }
        formulario.innerHTML += `
    
      <div class="rodape">

        <div class="progress" role="progressbar" aria-label="Example 3px high" aria-valuenow="25" aria-valuemin="0"
          aria-valuemax="100" style="height: 5px">
          <div class="progress-bar" style="width: 10%"></div>
        </div>

      </div>
    `;
      
    } catch (error) {
      console.log("Erro ao carregar clientes: ", error);
    }
  }
    fetchClientes("http://localhost:8080/cliente/listar_clientes");

  }
});
