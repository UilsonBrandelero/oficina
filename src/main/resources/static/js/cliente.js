document.addEventListener('DOMContentLoaded', function(){
    let clientes = document.querySelector('.listaCliente');
    async function fetchClientes(url) {
        let data = await fetch(url);
        let response = await data.json();
        console.log(response)
        for(i = 0; i <= response.length;i++){
            let nome = response[i].nomeCliente;
            let telefone = response[i].telefoneCliente;
            let rua = response[i].enderecoCliente.rua; 
            let bairro = response[i].enderecoCliente.bairro; 
            let numero = response[i].enderecoCliente.numero;
            let cep = response[i].enderecoCliente.cep;
            let cidade = response[i].enderecoCliente.cidade;


            
            clientes.innerHTML += `
            <div class="accordion accordion-flush" id="accordionFlushExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne-${nome}" aria-expanded="false" aria-controls="flush-collapseOne">
        ${nome}
      </button>
    </h2>
    <div id="flush-collapseOne-${nome}" class="accordion-collapse collapse" ">
      <div class="accordion-body">
      <h5>Telefone: ${telefone}</h5>
      <h6>Endereço</h6>
      <p>Rua: ${rua}</p>
      <p>Bairro: ${bairro}</p>
      <p>Numero: ${numero}</p>
      <p>Cep: ${cep}</p>
      <p>Cidade: ${cidade}</p>
      </div>
    </div>
  </div>
  
  </div>
</div>
            `
        }
    }


fetchClientes('http://localhost:8080/cliente/listar_clientes')
})
