import React, { useState } from "react";
import { Link } from "react-router-dom";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import {
  BookmarkStar,
  BookmarkStarFill,
  Star,
  HandThumbsUp,
} from "react-bootstrap-icons";

/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

function LogItem({ log }) {
  // dummy data
  const {
    roomNo,
    isBookmark,
    subject,
    title,
    startTime,
    time,
    member,
    selfScore,
    thumbCnt,
  } = log;
  
  const logUrl = `/conversationLog/logdetail/${roomNo}`;
  const subjectString = subject

  // const [bookmarkState, setBookmarkState] = useState(bookmark);

  // // 북마크 삭제 / 추가
  // const onToggle = () => {
  //   setBookmarkState(!bookmarkState);
  // };

  return (
    <TableRow sx={{ "&:last-child td, &:last-child th": { border: 0 } }}>
      {/* 북마크 */}
      <TableCell component="th" scope="row" width="10%" align="center">
        {isBookmark ? (
          <BookmarkStar
            css={css`
              font-size: 24px;
              color: #3c90f2;
            `}
            // onClick={onToggle}
          />
        ) : (
          <BookmarkStarFill
            css={css`
              font-size: 24px;
              color: #3c90f2;
            `}
            // onClick={onToggle}
          />
        )}
      </TableCell>

      {/* 주제 */}
      <TableCell width="10%" align="center">
        <div
          css={css`
            border: 2px solid #111111;
            background: #ffa903;
            text-align: center;
            width: 100%
          `}
        >
          {subject}
        </div>
      </TableCell>

      {/* 제목 */}
      <TableCell width="25%" align="center">
        <Link to={logUrl} style={{ textDecoration: "none", color: "black" }}>
          {title}
        </Link>
      </TableCell>

      {/* 날짜 */}
      <TableCell width="15%" align="center">{startTime}</TableCell>

      {/* 대화시간 */}
      <TableCell width="10%" align="center">
        <div
          css={css`
            border: 2px solid #111111;
            background: #3c90f2;
            color: white;
            text-align: center;
            width: 100%;
          `}
        >
          {time}분
        </div>
      </TableCell>

      {/* 인원 */}
      <TableCell width="10%" align="center">{member}명</TableCell>

      {/* 자기평가 */}
      <TableCell width="10%" align="center">
        <div
          css={css`
            border: 2px solid #111111;
            text-align: center;
            width: 100%;
          `}
        >
          <Star css={css`font-size: 17px;`}/>

          {selfScore}
        </div>
      </TableCell>

      {/* 따봉개수 */}
      <TableCell width="10%" align="center">
        <div
          css={css`
            border: 2px solid #111111;
            text-align: center;
            width: 100%;
          `}
        >
          <HandThumbsUp css={css`font-size: 17px;`}/>
          {thumbCnt}
        </div>
      </TableCell>
    </TableRow>
  );
}

export default LogItem;
