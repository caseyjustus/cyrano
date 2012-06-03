var sendComments = function(comment){
  $.ajax({
		url: 'test.php',
		data: {  },
		success: function(data) {
			console.log('sendComments success');
		}
	});
},

getTexts = function(){
	$.ajax({
		url: 'texts.php',
		success: function(data) {
			$('.result').html(data);
			alert('Load was performed.');
		}
	});
},

buildComment = function(comment){
var output = '<li><span>' + comment + '</span><div class="datetime">June 2, 2012 @ 11:15PM CDT</div><ul class="controls"><li class="like"></li><li class="dislike"></li></ul></li>';
	$('ul.valiantAttempts').append( output );
},

setComment = function(){
	var comment = $('textarea').text();
	buildComment(comment);
	sendComments(comment)
	$('textarea').text("");
},

favorites = function(){
	$("li.like").click(function(index) {
  		$(this).addClass('liked');
	});	

	$("li.dislike").click(function(index) {
  		$(this).addClass('disliked');
	});	
}


favorites();

$('button').click(function(index) {
	setComment();
})


