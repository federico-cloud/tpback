
window.addEventListener('load', () => {
    const apiUrl = 'http://localhost:8080/authenticate';

    const formulario = document.querySelector('form.loginRegistry');
    const email      = formulario.querySelector('#email');
    const password   = formulario.querySelector('#password');

    const validacionNoVacio = (texto) => {
        let resultado = true;
    
        if(texto === ""){
            resultado = false;
        }
    
        return resultado
    }
    
    const normalizacionLogin = (email, password) => {
        const usuario = {
            email: email.trim(),
            password: password.trim()
        }
    
        return usuario;
    }

    const fetchApiLogin = async(url,payload) => {

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            body: JSON.stringify(payload)
        }

        const resp = await fetch(url, settings);
        const data = await resp.json();
        const jwt = data.jwt;

        if(jwt){
            localStorage.setItem('jwt', data.jwt);
            location.href = '/bienvenido.html';
        }
    }

    formulario.addEventListener('submit', (event) => {

        event.preventDefault();
        const validacion = validacionNoVacio(email.value) && validacionNoVacio(password.value);

        if(validacion){
            const datosUsuario = normalizacionLogin(email.value, password.value);
            fetchApiLogin(apiUrl, datosUsuario);
        }else{
            console.log("algun dato no es correcto");
        }
        formulario.reset();
    })

});