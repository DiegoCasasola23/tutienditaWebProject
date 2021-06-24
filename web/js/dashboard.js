(function () {
    if (!sessionStorage.getItem("userInSession")) {
        alert("Primero debes Iniciar Sesión o Registrarse")
        document.location.href = "login.html";
        return;
    }

    document.body.style.display = "block"

    document.querySelector("#btn-fin").addEventListener('click', () => {
        sessionStorage.removeItem("userInSession");
        document.location.href = "index.html";
    })

    cargarContactos();

})();

function cargarContactos() {
    let userInSession = JSON.parse(sessionStorage.getItem("userInSession"))
    fetch("api/anuncio/usuario/" + userInSession.usuarioId)
        .then((response) => {
            return response.json();
        })
        .then(function (data) {
            console.log("imprimiendo contactos")
            console.log(data)
            mostrarContactos(data);
        })
        .catch((error) => {
            console.log(error);
            alert("Ocurrio un error al obtener los usuarios");
        });


}

function mostrarContactos(contactos) {
    const contactosHTML = document.querySelector("#contenido-productos");
    contactosHTML.innerHTML = "";

    if (contactos.length == 0) {
        contactosHTML.innerHTML = '<div class="msg-vacio">No tiene productos registrados. Presione el boton "Nuevo producto" para registrar uno nuevo.</div>'
        return;
    }


    let html = ""
    for (const i in contactos) {
        const obj = contactos[i];

        let contactoHTML = getContactoInHTML(obj);

        html += contactoHTML;
    }
    contactosHTML.innerHTML = html;
}

function getContactoInHTML(obj) {
    const image = obj.img_file_id == 0 ? "images/producto_default.png" : "api/anuncio/image/" + obj.img_file_id;

    return `<div class="content-product">
                <div class="producto">
                <div class="producto-img" style = "background-image: url('${image}')"></div>
                <div class="producto-desc">
                    <h5 class="producto-title">${obj.titulo}</h5>
                    <div class="producto-info">
                        <div><strong>Teléfono:</strong> ${obj.telefono_contacto}</div>
                        <div><strong>Precio:</strong> ${obj.precio} Bs.-</div>
                        <div><strong>Estado:</strong> ${obj.estado}</div>
                    </div>
                    <div class="btn-down">
                        <a href="product-frm.html?id=${obj.anuncio_id}" class="btn">Editar</a>
                        <button type="button" class="btn" onclick="eliminarContacto(${obj.anuncio_id})">Eliminar</button>
                </div>
                </div>
                </div>
            </div>`
}

function eliminarContacto(contactoId) {
    if (!confirm("¿Esta seguro que desea eliminar el contacto seleccionado?")) {
        return;
    }

    console.log("Eliminando contacto " + contactoId)

    fetch('api/anuncio/' + contactoId, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json', //MimeType
            'Content-Type': 'application/json'
        }
    }).then((response) => {
        return response.json();
    }).then((data) => {
        debugger;
        if (!data.isOK) {
            alert(data.message);
            return;
        }
        cargarContactos();

    });
}

function editarContacto(evt, contactoId) {
    evt.preventDefault();
    debugger;
}