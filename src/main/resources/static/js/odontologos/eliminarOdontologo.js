window.addEventListener('load', function () {

    const formulario = document.querySelector('#delete_odontologo');
    const id = formulario.querySelector("#id");
    
    const resetUploadForm = () => {
        id.value = "";
    }

    const showAlert = (status) => {

        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El odontologo fue eliminado exitosamente.',
                showConfirmButton: false,
                timer: 2000
            })
        ):(
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Posiblemente el odontologo que esta intentando eliminar posee un turno vigente.',
                showConfirmButton: false,
                timer: 4000
            })
        );
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
