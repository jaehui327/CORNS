import React, {useState} from "react";
import { useDispatch } from "react-redux";
import {TableRow, TableCell,Select,MenuItem}   from "@mui/material";
import { logBookmarkListActions, getLogBookmarkListAxios } from "store/reducers/logBookmarkListReducer";

function LogHeader() {
  const dispatch = useDispatch()
  const [type, setType] = useState("DESC");
  
  const changeHandler = (e) => {
    setType(e.target.value);
    dispatch(logBookmarkListActions.toggleSortOption(e.target.value))
    dispatch(getLogBookmarkListAxios(e.target.value))
  };
  
  return (
    <TableRow>
      <TableCell width="10%" align="center">
        북마크
      </TableCell>

      <TableCell width="10%" align="center">
        주제
      </TableCell>

      <TableCell width="25%" align="center">
        제목
      </TableCell>

      <TableCell width="15%" align="center">
        <Select value={type} variant="standard" size="small" onChange={changeHandler} sx={{fontSize: "14px"}} >
          <MenuItem value={"DESC"}>최신순</MenuItem>
          <MenuItem value={"ASC"}>오래된순</MenuItem>
        </Select>
      </TableCell>

      <TableCell width="10%" align="center">
        대화시간
      </TableCell>

      <TableCell width="10%" align="center">
        인원
      </TableCell>

      <TableCell width="10%" align="center">
        자기평가
      </TableCell>

      <TableCell width="10%" align="center">
        따봉개수
      </TableCell>
    </TableRow>
  );
}

export default LogHeader;
