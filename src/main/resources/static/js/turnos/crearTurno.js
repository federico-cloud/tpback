window.addEventListener('load', function () {
    
    const formulario = document.querySelector('#add_new_turno');
    const buttonSubmit = document.querySelector("form.loginRegistry button.botonSubmit");
   
    
    const showAlert = (status) => {

        status === 200 ? (
            Swal.fire({
                icon: 'success',
                title: 'Tarea completada',
                text: 'El Turno fue creado exitosamente.',
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

    const crearTurno = async(paciente, odontologo) => {

        const url = '/turnos/add';

        const formData = {
            paciente: {
                'id': paciente.id,
            },
            odontologo:{
                'id': odontologo.id
            }    
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

        const idPaciente = formulario.querySelector('#idPaciente').value;
        const idOdontologo = formulario.querySelector('#idOdontologo').value;

        const paciente = await buscarPacienteId(idPaciente);
        const odontologo = await buscarOdontologoId(idOdontologo);

        crearTurno(paciente, odontologo);

    })



});
  
  