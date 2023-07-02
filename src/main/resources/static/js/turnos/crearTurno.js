import { showAlert } from '../util/showAlert.js';
import { validarJwt } from '../util/validarJwt.js';

validarJwt();

window.addEventListener('load', () => {
    
    const formulario = document.querySelector('#add_new_turno');
    const buttonSubmit = document.querySelector("form.loginRegistry button.botonSubmit");

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
  
  