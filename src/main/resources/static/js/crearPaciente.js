window.addEventListener('load', function () {
    
    const formulario = document.querySelector('#add_new_paciente');
    
    function resetUploadForm() {
        formulario.querySelector('#nombre').value = '';
        formulario.querySelector('#apellido').value = '';
        formulario.querySelector('#dni').value = '';
        formulario.querySelector('#calle').value = '';
        formulario.querySelector('#numero').value = '';
        formulario.querySelector('#localidad').value = '';
        formulario.querySelector('#provincia').value = '';
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

    const showAlert = (status) => {

        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'El paciente fue generado extiosamente',
                showConfirmButton: false,
                timer: 1500
              })
        ):(
            Swal.fire({
                icon: 'error',
                title: 'Hubo un error al crear el paciente',
                showConfirmButton: false,
                timer: 1500
              })
        );
    }

    formulario.addEventListener('submit', (event) => {

        event.preventDefault();
        crearPaciente();
        resetUploadForm();

    });

});
