export const showAlert = (status) => {
 status === 200 ? (
  Swal.fire({
   icon: 'success',
   title: 'Tarea completada',
   text: 'El paciente fue generado extiosamente',
   showConfirmButton: false,
   timer: 2000
  })
 ):(
  Swal.fire({
   icon: 'error',
   title: 'ERROR',
   text: 'Hubo un error al crear el paciente',
   showConfirmButton: false,
   timer: 4000
  })
 );
}