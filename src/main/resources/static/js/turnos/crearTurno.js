// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
    location.replace('/');
}

window.addEventListener('load', () => {
    
    const formulario   = document.querySelector('#add_new_turno');
    const buttonSubmit = document.querySelector('form.loginRegistry button.botonSubmit');

    const showAlert = (status) => {
        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El turno fue generado extiosamente.',
                showConfirmButton: false,
                timer: 2000
            })
        ):(
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Hubo un error al crear el turno.',
                showConfirmButton: false,
                timer: 4000
            })
        );
    }

    const buscarPacienteId = async(id) => {

        const url = `/pacientes/search/${id}`
        const resp = await fetch(url);
        const data = await resp.json();

        return data;

    } 

    const buscarOdontologoId = async(id) => {
        
        const url = `/odontologos/search/${id}`
        const resp = await fetch(url);
        const data = await resp.json();

        return data;

    } 

    const crearTurno = async(paciente, odontologo, fechaYHora) => {

        const url = '/turnos/add';

        const formData = {
            paciente: {
                'id': paciente.id,
            },
            odontologo:{
                'id': odontologo.id
            },
            fechaYHora: fechaYHora   
        }

        const settings = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        }

        const resp = await fetch(url, settings);
        showAlert(resp.status);
        const data = await resp.json();

        return data;
    }

    buttonSubmit.addEventListener('click' , async(event) =>{

        event.preventDefault();

        let fechaYHora   = document.querySelector('#fechaYHora').value;
        fechaYHora = new Date(fechaYHora); 
        
        //Se formatea la fecha
        const dia = fechaYHora.getDate().toString().padStart(2, '0');
        const mes = (fechaYHora.getMonth() + 1).toString().padStart(2, '0');
        const anio = fechaYHora.getFullYear().toString();
        const horas = fechaYHora.getHours().toString().padStart(2, '0');
        const minutos = fechaYHora.getMinutes().toString().padStart(2, '0');
        
        //Se arma la fecha que va a ser guardada en la base de datos
        const fechaYHoraFormateada = `${dia}/${mes}/${anio} ${horas}:${minutos}`;

        const idPaciente = formulario.querySelector('#idPaciente').value;
        const paciente = await buscarPacienteId(idPaciente);

        const idOdontologo = formulario.querySelector('#idOdontologo').value;
        const odontologo = await buscarOdontologoId(idOdontologo);

        crearTurno(paciente, odontologo, fechaYHoraFormateada);
        formulario.reset()

    })



});
  
  