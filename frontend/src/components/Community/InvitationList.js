import React from "react";
import InvitationItem from "./InvitationItem";

import { Box } from "@mui/material";

function InvitationList({ inviteList, setInviteList }) {
  return (
    <Box sx={{ display: "flex", flexDirection: "column", gap: "15px", margin: "50px 0" }}>
      {inviteList.map((item, index) => {
        return (
          <InvitationItem user={item.inviteUser} room={item.inviteRoom} key={index} setInviteList={setInviteList}/>
        );
      })}
    </Box>
  );
}

export default InvitationList;
