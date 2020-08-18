const bodyParser = require("body-parser");
const express = require("express");
const { default: Axios } = require("axios");

const app = express();
const FLAGGED_WORD = "orange";

app.use(bodyParser.json());

app.post("/events", async (req, res) => {
  const { type, data } = req.body;

  if (type === "CommentCreated") {
    const { content, commentId, postId } = data;
    const status = content.toLowerCase().includes(FLAGGED_WORD)
      ? "rejected"
      : "approved";

    // stream event to event bus
    await Axios.post("http://localhost:4005/events", {
      type: "CommentModerated",
      data: {
        postId,
        status,
        commentId,
        content,
      },
    });
  }

  res.send({});
});

app.listen(4003, () => {
  console.log("Listening on port 40003");
});
