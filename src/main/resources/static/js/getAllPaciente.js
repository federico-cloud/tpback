window.addEventListener('load', function () {

    const botonListar = document.querySelector('#listar');
    const pacientesTable = document.querySelector('table#pacientes-table tbody');

    const obtenerOdontologos = async() => {

        const resp = await fetch('/pacientes/getAll');
        const data = await resp.json();

        console.log(data);
        return data;

    }

    botonListar.addEventListener('click', async(event) => {

        const data = await obtenerOdontologos();
        // Vaciar la tabla antes de llenarla nuevamente
        pacientesTable.innerHTML = '';

        // Iterar sobre los datos recibidos y agregar las filas a la tabla
        data.forEach(paciente => {
            const row = document.createElement('tr');
            row.innerHTML = `
                    <td>${paciente.id}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.apellido}</td>
                    <td>${paciente.dni}</td>
                    <td>${paciente.domicilio.calle}, ${paciente.domicilio.numero}, ${paciente.domicilio.localidad}, ${paciente.domicilio.provincia}</td>
                `
            pacientesTable.appendChild(row);
        });
    })

});
