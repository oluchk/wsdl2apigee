var queryparamsNamesCollection = context.getVariable("request.queryparams.names") + '';
queryparamsNamesCollection = queryparamsNamesCollection.substr(1, queryparamsNamesCollection.length - 2);
var queryparamsNames = queryparamsNamesCollection.split(", ");
var queryparams = {};

queryparamsNames.forEach(function(queryName) {
    var queryValue = context.proxyRequest.queryParams[queryName];
    queryparams[queryName] = queryValue;
});


var requestData = {
        
};

context.setVariable('requestData', JSON.stringify(requestData));