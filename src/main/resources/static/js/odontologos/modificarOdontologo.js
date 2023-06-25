window.addEventListener('load', function () {

    const formulario = document.querySelector('#modify_odontologo');

    const showAlert = (status) => {

        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El odontologo fue modificado exitosamente.',
                showConfirmButton: false,
                timer: 2000
            })
        ):(
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Hubo un error al modificar el odontologo.',
                showConfirmButton: false,
                timer: 4000
            })
        );
    }

    const modificarOdontologo = async(id) => {

        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const matricula = document.getElementById('matricula').value;

        // Crea el objeto odontologoDTO
        const formData = {
            nombre: nombre,
            apellido: apellido,
            matricula: matricula
        };

        const url = `/odontologos/modify/${id}`;
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Realiza la petición PUT a la API
        const resp = await fetch(url, settings);
        console.log(resp);
        const data = await resp.json();
        console.log(data)

        // return data;
    }

    document.getElementById('modify_odontologo').addEventListener('submit', function(event) {
        
        event.preventDefault();
        const id = formulario.querySelector('#id').value;
        modificarOdontologo(id);

    });

});
