import { createSlice } from "@reduxjs/toolkit";

// export const addSubject = (state, action) => {};
// export const removeSubject = (state, action) => {
//   state.subject.filter((sub) => sub.subjectNo !== action.payload);
// };

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
    add: (state, action) => {
      state.subject.push(action.payload);
    },
    remove: (state, action) => {
      const cleaned = state.subject.filter(
        (sub) => sub.id !== action.payload.id
      );
      return { ...state, subject: cleaned };
    },
  },
});

export default roomFilterReducer.reducer;
export const { add, remove } = roomFilterReducer.actions;
