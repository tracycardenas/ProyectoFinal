function checkPasswords() {
    var calificacionesInput = document.getElementById('tablaEstudiantes');
	
	document.getElementById('botonEditar').addEventListener('click', function(e) {
	  console.log('Vamos a habilitar el input text');
	  calificacionesInput.disabled = false;
	});
}


