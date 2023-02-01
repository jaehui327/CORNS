import React, { useState } from "react";
import { NavLink } from "react-router-dom";

import { Box, Button } from "@mui/material";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

function SigninNickname({nickname, setNickname, stateNickname, setStateNickname}) {
  const onChangeNickname = (e) => {
    setNickname(e.target.value);
  };

  // 닉네임 유효성 검사 -> 추후 수정
  const validateNickname = (nickname) => {
    return nickname.toLowerCase().match(/^[a-z|A-Z|].{1,8}$/);
  };
  
  return (
    <Box sx={{ width: "80%" }}>
      <h5
        css={css`
          font-size: 20px;
        `}
      >
        닉네임
      </h5>
      <input
        placeholder="닉네임을 입력하세요."
        value={nickname}
        onChange={onChangeNickname}
        css={css`
          width: 95%;
          height: 45px;
        `}
      />
      <div
        css={css`
          font-size: 12px;
        `}
      >
        닉네임은 실제로 불릴 이름이며, 영문으로 표기해야합니다.
      </div>
    </Box>
  );
}

export default SigninNickname;
