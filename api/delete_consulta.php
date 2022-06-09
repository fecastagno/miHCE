<?php
	include 'conexion.php';

	$idConsulta = $_POST["idConsulta"];

	$sentencia 	= $conexion->prepare("DELETE FROM consulta WHERE id = ?");
	$sentencia->bind_param('s', $idConsulta);
	$sentencia->execute();

	$sentencia->close();
	$conexion->close();

?>