// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
  location.replace('/');
}

window.addEventListener('load', function () {

    const botonListar = document.querySelector('#listar');
    const turnosTable = document.querySelector('table#turnos-table tbody');

    const obtenerTurnos = async() => {

        const resp = await fetch('/turnos/getAll');
        const data = await resp.json();

        return data;

    }

    botonListar.addEventListener('click', async(event) => {

        const data = await obtenerTurnos();
        // Vaciar la tabla antes de llenarla nuevamente
        turnosTable.innerHTML = '';

        // Iterar sobre los datos recibidos y agregar las filas a la tabla
        data.forEach(turno => {
            const row = document.createElement('tr');
            row.innerHTML = `
                    <td>${turno.id}</td>
                    <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                    <td>${turno.fechaYHora}</td>
                `
            turnosTable.appendChild(row);
        });
    })
});
