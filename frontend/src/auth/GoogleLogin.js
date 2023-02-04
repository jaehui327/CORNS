import React from "react";
import axios from "axios";

const GoogleLogin = async (code) => {
  try {
    const response = await axios.get(
      `${process.env.REACT_APP_HOST}/user/auth/google/callback?code=${code}`
    );
    console.log(response);
  } catch (e) {
    console.log(e);
  }
};

export default GoogleLogin;
