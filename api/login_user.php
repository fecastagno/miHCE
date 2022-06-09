<?php

	include 'conexion.php';

	$mail = "mail";
	$query = "SELECT password
				FROM usuario 
				WHERE mail = \"".$mail."\"";

	$resultset = mysqli_query($con,$query) or die ("Error al traer los datos: ".mysqli_error($con));

	if ($resultset) {	
    	while ($row = $resultset->fetch_array(MYSQLI_ASSOC)) {
    		echo json_encode($row);    	
    	}    	
   	}
	mysqli_close($con);
   
?>
