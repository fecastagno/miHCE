<?php
	include 'conexion.php';

	$idFarmaco = $_POST["idFarmaco"];

	$sentencia 	= $conexion->prepare("DELETE FROM farmacos WHERE id = ?");
	$sentencia->bind_param('s', $idFarmaco);
	$sentencia->execute();

	$sentencia->close();
	$conexion->close();

?>