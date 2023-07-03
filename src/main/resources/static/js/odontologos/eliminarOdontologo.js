import { showAlert } from '../util/showAlert.js';
import { validarJwt } from '../util/validarJwt.js';

validarJwt();

window.addEventListener('load', function () {

    const formulario = document.querySelector('#delete_odontologo');
    const id = formulario.querySelector("#id");
    
    const resetUploadForm = () => {
        id.value = "";
    }

    const eliminarOdontologo = async(id) => {

        const url = `/odontologos/delete/${id}`;
        const settings = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        };

        const resp = await fetch(url, settings);
        showAlert(resp.status);

        return resp;
    }

    formulario.addEventListener('submit', function (event) {

        const id = formulario.querySelector('#id').value;
        event.preventDefault();
        eliminarOdontologo(id);
        resetUploadForm();

    });
})
