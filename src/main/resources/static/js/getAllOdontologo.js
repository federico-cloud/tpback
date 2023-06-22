window.addEventListener('load', function () {

    const botonListar = document.querySelector('#listar');

    botonListar.addEventListener('click', function (event) {

        fetch('/odontologos/getAll')
        .then(response => response.json())
        .then(data => {
            const odontologosTable = document.querySelector('#odontologos-table tbody');

            // Vaciar la tabla antes de llenarla nuevamente
            odontologosTable.innerHTML = '';

            // Iterar sobre los datos recibidos y agregar las filas a la tabla
            data.forEach(odontologo => {
            const row = document.createElement('tr');
            console.log(data);
            row.innerHTML = `<td>${odontologo.id}</td>
                            <td>${odontologo.nombre}</td>
                            <td>${odontologo.apellido}</td>
                            <td>${odontologo.matricula}</td>`;
            odontologosTable.appendChild(row);
            });
        })

        .catch(error => {
            console.error('Error en la solicitud:', error);
        })
    });
});
