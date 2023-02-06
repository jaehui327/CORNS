import React, { useState, useEffect } from "react";

import { Box, Input, Select, MenuItem } from "@mui/material";
import { Search } from "react-bootstrap-icons";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

function SearchComp({ type, setType, text, setText, search, setSearch }) {
  // const [type, setType] = useState("user_id");
  // const [text, setText] = useState("");
  const [pathName, setPathName] = useState("");
  const [textInfo, setTextInfo] = useState("");

  // 유저 검색 or 친구인지 확인
  useEffect(() => {
    if (window.location.pathname.includes("searchUser")) {
      setPathName("searchUser");
    } else if (window.location.pathname.includes("friends")) {
      setPathName("friends");
    }
  }, [window.location.pathname]);

  // id or nickname 확인
  useEffect(() => {
    if (type === "id") {
      setTextInfo("숫자로 입력해주세요.");
    } else {
      setTextInfo("영어로 입력해주세요.");
    }
  }, [type]);

  // input handle
  const onChange = (e) => {
    if (type === "id") {
      if (e.target.value.toLowerCase().match(/^[0-9]*$/)) {
        setText(e.target.value);
      }
    } else {
      if (e.target.value.match(/^[a-zA-Z]*$/)) {
        setText(e.target.value);
      }
    }
    // 친구 검색일때만 바뀔때마다 검색되도록
    if (pathName === "friends") {
      setSearch((prev) => prev + 1);
    }
  };

  // type handle
  const handleChange = (e) => {
    setType(e.target.value);
    setText("");
    setSearch(0);
  };

  // search handle
  const onSearch = () => {
    setSearch((prev) => prev + 1);
  };

  return (
    <>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-around",
        }}
      >
        <Select
          value={type}
          onChange={handleChange}
          sx={{ height: "51px", border: "3px solid #111" }}
        >
          <MenuItem value={"id"}>아이디</MenuItem>
          <MenuItem value={"nickname"}>닉네임</MenuItem>
        </Select>
        <Box sx={{ width: "100%", height: "100%" }}>
          <input
            placeholder={`${type}을 입력해주세요.`}
            value={text}
            onChange={onChange}
            css={css`
              border: 3px solid #111;
              border-radius: 5px;
              width: 90%;
              height: 45px;
              font-size: 17px;
              padding: 0 0 0 2%;
              margin: 0 2%;
            `}
          />

          <Search
            css={css`
              font-size: 18px;
              cursor: pointer;
            `}
            onClick={onSearch}
          />
        </Box>
      </Box>

      <p
        css={css`
          font-size: 12px;
          margin-left: 14%;
        `}
      >
        {textInfo}
      </p>
    </>
  );
}

export default SearchComp;
