import axios from "axios";
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

export const getRoomList = createAsyncThunk("GET_ROOM_LIST", async (filter) => {
  try {
    const response = await axios.get(`${process.env.REACT_APP_HOST}/room`, {
      params: filter,
    });
    return response.data;
  } catch (e) {
    console.log("알수없는 error", e);
  }
});

export const roomListReducer = createSlice({
  name: "roomList",
  initialState: { data: {}, loading: "idle" },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getRoomList.pending, (state) => void (state.loading = "pending"))
      .addCase(getRoomList.fulfilled, (state, action) => {
        state.loading = "successed";
        state.data = action.payload;
      })
      .addCase(
        getRoomList.rejected,
        (state) => void (state.loading = "failed")
      );
  },
});
