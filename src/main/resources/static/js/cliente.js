document.addEventListener('DOMContentLoaded', function(){
    let clientes = document.querySelector('.listaCliente');
    async function fetchClientes(url) {
        let data = await fetch(url);
        let response = await data.json();
        console.log(response)
        for(i = 0; i <= response.length;i++){
            let nome = response[i].nomeCliente;

        }
    }


fetchClientes('http://localhost:8080/cliente/listar_clientes')
})