import React from "react";
import UserNameTag from "components/GlobalComponents/UserNameTag";
import FriendBtnTwo from "components/GlobalComponents/FriendBtnTwo";

import { Box, Card, CardMedia, Typography, Button } from "@mui/material";


function RequestCard({ user }) {

  // message 추가해야함
  const { userId, nickname, imgUrl, message } = user;
  return (
    <>
      <Card
        variant="outlined"
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          minWidth: "240px",
          width: "240px",
          height: "260px",
          padding: "16px 30px",
          boxSizing: "border-box",
          border: "3px solid #111111",
          mr: "1.5rem",
        }}
      >
        <CardMedia
          component="img"
          sx={{
            height: "128px",
            width: "128px",
            borderRadius: "200px",
            border: "3px solid #111",
          }}
          image={imgUrl}
          alt="Live from space album cover"
        />
        <Box sx={{ display: "flex", flexDirection: "column" }}>
          <Typography component="div" sx={{ fontSize: 18, p: "16px" }}>
            <UserNameTag nickname={nickname} userId={userId} />
          </Typography>
        </Box>
          
      <FriendBtnTwo fromId={sessionStorage.getItem("userId")} toId={userId} />
      
      </Card>
    </>
  );
}

export default RequestCard;
