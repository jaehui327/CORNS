import axios from "axios";
import { createSlice } from "@reduxjs/toolkit";

const initialWordListState = {
    isWordListLoading: false,
    todoList: [],
    doneList: [],
};

const userId = sessionStorage.getItem("userId");

export const wordListReducer = createSlice({
    name: "wordList",
    initialState: initialWordListState,
    reducers: {
        isWordListLoading(state) {
            state.isWordListLoading = !state.isWordListLoading;
        },
        getTodoList(state, actions) {
            state.todoList = actions.payload;
        },
        getDoneList(state, actions) {
            state.doneList = actions.payload;
        },
    },
});

export const getTodoList = (baseTime) => {
    return async (dispatch) => {
        dispatch(wordActions.isWordListLoading());
        const sendRequest = async () => {
            const response = await axios.get(`${process.env.REACT_APP_HOST}/word/${userId}?` + 
                new URLSearchParams({
                    baseTime: baseTime,
                    wordStatus: 1,
                    page: 0,
                    size: 100
                }),
            );
            return response.data.list;
        };
        try {
            const todoList = await sendRequest();
            dispatch(wordActions.getTodoList(todoList));
        } catch (e) {
            console.error(e);
        }
        dispatch(wordActions.isWordListLoading());
    }
};

export const getDoneList = (baseTime) => {
    return async (dispatch) => {
        dispatch(wordActions.isWordListLoading());
        const sendRequest = async () => {
            const response = await axios.get(`${process.env.REACT_APP_HOST}/word/${userId}?` + 
                new URLSearchParams({
                    baseTime: baseTime,
                    wordStatus: 2,
                    page: 0,
                    size: 100
                }),
            );
            return response.data.list;
        };
        try {
            const doneList = await sendRequest();
            dispatch(wordActions.getDoneList(doneList));
        } catch (e) {
            console.error(e);
        }
        dispatch(wordActions.isWordListLoading());
    }
};

export const wordActions = wordListReducer.actions;
export const { initialState } = wordListReducer;