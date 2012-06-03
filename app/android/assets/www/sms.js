var SMS = function() {

};

SMS.prototype.getInbox = function(successCallback, failureCallback) {
	return PhoneGap.exec(
		successCallback,
		failureCallback,
		'SmsPlugin',
		'getInbox',
		[]);
};

SMS.prototype.getSent = function(successCallback, failureCallback) {
	return PhoneGap.exec(
		successCallback,
		failureCallback,
		'SmsPlugin',
		'getSent',        
		[]);        		
};

SMS.prototype.getAll = function(successCallback, failureCallback){
	return PhoneGap.exec(
		successCallback,
		failureCallback,
		'SmsPlugin',
		'getAll',        
		[]);  
}
 
PhoneGap.addConstructor(function() {
   PhoneGap.addPlugin("sms", new SMS());
});