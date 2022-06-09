<?php
	include 'conexion.php';
	$nombre 	= $_POST['nombre'];
	$apellido 	= $_POST['apellido'];
	$usuario 	= $_POST['mail'];
	$pass 		= $_POST['pass'];

	$query = "INSERT INTO usuario (nombre, apellido, mail, password) 
				VALUES ('".$nombre."','".$apellido."','".$usuario."','".$pass."')";

	mysqli_query($conexion,$query) or die ("Error al insertar: ".mysqli_error($conexion));
	mysqli_close($conexion);

?>