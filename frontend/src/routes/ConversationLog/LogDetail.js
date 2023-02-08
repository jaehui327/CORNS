import React, { useState, useEffect } from "react";
import axios from "axios";
import LogItem from "components/ConversationLog/LogItem";
import ParticipantScriptList from "components/ConversationLog/ParticipantScriptList";
import SelfEvaluation from "components/GlobalComponents/SelfEvaluation";

import { Table, TableBody, Box } from "@mui/material";
import backgroundImage from "assets/backgroundImage.png";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

// 로그 상세정보 get axios
const getLogDetail = async (
  roomNo,
  userId,
  setLog,
  setParticipants,
  setLoading
) => {
  setLoading(true);
  try {
    const response = await axios.get(
      `${process.env.REACT_APP_HOST}/corns-log/${roomNo}/${userId}`
    );
    console.log(response.data);
    setLog(response.data.room);
    setParticipants(response.data.memberList);
  } catch (e) {
    console.log(e);
  }
  setLoading(false);
};

function LogDetail({ match }) {
  const { roomNo } = match.params;
  const [log, setLog] = useState({});
  const [participants, setParticipants] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    getLogDetail(
      roomNo,
      sessionStorage.getItem("userId"),
      setLog,
      setParticipants,
      setLoading
    );
  }, [roomNo]);

  return (
    <>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableBody>
          <LogItem log={log} />
        </TableBody>
      </Table>
      <hr />

      <ParticipantScriptList participants={participants} />

      <Box
        sx={{
          width: "95.4%",
          padding: "32px",
          backgroundImage: `url(${backgroundImage})`,
          position: "absolute",
          bottom: "0",
          left: "0",
        }}
      >
        <SelfEvaluation />
      </Box>
    </>
  );
}

export default LogDetail;
