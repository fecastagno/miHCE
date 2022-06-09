<?php
	include 'conexion.php';
	$idUsuario = $_POST["idUsuario"];
	
	$query = "SELECT *
				FROM farmacos 
				WHERE id_usuario = \"".$idUsuario."\" 
				ORDER BY 1 DESC";	

	$resultset = mysqli_query($conexion,$query) or die ("Error al traer los datos: ".mysqli_error($conexion));

   	if($resultset) {
	    while($row = mysqli_fetch_assoc($resultset)){
	        echo json_encode($row);
	    }
	}

	mysqli_close($conexion);
?>