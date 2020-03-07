import { createServer } from 'http';
import { hostname } from 'os';

console.log("Kbia server is starting...");

var handler = function (req, resp) {
    console.log(`Received request from ${req.connection.remoteAddress}`);
    resp.writeHead(200);
    resp.end(`You've hit ${hostname()}\n`);
}

www = createServer(handler)
www.listen(8080)