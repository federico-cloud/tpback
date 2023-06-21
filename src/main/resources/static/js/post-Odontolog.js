window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value
        };

        const url = '/odontologos/add';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Si la petición fue exitosa, mostramos un mensaje de éxito
                    showResponseMessage('success', 'Usuario agregado');
                    resetUploadForm();
                } else {
                    // Si hubo un error, mostramos un mensaje de error
                    showResponseMessage('danger', 'Error, intente nuevamente');
                }
            })
            .catch(error => {
                // Si hubo un error en la petición, mostramos un mensaje de error
                showResponseMessage('danger', 'Error, intente nuevamente');
            });
    });

    function showResponseMessage(type, message) {
        const responseDiv = document.querySelector('#response');
        responseDiv.innerHTML = `<div class="alert alert-${type} alert-dismissible">
                                     <button type="button" class="close" data-dismiss="alert">&times;</button>
                                     <strong>${message}</strong>
                                 </div>`;
        responseDiv.style.display = "block";
    }

    function resetUploadForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }

    // Resto del código...

});
