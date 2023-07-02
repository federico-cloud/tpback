// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
  location.replace('/');
}

window.addEventListener('load', function () {

    const botonListar = document.querySelector('#listar');
    const pacientesTable = document.querySelector('table#pacientes-table tbody');

    const obtenerPacientes = async() => {

        const resp = await fetch('/pacientes/getAll');
        const data = await resp.json();

        return data;

    }

    botonListar.addEventListener('click', async(event) => {

        const data = await obtenerPacientes();
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
