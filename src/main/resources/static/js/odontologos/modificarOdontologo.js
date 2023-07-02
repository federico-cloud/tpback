import { showAlert } from '../util/showAlert.js';
import { validarJwt } from '../util/validarJwt.js';

validarJwt();

window.addEventListener('load', function () {

    const formulario = document.querySelector('#modify_odontologo');

    const modificarOdontologo = async(id) => {

        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const matricula = document.getElementById('matricula').value;

        // Crea el objeto odontologoDTO
        const formData = {
            nombre: nombre,
            apellido: apellido,
            matricula: matricula
        };

        const url = `/odontologos/modify/${id}`;
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Realiza la petición PUT a la API
        const resp = await fetch(url, settings);
        showAlert(resp.status);
        const data = await resp.json();

        return data;
    }

    document.getElementById('modify_odontologo').addEventListener('submit', function(event) {
        
        event.preventDefault();
        const id = formulario.querySelector('#id').value;
        modificarOdontologo(id);

    });

});
