if (Meteor.is_client) {
  Template.sms.msg = function () {
    return "Welcome to cyrano sms.";
  };

  Template.advice.msg = function () {
    return "Welcome to cyrano advice.";
  };

  Template.sms.events = {
    'click input' : function () {
      // template data, if any, is available in 'this'
      if (typeof console !== 'undefined')
        console.log("You pressed the button");
    }
  };
}

if (Meteor.is_server) {
  Meteor.startup(function () {
    // code to run on server at startup
  });
}