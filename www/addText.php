<?php
	require_once('master.php');
	
	$sql = "INSERT INTO texts (name, messages) VALUES (:name, :messages)";
	$q = $dbh->prepare($sql);
	$q->execute(array(
		':name' => $_GET['name'],
        ':messages' =>  $_GET['messages']
	));
?>