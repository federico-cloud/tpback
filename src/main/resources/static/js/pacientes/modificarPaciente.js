import { showAlert } from '../util/showAlert.js';
import { validarJwt } from '../util/validarJwt.js';

validarJwt();

window.addEventListener('load', () => {

  const formulario  = document.querySelector('#modify_pacient');

  const modificarPaciente = async (id) => {

    const nombre    = formulario.querySelector('#nombre').value;
    const apellido  = formulario.querySelector('#apellido').value;
    const dni       = formulario.querySelector('#dni').value;
    const calle     = formulario.querySelector('#calle').value;
    const numero    = formulario.querySelector('#numero').value;
    const localidad = formulario.querySelector('#localidad').value;
    const provincia = formulario.querySelector('#provincia').value;

    // Se crea el objeto paciente
    const formData = {
      'nombre': nombre,
      'apellido': apellido,
      'dni': dni,
      domicilio: {
        'calle': calle,
        'numero': numero,
        'localidad': localidad,
        'provincia': provincia
      }
    };

    const url = `/pacientes/modify/${id}`;
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

    return resp;
  }

  formulario.addEventListener('submit', async(event) => {

    const id = formulario.querySelector('#id').value;
    event.preventDefault();
    modificarPaciente(id);

  });

});


