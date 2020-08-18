const express = require("express");
const bodyParser = require("body-parser");
const { default: Axios } = require("axios");

const app = express();

app.use(bodyParser.json());

app.post("/events", (req, res) => {
  const event = req.body;

  Axios.post("http://localhost:4000/events", event); // posts
  Axios.post("http://localhost:4001/events", event); // comments
  Axios.post("http://localhost:4002/events", event); // query
  Axios.post("http://localhost:4003/events", event); // moderation

  res.send({ status: "OK" });
});

app.listen(4005, () => {
  console.log("Listening on port 4005");
});
