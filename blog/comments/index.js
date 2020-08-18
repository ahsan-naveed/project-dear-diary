const bodyParser = require("body-parser");
const cors = require("cors");
const express = require("express");
const { randomBytes } = require("crypto");
const { default: Axios } = require("axios");

const app = express();
const commentsByPostId = {};

app.use(bodyParser.json());
app.use(cors());

app.post("/posts/:id/comments", async (req, res) => {
  const commentId = randomBytes(4).toString("hex");
  const { content } = req.body;
  const comments = commentsByPostId[req.params.id] || [];

  comments.push({ commentId, content });
  commentsByPostId[req.params.id] = comments;

  await Axios.post("http://localhost:4005/events", {
    type: "CommentCreated",
    data: {
      postId: req.params.id,
      id: commentId,
      content,
    },
  });

  res.status(201).send(comments);
});

app.post("/events", (req, res) => {
  console.log("Received Event: ", req.body.type);

  res.send({});
});

app.get("/posts/:id/comments", (req, res) => {
  res.send(commentsByPostId[req.params.id] || []);
});

app.listen(4001, () => {
  console.log("Listening on port 4001");
});
