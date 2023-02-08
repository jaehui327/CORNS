import React, { useEffect } from "react";
import axios from 'axios';
import { useSelector, useDispatch } from "react-redux";
import { getLogBookmarkListAxios } from "store/reducers/logBookmarkListReducer";
import { toStringDate } from "store/reducers/roomFilterReducer";

import LogHeader from "components/ConversationLog/LogHeader";
import LogList from "components/ConversationLog/LogList";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";

function LogBookmark() {
  // const dispatch = useDispatch();
  // const logs = useSelector((state) => state.logBookmarkListReducer.logBookmarkList);

  // const loading = useSelector(
  //   (state) => state.logBookmarkListReducer.isLogBookmarkListLoading
  // );

  // useEffect(() => {
  //   console.log('dispatch 하겠음!')
  //   dispatch(getLogBookmarkListAxios())
  //   console.log(logs)
  // }, []);

  const getLogBookmarkListAxios = async () => {
    console.log("즐겨찾기 axios 보내겠음!");
    const response = await axios.get(
      `${
        process.env.REACT_APP_HOST
      }/corns-log/bookmark/${sessionStorage.getItem("userId")}?` +
        new URLSearchParams({
          baseTime: toStringDate(),
          page: 0,
          size: 20,
          sort: "startTm,DESC",
        }),
      {
        validateStatus: (status) => status === 200 || status === 204,
      }
    );
    console.log("get bookmark");
    console.log(response);
    if (response.status === 200) {
      return response.data.list;
    } else if (response.status === 204) {
      return [];
    }
  };

  const [logs, setLogs] = React.useState([]);


  useEffect(() => {
    const data = getLogBookmarkListAxios();
    setLogs(data);
  }, [])



  return (
    <>
      <h2>즐겨찾기</h2>

      <TableContainer>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <LogHeader />
          </TableHead>
          <TableBody>{logs.length > 0 && <LogList logs={logs} />}</TableBody>
        </Table>
      </TableContainer>
    </>
  );
}

export default LogBookmark;
