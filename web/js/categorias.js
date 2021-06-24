(function () {
    if (sessionStorage.getItem("userInSession")) {
        const userNombre = document.querySelector("#usuario-name");
        let userInSession = JSON.parse(sessionStorage.getItem("userInSession"))
        userNombre.innerHTML = 'Bienvenido '+ userInSession.username
        
        const btnIniciarS = document.querySelector("#boton-sesion")
        const listSesion = document.querySelector("#iniSes-list")
        const listReg = document.querySelector("#reg-list")
        btnIniciarS.style.display = "none"
        listSesion.style.display = "none"
        listReg.style.display = "none"

        document.querySelector("#list-salir").addEventListener('click', ()=>{
            sessionStorage.removeItem("userInSession");
            document.location.href = "index.html";        
        })
        getCategoria();
        cargarContactos();
        return;
    }


    const listSalir = document.querySelector("#list-salir")
    listSalir.style.display = "none"

    getCategoria();
    cargarContactos();

    
    

})();

function cargarContactos(){
    const urlParams = new URLSearchParams(window.location.search);
    const categoriaTipo = urlParams.get('cat');
    fetch("api/anuncio/categoria/" + categoriaTipo)
        .then((response) => {
            return response.json();
        })
        .then(function(data){
            console.log("imprimiendo contactos")
            console.log(data)
            mostrarContactos(data);
        })
        .catch((error) => {
            console.log(error);
            alert("Ocurrio un error al obtener los usuarios");
        });


}

function mostrarContactos(contactos){
    const contactosHTML = document.querySelector("#contenido-productos");
    contactosHTML.innerHTML = "";
    
    if(contactos.length == 0){
        contactosHTML.innerHTML = '<div class="msg-vacio">No tiene productos registrados. Presione el boton "Nuevo producto" para registrar uno nuevo.</div>'
        return;
    }
    

    let html = ""
    for(const i in contactos){
        const obj = contactos[i];

        let contactoHTML = getContactoInHTML(obj);

        html += contactoHTML;
    }
    contactosHTML.innerHTML = html;
}

function getContactoInHTML(obj){
    const image = obj.img_file_id == 0 ? "images/producto_default.png" : "api/anuncio/image/" + obj.img_file_id;
    return `<div class="content-product">
                <a href="viewProduct.html?id=${ obj.anuncio_id }">
                    <div class="producto">
                        <div class="producto-img" style = "background-image: url('${ image }')"></div>
                        <div class="producto-desc">
                            <h5 class="producto-title">${ obj.titulo }</h5>
                            <div class="producto-info">
                                <div><strong>Teléfono:</strong> ${ obj.telefono_contacto }</div>
                                <div><strong>Precio:</strong> ${ obj.precio } Bs.-</div>
                                <div><strong>Categoría:</strong> ${ obj.categoria }</div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>`
}

function getCategoria(){
    const autos = document.querySelector("#btnAutos").value;
    const electrodomesticos = document.querySelector("#btnElectrodomesticos").value;
    const musica = document.querySelector("#btnMusica").value;
    const deportes = document.querySelector("#btnDeportes").value;
    const electronicos = document.querySelector("#btnElectronicos").value;
    const hogar = document.querySelector("#btnHogar").value;
    const inmuebles = document.querySelector("#btnInmuebles").value;
    const ninos = document.querySelector("#btnNinos").value;
    const vestimenta = document.querySelector("#btnVestimenta").value;
    const servicios = document.querySelector("#btnServicios").value;
    const varios = document.querySelector("#btnVarios").value;

    document.querySelector("#btnAutos").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + autos;
    })
    document.querySelector("#btnElectrodomesticos").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + electrodomesticos;
    })
    document.querySelector("#btnMusica").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + musica;
    })
    document.querySelector("#btnDeportes").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + deportes;
    })
    document.querySelector("#btnElectronicos").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + electronicos;
    })
    document.querySelector("#btnHogar").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + hogar;
    })
    document.querySelector("#btnInmuebles").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + inmuebles;
    })
    document.querySelector("#btnNinos").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + ninos;
    })
    document.querySelector("#btnVestimenta").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + vestimenta;
    })
    document.querySelector("#btnServicios").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + servicios;
    })
    document.querySelector("#btnVarios").addEventListener('click', ()=>{
        document.location.href = 'categorias.html?cat=' + varios;
    })
}