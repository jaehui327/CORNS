import { createSlice } from "@reduxjs/toolkit";

export const addSubject = (state, action) => {};
export const removeSubject = (state, action) => {
  state.subject.filter((sub) => sub.subjectNo !== action.payload);
};

export const roomFilterReducer = createSlice({
  name: "roomFilter",
  initialState: {
    page: 0,
    size: 6,
    subject: [],
    time: {
      minTime: 0,
      maxTime: 30,
    },
    isAvail: false,
  },
  reducers: {
    addSubject,
    removeSubject,
  },
});

export default roomFilterReducer.reducer;
