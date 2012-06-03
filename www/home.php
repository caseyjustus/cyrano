<?php
	$stmt = $dbh->prepare('SELECT * FROM texts');
	$stmt->execute();
	$texts = $stmt->fetchAll(PDO::FETCH_OBJ);
?>

<div class="logoMain"></div>

<ul class="sort">
	<li>Hot</li>
	<li>Popular</li>
	<li>New</li>
</ul>


<ul class="main">
	<?php
	foreach($texts as $text){
	?>
		<li data-text-id="<?php print $text->id; ?>">
			<span>
				<a href="/text.php?id=<?php print $text->id; ?>"><?php print $text->name; ?></a>
			</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike"></li>
			</ul>
		</li>
	<?php
		}
	?>

</ul>