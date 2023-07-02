import { showAlert } from '../util/showAlert.js';
import { validarJwt } from '../util/validarJwt.js';

validarJwt();

window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_odontologo');

    const resetUploadForm = () => {
        formulario.querySelector('#nombre').value = "";
        formulario.querySelector('#apellido').value = "";
        formulario.querySelector('#matricula').value = "";
    }

    const crearOdontologo = async() => {
        const formData = {
            nombre: formulario.querySelector('#nombre').value,
            apellido: formulario.querySelector('#apellido').value,
            matricula: formulario.querySelector('#matricula').value
        };

        const url = '/odontologos/add';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        const resp = await fetch(url, settings);
        showAlert(resp.status);
        const data = await resp.json();

        return data;
    }

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();
        crearOdontologo();
        resetUploadForm();

    });

});
