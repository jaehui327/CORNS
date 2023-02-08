import RoomList from "components/Conversation/RoomList";
import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getRoomList } from "store/reducers/roomListReducer";

function RoomListsContainer({ main }) {
  const dispatch = useDispatch();
  const { data, loading } = useSelector((state) => state.roomListReducer);
  const filter = useSelector((state) => state.roomFilterReducer);

  useEffect(() => {
    dispatch(getRoomList(filter));
  }, [dispatch, filter]);

  let maxRoomList = false;

  if (main) maxRoomList = true;

  if (loading === "pending") {
    return <div>로딩중</div>;
  }
  if (loading === "successed") {
    const roomList = data;
    return (
      <div>
        {roomList && (
          <RoomList roomLists={roomList.list} maxRoomList={maxRoomList} />
        )}
      </div>
    );
  }
}

export default RoomListsContainer;
