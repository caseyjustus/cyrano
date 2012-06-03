<?php
	$stmt = $dbh->prepare('SELECT * FROM texts WHERE id = :id');
	$stmt->execute(array(
		':id' => $_GET['id']
	));
	
	$text = $stmt->fetch(PDO::FETCH_OBJ);
?>

<div class="conversation panel" data-text-id="<?php print $text->id; ?>">

	<div class="logo"></div>

	<h2><?php print $text->name; ?></h2>
	<h3>Submitted June 2, 2012 @ 6:47PM CDT</h3>
	
	<?php
		$messages = json_decode($text->messages);
		if($messages){
			foreach($messages as $message){
	?>
		<div class="<?php print ($message->is_me) ? 'personA' : 'personB' ?> person">
			
			<?php print $message->body; ?>

			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>

		</div>
	<?php
			}
		}
	?>

	<textarea></textarea>
	<button>Comment</button>
	<button>Tweet this!</button>

</div>

<div class="comments panel" data-text-id="<?php print $text->id; ?>">

	<div class="hot group">
		<h4>The Hotness</h4>
		<span>Tell that bitch to be cool! Be cool, bitch!</span>
		<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
		<ul class="controls">
			<li class="like liked"></li>
			<li class="dislike"></li>
		</ul>
	</div>

	<ul class="runnerUp">
		<li>
			<h4>2nd Place</h4>
			<span>So you made me a bloomin' onion?</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike"></li>
			</ul>
		</li>
		<li>
			<h4>3rd Place</h4>
			<span>Do you need me to turn around?</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike disliked"></li>
			</ul>
		</li>
		<li>
			<h4>Honorable Mention</h4>
			<span>Wipe away those alligator tears.</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike"></li>
			</ul>
		</li>
	</ul>

	<h4>Valiant Attempts</h4>
	<ul class="valiantAttempts">
		<li>
			<span>Girl, I'ma go win you a Quadracopter.</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like liked"></li>
				<li class="dislike"></li>
			</ul>
		</li>
		<li>
			<span>This ain't nothing a Jawbone with a chain can't fix.</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike"></li>
			</ul>
		</li>
		<li>
			<span>Pack your things, we're going to Disney World. I mean, you're going to Disney World. I mean, I'm leaving you.</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike"></li>
			</ul>
		</li>
		<li>
			<span>I told you those onions were for dinner tomorrow night!</span>
			<div class="datetime">June 1, 2012 @ 10:00PM CDT</div>
			<ul class="controls">
				<li class="like"></li>
				<li class="dislike"></li>
			</ul>
		</li>
	</ul>

</div>
