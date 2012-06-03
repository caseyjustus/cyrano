var onDeviceReady = function() {
    window.plugins.sms.getAll(getInboxCallback, function(exception){
    		console.log(arguments[0] + ' error happened')
    });
};

function init(){
	document.addEventListener("deviceready", onDeviceReady, true);
}

var allMessages;
function getInboxCallback(messages){
	var messages = _.sortBy(messages, function(message, index){
		return message.sort_index;
	}).reverse();
	
	allMessages = messages;
	
	html = [];
	for(var i = 0; i < messages.length; i++){
		var message = messages[i];
		html.push('<li data-message-index="', i, '" class="message person ', ((message.person) ? 'personB' : 'personA'), '">', 
			'<h3>', ((message.person) ? 'From: ' + message.address : 'To: ' + message.address), '</h3>',
			'<div>', message.body, '</div>',
		'</li>');
	}
	$('#upload-page .texts').html($(html.join('')));
}

$('#upload-page .texts li').live('click', function(event){
	$(event.currentTarget).addClass('selected');
});

$('.upload-link').live('click', function(event){
	event.preventDefault();
	$('#upload-page').show();
	$('#index-page').hide();
})

$('.upload').live('click', function(event){
	var selectedMessages = [];
	$('.message.selected').each(function(){
		selectedMessages.push(allMessages[$(this).data('message-index')]);
	})
	
	$('.message.selected').removeClass('selected');
	
	$('#upload-page').hide();
	$('#index-page').show();
})