var messageId = context.getVariable('messageid');
var now = new Date(); 
var timestamp = now.toISOString().slice(0, -5) + '.000';

context.setVariable("messageId", messageId);
context.setVariable("timestamp", timestamp);
