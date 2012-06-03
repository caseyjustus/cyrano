var SMS = function() {

};

SMS.prototype.getInbox = function(successCallback, failureCallback) {
	return PhoneGap.exec(
		successCallback,    //Success callback from the plugin
		failureCallback,    //Error callback from the plugin
		'SmsPlugin',  		//Tell PhoneGap to run "DirectoryListingPlugin" Plugin
		'getInbox',         //Tell plugin, which action we want to perform
		[]);        		//Passing list of args to the plugin
};
 
PhoneGap.addConstructor(function() {
   PhoneGap.addPlugin("sms", new SMS());
});