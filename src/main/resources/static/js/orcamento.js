document.addEventListener('DOMContentLoaded', function () {
  let orcamento = document.querySelector('.listaOrcamento');
  async function fetchProducts(url) {
    try {
      let data = await fetch(url);
      let response = await data.json();
      console.log(response)

      for (i = 0; i <= response.length; i++) {
        let id = response[i].id
        let cliente = response[i].cliente.nomeCliente
        let valorTotal = response[i].valorDescontado
        let data = new Date(response[i].dataOrcamento)
        console.log(id)
        console.log(cliente)
        console.log(valorTotal)
        console.log(data)

        orcamento.innerHTML += `
               <div class="card cartao" style="width: 18rem;">
  <img src="https://cdn-icons-png.flaticon.com/512/126/126391.png" class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">${cliente}</h5>
    <p class="card-text">ID ${id}</p>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Valor R$ ${valorTotal.toFixed(2)}</li>
    <li class="list-group-item">Data ${data.toLocaleString('pt-BR', { timezone: 'UTC' })}</li>
    
  </ul>
  <div class="card-body">
    <a href="#" class="card-link">Card link</a>
    <a href="#" class="card-link">Another link</a>
  </div>
</div>
               `
      }
    }
    catch {

    }
  }
  fetchProducts('http://localhost:8080/orcamento/listar_orcamentos')

}
)

// Obter os botões de rádio
const radios = document.querySelectorAll('input[type="radio"][name="btnradio"]');

// Obter o contêiner onde o conteúdo será alterado
const content = document.getElementById('listaOrcamento');

// Função para alterar o conteúdo
function changeContent() {
  if (document.getElementById('btnradio1').checked) {
    fetchProducts('http://localhost:8080/orcamento/listar_orcamentos')
    
    //content.innerHTML = `<h1>Deu Boa</h1>`;
  } else if (document.getElementById('btnradio2').checked) {
    content.innerHTML = '<p>Conteúdo para Novo Orçamento.</p>';
  } else if (document.getElementById('btnradio3').checked) {
    content.innerHTML = '<p>Conteúdo para Editar.</p>';
  } else if (document.getElementById('btnradio4').checked) {
    content.innerHTML = '<p>Conteúdo para Excluir.</p>';
  }
}

// Adicionar um ouvinte de evento a cada botão de rádio
radios.forEach(radio => {
  radio.addEventListener('change', changeContent);
});

// Chamar a função uma vez para definir o conteúdo inicial
changeContent();

async function fetchProducts(url) {
  try {
    let data = await fetch(url);
    let response = await data.json();
    console.log(response)

    for (i = 0; i <= response.length; i++) {
      let id = response[i].id
      let cliente = response[i].cliente.nomeCliente
      let valorTotal = response[i].valorDescontado
      let data = new Date(response[i].dataOrcamento)
      console.log(id)
      console.log(cliente)
      console.log(valorTotal)
      console.log(data)

      orcamento.innerHTML += `
             <div class="card cartao" style="width: 18rem;">
<img src="https://cdn-icons-png.flaticon.com/512/126/126391.png" class="card-img-top" alt="...">
<div class="card-body">
  <h5 class="card-title">${cliente}</h5>
  <p class="card-text">ID ${id}</p>
</div>
<ul class="list-group list-group-flush">
  <li class="list-group-item">Valor R$ ${valorTotal.toFixed(2)}</li>
  <li class="list-group-item">Data ${data.toLocaleString('pt-BR', { timezone: 'UTC' })}</li>
  
</ul>
<div class="card-body">
  <a href="#" class="card-link">Card link</a>
  <a href="#" class="card-link">Another link</a>
</div>
</div>
             `
    }
  }
  catch {

  }
}