window.addEventListener('load', function () {
  const formulario = document.getElementById('modify-pacient');

  formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    const id = document.getElementById('id').value;
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const dni = document.getElementById('dni').value;
    const calle = document.getElementById('calle').value;
    const numero = document.getElementById('numero').value;
    const localidad = document.getElementById('localidad').value;
    const provincia = document.getElementById('provincia').value;

    const pacienteDTO = {
      nombre,
      apellido,
      dni,
      Domicilio: {
        calle,
        numero,
        localidad,
        provincia,
      },
    };

    const url = `/pacientes/modify/${id}`;

    const settings = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(pacienteDTO),
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
           document.getElementById('response').innerText = JSON.stringify(data);
         })
         .catch(error => {
           console.error('Error:', error);
         });

    formulario.reset();

  });
});

