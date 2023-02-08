import RoomList from "components/Conversation/RoomList";
import React, { useEffect, useRef, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getRoomList } from "store/reducers/roomListReducer";
import { addPage } from "store/reducers/roomFilterReducer";
import { addPageCount } from "store/reducers/pageReducer";
import useIntersect from "utils/useIntersect";

function RoomListsContainer({ main }) {
  const dispatch = useDispatch();
  const { data, loading } = useSelector((state) => state.roomListReducer);
  const { pageCount } = useSelector((state) => state.pageReducer);
  const filter = useSelector((state) => state.roomFilterReducer);
  const page = useRef(pageCount);
  const [list, setList] = useState([]);

  useEffect(() => {
    dispatch(getRoomList(filter));
  }, [dispatch, filter]);

  useEffect(() => {
    setList(data.list);
  }, [data]);

  // const [_, setRef] = useIntersect(async (entry, observer) => {
  //   observer.unobserve(entry.target);
  //   await dispatch(addPage(page));
  //   const addList = await dispatch(getRoomList(filter));
  //   await setList([...list, addList]);
  //   observer.observe(entry.target);
  // }, {});

  let maxRoomList = false;

  if (main) maxRoomList = true;

  const addNext = async () => {
    const addList = await dispatch(getRoomList(filter));
    setList([...list, addList]);
  };

  const nextPage = () => {
    dispatch(addPageCount(pageCount, "ADD_PAGE_COUNT"));
    dispatch(addPage(pageCount, "ADD_PAGE"));
    addNext();
  };

  return (
    <div>
      {list && <RoomList roomLists={list} maxRoomList={maxRoomList} />}
      {loading && <div>로딩중</div>}
      <button onClick={nextPage}>추가로 불러오기</button>
    </div>
  );
}

export default RoomListsContainer;
