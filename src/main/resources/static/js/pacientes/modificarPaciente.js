// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
    location.replace('/');
}
window.addEventListener('load', () => {

  const formulario  = document.querySelector('#modify_pacient');

  const showAlert = (status) => {
    status === 200 ? (
        Swal.fire({
            icon: 'success',
            title: 'Tarea completada',
            text: 'El paciente fue modificado extiosamente.',
            showConfirmButton: false,
            timer: 2000
        })
    ):(
        Swal.fire({
            icon: 'error',
            title: 'ERROR',
            text: 'Hubo un error al modificar a el paciente.',
            showConfirmButton: false,
            timer: 4000
        })
    );
}

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
    formulario.reset();

  });

});


