import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

const userId = "";

export const getUserInfo = createAsyncThunk("GET_USER_Info", async () => {
  const response = await axios.get(
    `${process.env.REACT_APP_HOST}/user/${userId}`
  );
  return response.data;
});

export const userInfoReducer = createSlice({
  name: "userInfo",
  initialState: [],
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(getUserInfo.fulfilled, (state, { payload }) => [
      ...payload,
    ]);
  },
});
