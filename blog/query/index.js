const bodyParser = require("body-parser");
const cors = require("cors");
const express = require("express");
const { default: Axios } = require("axios");

const app = express();
const posts = {};
const handleEvent = (type, data) => {
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
};

app.use(bodyParser.json());
app.use(cors());

app.get("/posts", (_, res) => {
  res.send(posts);
});

app.post("/events", (req, res) => {
  const { type, data } = req.body;

  handleEvent(type, data);

  res.send({});
});

app.listen(4002, async () => {
  console.log("Listening on port 4002");

  // catch up with the missed events
  const res = await Axios.get("http://localhost:4005/events");

  for (let event of res.data) {
    console.log("Processing event: ", event.type);

    handleEvent(event.type, event.data);
  }
});
