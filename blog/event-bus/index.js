const express = require("express");
const bodyParser = require("body-parser");
const { default: Axios } = require("axios");

const app = express();
const events = [];

app.use(bodyParser.json());

app.post("/events", (req, res) => {
  const event = req.body;

  events.push(event);

  Axios.post("http://localhost:4000/events", event); // posts
  Axios.post("http://localhost:4001/events", event); // comments
  Axios.post("http://localhost:4002/events", event); // query
  Axios.post("http://localhost:4003/events", event); // moderation

  res.send({ status: "OK" });
});

app.get("/events", (_, res) => {
  res.send(events);
});

app.listen(4005, () => {
  console.log("Listening on port 4005");
});
