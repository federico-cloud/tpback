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

        const token = localStorage.getItem('jwt');
        const url = '/odontologos/add';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bareer' + token
            },
            body: JSON.stringify(formData)
        }

        const resp = await fetch(url, settings);
        showAlert(resp.status);
        const data = await resp.json();

        return data;
    }

    const showAlert = (status) => {

        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El odontologo fue generado exitosamente.',
                showConfirmButton: false,
                timer: 2000
              })
        ):(
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Hubo un error al generar el nuevo odontologo.',
                showConfirmButton: false,
                timer: 4000
              })
        );
    }

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();
        crearOdontologo();
        resetUploadForm();

    });

});
