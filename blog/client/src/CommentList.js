import React from "react";

export default ({ comments }) => {
  const renderedComments = comments.map((comment) => {
    let commentContent = comment.content;

    switch (comment.status) {
      case "rejected":
        commentContent = "This comment has been rejected";
        break;
      case "pending":
        commentContent = "This comment is awaiting moderation";
        break;
      default:
        break;
    }
    return <li key={comment.commentId}>{commentContent}</li>;
  });

  return <ul>{renderedComments}</ul>;
};
