document.addEventListener('DOMContentLoaded', function () {
    let orcamento = document.querySelector('.listaOrcamento');
    async function fetchProducts(url) {
        try {
            let data = await fetch(url);
            let response = await data.json();
            console.log(response)
            
            for (i = 0; i<= response.length; i++){
                let id = response[i].id
                let cliente = response[i].cliente.nomeCliente
                let valorTotal = response[i].valorDescontado
                let data = new Date(response[i].dataOrcamento)
               console.log(id)
               console.log (cliente)
               console.log (valorTotal)
               console.log (data)

               orcamento.innerHTML +=`
               <div class="card" style="width: 18rem;">
  <img src="https://cdn-icons-png.flaticon.com/512/126/126391.png" class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">${cliente}</h5>
    <p class="card-text">ID ${id}</p>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Valor R$ ${valorTotal.toFixed(2)}</li>
    <li class="list-group-item">Data ${data.toLocaleString('pt-BR',{timezone : 'UTC'})}</li>
    
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