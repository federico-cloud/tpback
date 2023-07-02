export const validarJwt = () => { 
 // chequear que exista un usuario loggeado
 const jwt = localStorage.getItem('jwt');
 // si no existe un token, lo sacamos de la vista
 if (!jwt) {
  location.replace('/');
 }
}