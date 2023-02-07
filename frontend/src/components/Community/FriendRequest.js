import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getFriendRequestListAxios } from "store/reducers/friendListReducer";

import RequestList from "./RequestList";
import { Box } from "@mui/material";

function FriendRequest() {
  const dispatch = useDispatch();
  const users = useSelector((state) => state.friendListReducer.friendRequestList)

  useEffect(() => {
    dispatch(getFriendRequestListAxios());
  }, [dispatch]);

  return (
    <>
      <h3>친구신청</h3>
      {users.length > 0 && <RequestList users={users} />}
      {users.length === 0 && (
        <Box
          sx={{
            backgroundColor: "#DDDDDD",
            border: "3px solid #111111",
            position: "relative",
            p: "1.5rem",
          }}
        >
          <p>친구신청이 없습니다.</p>
        </Box>
      )}
    </>
  );
}
export default FriendRequest;
