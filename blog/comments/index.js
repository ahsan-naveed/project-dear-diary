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
  const postId = req.params.id;
  const comments = commentsByPostId[postId] || [];
  const comment = { commentId, content, status: "pending" };

  comments.push(comment);
  commentsByPostId[postId] = comments;

  // stream event to event bus
  await Axios.post("http://localhost:4005/events", {
    type: "CommentCreated",
    data: {
      postId,
      ...comment,
    },
  });

  res.status(201).send(comments);
});

app.post("/events", async (req, res) => {
  console.log("Received Event: ", req.body.type);

  const { type, data } = req.body;

  if (type === "CommentModerated") {
    const { postId, status, commentId, content } = data;
    const comments = commentsByPostId[postId];
    const comment = comments.find((c) => {
      return c.commentId === commentId;
    });

    comment.status = status;

    // stream event to event bus
    await Axios.post("http://localhost:4005/events", {
      type: "CommentUpdated",
      data: {
        postId,
        commentId,
        content,
        status,
      },
    });
  }

  res.send({});
});

app.get("/posts/:id/comments", (req, res) => {
  res.send(commentsByPostId[req.params.id] || []);
});

app.listen(4001, () => {
  console.log("Listening on port 4001");
});
