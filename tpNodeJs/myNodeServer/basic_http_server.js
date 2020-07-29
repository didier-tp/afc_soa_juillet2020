var http = require('http');
//import * as http from 'http'; //es2015 , typescript
var myHttpFunction = function(req , res ) {
res.writeHead(200 , {"Content-Type": "text/html"}); //OK=200
res.write("<html> <body> <i><b> hello world </b></i> </body></html>")
res.end();
};
var server = http.createServer(myHttpFunction);
console.log("http://localhost:8282")
server.listen(8282);