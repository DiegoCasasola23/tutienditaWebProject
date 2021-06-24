(function () {

    document.querySelector("#btn-regis").addEventListener('click', (e) => {
        e.preventDefault();

        const Name = document.querySelector("#inputName").value
        const User = document.querySelector("#inputUser").value
        const Mail = document.querySelector("#inputMail").value
        const Password = document.querySelector("#inputPassword").value
        const PasswordS = document.querySelector("#inputPasswordSecond").value

        const validacionNombre = document.querySelector("#validation-name");
        const validacionUsuario = document.querySelector("#validation-username");
        const validacionMail = document.querySelector("#validation-mail");
        const validacionPassword = document.querySelector("#validation-password");
        const validacionPasswordS = document.querySelector("#validation-password-second");

        const msgError = document.querySelector("#msg-error-regis")

        validacionNombre.style.display = "none";
        validacionUsuario.style.display = "none";
        validacionMail.style.display = "none";
        validacionPassword.style.display = "none";
        validacionPasswordS.style.display = "none";

        let hasError = false;

        if (Name == "") {
            hasError = true;
            validacionNombre.style.display = "block"
        }

        if (User == "") {
            hasError = true;
            validacionUsuario.style.display = "block"
        }

        if (Mail == "") {
            hasError = true;
            validacionMail.style.display = "block"
        } else if (!isEmailValid(Mail)) {
            hasError = true;
            validacionMail.innerHTML = "El correo electrónico no es válido"
            validacionMail.style.display = "block"
        }

        if (Password == "") {
            hasError = true;
            validacionPassword.style.display = "block"
        } else if (!isPasswordValid(Password)) {
            hasError = true;
            validacionPassword.innerHTML = "La contraseña debe tener minimo ocho caracteres, al menos una letra y un número"
            validacionPassword.style.display = "block"
        }

        if (PasswordS == "") {
            hasError = true;
            validacionPasswordS.style.display = "block"
        }
        if (Password != PasswordS) {
            hasError = true;
            validacionPassword.innerHTML = "Las contraseñas no coinciden"
            validacionPasswordS.innerHTML = "Las contraseñas no coinciden"
            validacionPassword.style.display = "block"
            validacionPasswordS.style.display = "block"
        }

        if (hasError)
            return;

        const usuario = {
            nombre_completo: Name,
            username: User,
            email: Mail,
            contrasena: Password
        }
        debugger;
        fetch('api/usuario', {
            method: 'POST',
            headers: {
                'Accept': 'application/json', //MimeType
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            
            if (!data.isOK) {
                msgError.innerHTML = data.message;
                msgError.style.display = "block"
                return;
            }

            document.location.href = "index.html";

        });
    });
})();

function isEmailValid(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function isPasswordValid(pass) {
    const re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return re.test(pass);
}