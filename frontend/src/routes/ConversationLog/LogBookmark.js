import React, { useEffect, useState } from "react";
import axios from "axios";
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
  const dispatch = useDispatch();
  const logs = useSelector(
    (state) => state.logBookmarkListReducer.logBookmarkList
  );
  const loading = useSelector(
    (state) => state.logBookmarkListReducer.isLogBookmarkListLoading
  );
  const sort = useSelector((state) => state.logBookmarkListReducer.sort);

  useEffect(() => {
    dispatch(getLogBookmarkListAxios(sort));
  }, [dispatch]);

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
