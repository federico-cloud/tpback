window.addEventListener('load', function () {

    document.getElementById('modify_odontologo').addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('id').value;
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const matricula = document.getElementById('matricula').value;

        // Crea el objeto odontologoDTO
        const odontologoDTO = {
            nombre: nombre,
            apellido: apellido,
            matricula: matricula
        };

        // Realiza la petición PUT a la API
        fetch(`/modify/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(odontologoDTO),
        })
        .then(response => response.json())
        .then(data => {
            // Muestra la respuesta en el div #response
            document.getElementById('response').innerText = JSON.stringify(data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    });
