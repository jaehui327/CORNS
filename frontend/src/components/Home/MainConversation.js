import RoomList from "components/Conversation/RoomList";
import React, { useEffect, useRef, useState, useMemo } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getRoomList } from "store/reducers/roomListReducer";

function MainConversation() {
  const dispatch = useDispatch();
  const { data, loading } = useSelector((state) => state.roomListReducer);
  const filter = useSelector((state) => state.roomFilterReducer);

  useEffect(() => {
    dispatch(getRoomList(filter));
  }, [dispatch, filter]);

  if (!loading) {
    return (
      <div>
        <RoomList roomLists={data.list.slice(0, 6)} />
      </div>
    );
  }
}

export default MainConversation;
