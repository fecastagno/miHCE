<?php
	include 'conexion.php';
	$idFarmaco = $_POST["idFarmaco"];
	
	$query = "SELECT *
				FROM farmacos 
				WHERE id = ".$idFarmaco;	

	$resultset = mysqli_query($conexion,$query) or die ("Error al traer los datos: ".mysqli_error($conexion));

   	if($resultset) {
	    while($row = mysqli_fetch_assoc($resultset)){
	        echo json_encode($row);
	    }
	}

	mysqli_close($conexion);
?>