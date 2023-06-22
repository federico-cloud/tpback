window.addEventListener('load', function () {

    const botonListar = document.querySelector('#listar');
    const odontologosTable = document.querySelector('table#odontologos-table tbody');

    const obtenerOdontologos = async() => {

        const resp = await fetch('/odontologos/getAll');
        const data = await resp.json();

        return data;

    }

    botonListar.addEventListener('click', async(event) => {

        const data = await obtenerOdontologos();
        // Vaciar la tabla antes de llenarla nuevamente
        odontologosTable.innerHTML = '';

        // Iterar sobre los datos recibidos y agregar las filas a la tabla
        data.forEach(odontologo => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.matricula}</td>
            `
            odontologosTable.appendChild(row);
        });
    })

});
