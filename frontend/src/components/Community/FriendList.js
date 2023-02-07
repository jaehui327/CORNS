import React, { useState, useEffect } from "react";
import SearchComp from "components/GlobalComponents/SearchComp";
import UserList from "./UserList";
import { useSelector, useDispatch } from "react-redux";
import { getFriendListAxios } from "store/reducers/friendListReducer";
import { Box } from "@mui/material";

function FriendList({ items }) {
  const dispatch = useDispatch();
  const [type, setType] = useState("nickname");
  const [text, setText] = useState("");
  const [search, setSearch] = useState(false);
  const users = useSelector((state) => state.friendListReducer.friendList);

  useEffect(() => {
    dispatch(getFriendListAxios(type, text));
  }, [type, text, dispatch]);

  return (
    <>
      <h3>친구목록</h3>
      <SearchComp
        type={type}
        setType={setType}
        text={text}
        setText={setText}
        setSearch={setSearch}
      />
      <Box padding="48px 112px">
        {users && <UserList userList={users} />}
      </Box>
    </>
  );
}

export default FriendList;
