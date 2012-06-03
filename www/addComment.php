<?php
	require_once('master.php');
	
	$sql = "INSERT INTO comments (texts_id, text, vote) VALUES (:texts_id, :text, 0)";
	$q = $dbh->prepare($sql);
	$q->execute(array(
		':texts_id' => $_GET['texts_id'],
        ':text' =>  $_GET['body']
	));
?>