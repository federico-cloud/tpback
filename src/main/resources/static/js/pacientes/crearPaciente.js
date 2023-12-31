// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
    location.replace('/');
}

window.addEventListener('load', () => {
    
    const formulario = document.querySelector('#add_new_paciente');
    
    const showAlert = (status) => {
        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El paciente fue generado extiosamente.',
                showConfirmButton: false,
                timer: 2000
            })
        ):(
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Hubo un error al eliminar a el paciente.',
                showConfirmButton: false,
                timer: 4000
            })
        );
    }
    
    const crearPaciente = async() => {
     
        const formData = {
            nombre: formulario.querySelector('#nombre').value,
            apellido: formulario.querySelector('#apellido').value,
            dni: formulario.querySelector('#dni').value,
            domicilio:{
                calle: formulario.querySelector('#calle').value,
                numero: formulario.querySelector('#numero').value,
                localidad: formulario.querySelector('#localidad').value,
                provincia: formulario.querySelector('#provincia').value,
            }
        };
        
        const url = '/pacientes/add';
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

    formulario.addEventListener('submit', (event) => {

        event.preventDefault();
        crearPaciente();
        resetUploadForm();
        formulario.reset();

    });

});
