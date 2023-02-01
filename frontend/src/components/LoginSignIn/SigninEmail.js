import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import axios from "axios";

import { Box, Button } from "@mui/material";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";


function SigninEmail({email, setEmail, stateEmail, setStateEmail}) {
  const onChangeEmail = (e) => {
    setEmail(e.target.value);
    setStateEmail(false);
  };
  
    // 이메일 유효성 검사
    const validateEmail = (email) => {
      return email
        .toLowerCase()
        .match(
          /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/
        );
    };
  
    // 이메일 중복 확인 axios
    const onCheckEmail = async (e) => {
      e.preventDefault();
      if (!email) {
        alert("이메일을 입력해주세요.");
        return;
      }
      if (!validateEmail(email)) {
        alert("유효하지 않은 이메일입니다.");
        return;
      }
      try {
        const response = await axios.get(
          `${process.env.REACT_APP_HOST}/user/email-check/${email}`
        );
        const result = response.status;
        if (result === 200) {
          setStateEmail(true);
        } else {
          alert("사용할 수 없는 이메일입니다.");
        }
      } catch (e) {
        console.log(e);
      }
    };
  
  return (
    <Box sx={{ width: "80%" }}>
    <h5
      css={css`
        font-size: 20px;
      `}
    >
      이메일
    </h5>
    <Box sx={{ display: "flex", flexDirectionn: "row", gap: "10px" }}>
      <input
        placeholder="이메일을 입력하세요."
        value={email}
        onChange={onChangeEmail}
        css={css`
          width: 80%;
          height: 45px;
        `}
      />
      <Button
        sx={{
          backgroundColor: "#FFB800",
          color: "#111111",
        }}
        onClick={onCheckEmail}
      >
        중복확인
      </Button>
    </Box>
    {stateEmail && (
      <div
        css={css`
          font-size: 12px;
          color: #67C73A;
        `}
      >
        사용가능한 이메일입니다.
      </div>
    )}
  </Box>
  )
}

export default SigninEmail;
