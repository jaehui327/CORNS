import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import axios from "axios";

import SigninEmail from "./SigninEmail";
import SigninPassword from "./SigninPassword";
import SigninNickname from "./SigninNickname";

import { Box, Button } from "@mui/material";
import yellow_logo from "assets/corns_logo_yellow.png";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

function SigninCard() {
  const [email, setEmail] = useState("");
  const [password1, setPassword1] = useState("");
  const [password2, setPassword2] = useState("");
  const [nickname, setNickname] = useState("");

  const [stateEmail, setStateEmail] = useState(false);
  const [statePassword, setStatePassword] = useState(false);
  const [stateNickname, setStateNickname] = useState(false);


  // 회원가입 -> fetch
  const onSubmit = async (e) => {
    e.preventDefault();

    try {
      console.log(email, password1);

      // POST 요청
      const res = await fetch("/user/join", {
        method: "POST",
        headers: {
          Accept: "application/json",
        },
        body: JSON.stringify({
          email,
          password1,
          nickname,
        }),
      });
      console.log(res.json());
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <Box
      sx={{
        padding: "32px 48px",
        border: "3px solid #111",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        backgroundColor: "white",
        height: "800px",
      }}
    >
      <NavLink to="/">
        <img
          src={yellow_logo}
          alt="corns-logo"
          css={css`
            height: 2rem;
          `}
        />
      </NavLink>

      <SigninEmail
        email={email}
        setEmail={setEmail}
        stateEmail={stateEmail}
        setStateEmail={setStateEmail}
      />

      <SigninPassword
        password1={password1}
        setPassword1={setPassword1}
        password2={password2}
        setPassword2={setPassword2}
        statePassword={statePassword}
        setStatePassword={setStatePassword}
      />

      <SigninNickname
        nickname={nickname}
        setNickname={setNickname}
        stateNickname={stateNickname}
        setStateNickname={setStateNickname}
      />

      <Button
        sx={{
          backgroundColor: "#FFC804",
          color: "#111111",
          margin: "64px 0",
          width: "240px",
          height: "60px",
        }}
        onClick={onSubmit}
      >
        회원가입
      </Button>
    </Box>
  );
}

export default SigninCard;
