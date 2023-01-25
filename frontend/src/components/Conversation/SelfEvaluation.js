import React from "react";

import ParticipantCard from "./ParticipantCard";
import Box from "@mui/material/Box";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { XSquare } from "react-bootstrap-icons";

function SelfEvaluation({}) {
  const evaluationStyle = {
    display: "flex",
    justifyContent: "space-around",
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: "80%",
    height: "80%",
    bgcolor: "background.paper",
    border: "3px solid #111",
    boxShadow: 24,
    p: 4,
  };

  const user = {
    nickname: "Almeang",
    user_id: "10004567",
    img_url:
      "https://i.pinimg.com/564x/af/7b/de/af7bde50489a2cb932a98741b877704b.jpg",
  };

  const { nickname, user_id, img_url } = user;
  return (
    <>
      <Box sx={evaluationStyle}>
        <XSquare />
        <ParticipantCard
          nickname={nickname}
          user_id={user_id}
          img_url={img_url}
        />
      </Box>
    </>
  );
}

export default SelfEvaluation;
