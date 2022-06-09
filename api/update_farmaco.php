<?php
	include 'conexion.php';

	$idFarmaco 		= $_POST["idFarmaco"];
	$medicacion 	= $_POST["medicacion"];
	$dosis 			= $_POST["dosis"];
	$fechaInicio 	= $_POST["fechaInicio"];
	$fechaFin 		= $_POST["fechaFin"];

	$sentencia=$conexion->prepare("UPDATE farmacos 
									SET medicacion = ?,
									dosis = ?,
									fechaInicio = ?,
									fechaFin = ?
									WHERE id = ?");
	$sentencia->bind_param('sssss', $medicacion, $dosis, $fechaInicio, $fechaFin, $idFarmaco);
	$sentencia->execute();

	$sentencia->close();
	$conexion->close();

?>