const bodyParser = require("body-parser");
const express = require("express");
const { default: Axios } = require("axios");

const app = express();
const FLAGGED_WORD = "orange";

// helpers
async function handleEvent(type, data) {
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
}

app.use(bodyParser.json());

app.post("/events", async (req, res) => {
  const { type, data } = req.body;

  await handleEvent(type, data);

  res.send({});
});

app.listen(4003, async () => {
  console.log("Listening on port 40003");

  // catch up with the missed events
  const res = await Axios.get("http://localhost:4005/events");

  for (let event of res.data) {
    console.log("Processing event: ", event.type);

    await handleEvent(event.type, event.data);
  }
});
