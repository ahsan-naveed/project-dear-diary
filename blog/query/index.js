const bodyParser = require("body-parser");
const cors = require("cors");
const express = require("express");

const app = express();
const posts = {};

app.use(bodyParser.json());
app.use(cors());

app.get("/posts", (req, res) => {
  res.send(posts);
});

app.post("/events", (req, res) => {
  const { type, data } = req.body;

  if (type === "PostCreated") {
    const { id, title } = data;
    const newPost = {
      id,
      title,
      comments: [],
    };

    posts[id] = newPost;
  }

  if (type === "CommentCreated") {
    const { commentId, postId, content, status } = data;
    const post = posts[postId];
    const newComment = { commentId, content, status };

    post.comments.push(newComment);
  }

  if (type === "CommentUpdated") {
    const { commentId, postId, status, content } = data;
    const post = posts[postId];
    const comment = post.comments.find((c) => {
      return c.commentId === commentId;
    });

    comment.status = status;
    comment.content = content;
  }

  res.send({});
});

app.listen(4002, () => {
  console.log("Listening on port 4002");
});
