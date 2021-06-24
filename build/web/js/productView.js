(function () {
    if (sessionStorage.getItem("userInSession")) {
        const userNombre = document.querySelector("#usuario-name");
        let userInSession = JSON.parse(sessionStorage.getItem("userInSession"))
        userNombre.innerHTML = 'Bienvenido ' + userInSession.username

        const btnIniciarS = document.querySelector("#boton-sesion")
        const listSesion = document.querySelector("#iniSes-list")
        const listReg = document.querySelector("#reg-list")
        btnIniciarS.style.display = "none"
        listSesion.style.display = "none"
        listReg.style.display = "none"

        document.querySelector("#list-salir").addEventListener('click', () => {
            sessionStorage.removeItem("userInSession");
            document.location.href = "index.html";
        })
        getCategoria();
        cargarAnuncio();
        return;
    }


    const listSalir = document.querySelector("#list-salir")
    listSalir.style.display = "none"

    getCategoria();
    cargarAnuncio();




})();

function cargarAnuncio() {

    const urlParams = new URLSearchParams(window.location.search);
    const anuncioId = urlParams.get('id');


    fetch(`api/anuncio/${anuncioId}`)
        .then(response => {
            return response.json();
        })
        .then(data => {
            console.log(data)
            const titulo = document.querySelector("#tituloP");
            const descripcion = document.querySelector("#descripcionP");
            const precio = document.querySelector("#precioP");
            const celular = document.querySelector("#telefonoP");
            const imageElement = document.querySelector("#imagenP");

            titulo.innerHTML = data.titulo;
            descripcion.innerHTML = data.descripcion;
            precio.innerHTML = '<strong>Precio: </strong>Bs. ' + data.precio;
            celular.innerHTML = '<strong>Contacto: </strong>' + data.telefono_contacto;
            const imageSrc = data.img_file_id == 0 ? "images/producto_default.png" : "api/anuncio/image/" + data.img_file_id;
            imageElement.src = imageSrc;
        })



}


function getCategoria() {
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

    document.querySelector("#btnAutos").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + autos;
    })
    document.querySelector("#btnElectrodomesticos").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + electrodomesticos;
    })
    document.querySelector("#btnMusica").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + musica;
    })
    document.querySelector("#btnDeportes").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + deportes;
    })
    document.querySelector("#btnElectronicos").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + electronicos;
    })
    document.querySelector("#btnHogar").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + hogar;
    })
    document.querySelector("#btnInmuebles").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + inmuebles;
    })
    document.querySelector("#btnNinos").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + ninos;
    })
    document.querySelector("#btnVestimenta").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + vestimenta;
    })
    document.querySelector("#btnServicios").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + servicios;
    })
    document.querySelector("#btnVarios").addEventListener('click', () => {
        document.location.href = 'categorias.html?cat=' + varios;
    })
}