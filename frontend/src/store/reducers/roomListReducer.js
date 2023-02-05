import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

export const getRoomList = createAsyncThunk("GET_ROOM_LIST", async () => {
  const response = await axios.get(
    `${process.env.REACT_APP_HOST}/room?page=1&size=18&basetime=2022-0205 00:00:00&subject=1%2%3%4%5&minTime=5&maxTime=30&showAvail=true`
  );
  return response.data.rooms;
});

export const postRoomList = createAsyncThunk("POST_ROOM_LIST", async () => {});

export const roomListReducer = createSlice({
  name: "roomList",
  initialState: [],
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getRoomList.fulfilled, (state, { payload }) => [...payload])
      .addCase(postRoomList.fulfilled, (state, { payload }) => [...payload]);
  },
});
