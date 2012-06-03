<?php
	$dbh = new PDO('mysql:host=mysql.s467.sureserver.com;dbname=willpracht_cyrano', 'honeybadger', 'dontgiveafuck');
	
	$stmt = $dbh->prepare('SELECT * FROM texts');
	$texts = $stmt->execute()->fetchAll(PDO::FETCH_OBJ);
		
	print_r($texts);
?>