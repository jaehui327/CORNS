import React, { useState, useEffect } from "react";
import axios from 'axios';
import SearchComp from "../GlobalComponents/SearchComp";
import UserList from "./UserList";

import { Box } from "@mui/material";

// 친구 검색 axios
const GetFriends = async (type, text) => {
  // const startDate = moment().format("YYYY-MM-DDTHH:mm:sszz")
  // console.log(startDate)
  console.log('axios', type, text);
  // try {
  //   const response = await axios.get(
  //     `${process.env.REACT_APP_HOST}/user?` +
  //       new URLSearchParams({
  //         page: 0,
  //         size: 10,
  //         baseTime: "2023-02-05 00:00:00",
  //         filter: type,
  //         keyword: text,
  //       }),
  //     {
  //       validateStatus: (status) => status === 200 || status === 204,
  //     }
  //   );
  //   if (response.status === 200) {
  //     console.log(response.data);


  //   } else if (response.status === 204) {
  //     console.log(response.data);
  //   }
  // } catch (e) {
  //   console.log(e);
  // }
};



function FriendList({ items }) {
  const [type, setType] = useState("id");
  const [text, setText] = useState("");
  const [search, setSearch] = useState(false);
  const [users, setUsers] = useState([]);

  useEffect(() => {
    if (text) {
      GetFriends(type, text);
    }
  })


  return (
    <>
      <h3>친구목록</h3>
      <SearchComp
        type={type}
        setType={setType}
        text={text}
        setText={setText}
        search={search}
        setSearch={setSearch}
      />
      <Box padding="48px 112px">
        <UserList userList={users} />
      </Box>
    </>
  );
}

export default FriendList;
