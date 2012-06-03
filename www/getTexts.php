<?php
	require_once('master.php');
	
	$stmt = $dbh->prepare('SELECT * FROM texts');
	$stmt->execute();
	$texts = $stmt->fetchAll(PDO::FETCH_OBJ);
	
	print json_encode($texts);
?>