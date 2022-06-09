<?php
	$hostname='localhost';
	$database='mihce';
	$username='root';
	$password='';

	$conexion=new mysqli($hostname,$username,$password,$database);
	if($conexion->connect_errno){
	    echo "Problemas al conectarse";
	}
?>