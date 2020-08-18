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
    const { id, postId, content } = data;
    const post = posts[postId];
    const newComment = { id, content };

    post.comments.push(newComment);
  }

  res.send({});
});

app.listen(4002, () => {
  console.log("Listening on port 4002");
});
