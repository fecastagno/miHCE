<?php
	include 'conexion.php';

	$idConsulta 	= $_POST["idConsulta"];
	$especialista 	= $_POST["especialista"];
	$motivoConsulta = $_POST["motivoConsulta"];
	$tratamiento 	= $_POST["tratamiento"];
	$observaciones 	= $_POST["observaciones"];

	$sentencia=$conexion->prepare("UPDATE consulta 
									SET especialista = ?,
									motivoConsulta = ?,
									tratamiento = ?,
									observaciones = ?
									WHERE id = ?");
	
	$sentencia->bind_param('sssss', $especialista, $motivoConsulta, $tratamiento, $observaciones, $idConsulta);
	$sentencia->execute();

	$sentencia->close();
	$conexion->close();

?>