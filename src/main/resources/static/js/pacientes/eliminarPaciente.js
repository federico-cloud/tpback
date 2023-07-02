import { showAlert } from '../util/showAlert.js';
import { validarJwt } from '../util/validarJwt.js';

validarJwt();

window.addEventListener('load', () => {

    const formulario = document.querySelector('#eliminarP');
    const id = formulario.querySelector("#id");
    
    const resetUploadForm = () => {
        id.value = "";
    }

    const eliminarPaciente = async(id) => {

        const url = `/pacientes/delete/${id}`;
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

    formulario.addEventListener('submit', (event) => {

        const id = formulario.querySelector('#id').value;
        event.preventDefault();
        eliminarPaciente(id);
        resetUploadForm();

    });
})
