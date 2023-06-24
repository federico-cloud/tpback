window.addEventListener('load', function () {

    const formulario = document.querySelector('#eliminarP');

    formulario.addEventListener('submit', function (event) {

        const formData = {
            id: document.querySelector('#id').value,
        };

        const url = '/odontologos/delete/${id}';
        const settings = {
            method: 'DELETE',
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
        document.querySelector('#id').value = "";
    }

});
