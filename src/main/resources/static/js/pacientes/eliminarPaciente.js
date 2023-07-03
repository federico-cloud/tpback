// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
    location.replace('/');
}

window.addEventListener('load', () => {

    const formulario = document.querySelector('#eliminarP');
    const id = formulario.querySelector("#id");

    const showAlert = (status) => {
        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El paciente fue eliminado extiosamente.',
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
        formulario.reset();
    });
})
