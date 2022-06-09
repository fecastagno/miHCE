<?php
	include 'conexion.php';

	$idUsuario 	 		= $_POST['idUsuario'];
	$dni 				= $_POST['dni'];
	$sexo 				= $_POST['sexo'];
	$fechaNacimiento 	= $_POST['fechaNacimiento'];
	$edad 				= $_POST['edad'];
	$direccion 			= $_POST['direccion'];
	$obraSocial 		= $_POST['obraSocial'];
	$peso 				= $_POST['peso'];
	$altura 			= $_POST['altura'];
	$grupoSanguineo 	= $_POST['grupoSanguineo'];
	$fuma 				= $_POST['fuma'];
	$bebeAlcohol 		= $_POST['bebeAlcohol'];
	$alergia 			= $_POST['alergia'];
	$medicacionCronica 	= $_POST['medicacionCronica'];
	$nacionalidad 		= $_POST['nacionalidad'];
	$ocupacion 			= $_POST['ocupacion'];
	$estadoCivil 		= $_POST['estadoCivil'];

	$query = "INSERT INTO misdatos (id, id_usuario, dni, sexo, fechaNacimiento, edad, 
									direccion, obraSocial, peso, altura, grupoSanguineo, 
								 	fuma, bebeAlcohol, alergia, medicacionCronica, nacionalidad, ocupacion, estadoCivil) 
				VALUES (null,'".$idUsuario."','".$dni."','".$sexo."','".$fechaNacimiento."',
						'".$edad."','".$direccion."','".$obraSocial."',
						'".$peso."','".$altura."','".$grupoSanguineo."',
						'".$fuma."','".$bebeAlcohol."','".$alergia."','".$medicacionCronica."',
						'".$nacionalidad."','".$ocupacion."','".$estadoCivil."')";

	mysqli_query($conexion,$query) or die ("<br>Error al insertar: ".mysqli_error($conexion));
	mysqli_close($conexion);
?>