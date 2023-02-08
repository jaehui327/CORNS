import React from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";
import { getLogBookmarkListAxios } from "store/reducers/logBookmarkListReducer";
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

// 즐겨찾기 등록 / 해제 patch
// CORS 에러.....
const toggleBookmark = async (roomNo, userId, doRegister) => {
  try {
    const response = await axios.patch(
      `${process.env.REACT_APP_HOST}/friend/delete`,
      {
        roomNo,
        userId,
        doRegister,
      }
    );
    if (response.status === 200) {
    }
  } catch (e) {
    console.log(e);
  }
};

function LogItem({ log }) {
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
  const subjectString = (subject) => {
    switch (subject) {
      case 1:
        return "일상";
      case 2:
        return "비즈니스";
      case 3:
        return "시험";
      case 4:
        return "소개팅";
      case 5:
        return "자유";
      default:
        return "";
    }
  };

  const dispatch = useDispatch();
  const sort = useSelector((state) => state.logBookmarkListReducer.sort);

  // 북마크 삭제 / 추가
  const onToggleHandler = async () => {
    await toggleBookmark(roomNo, sessionStorage.getItem("userId"), !isBookmark);
    // 즐겨찾기 page일때만 갱신
    if (window.location.pathname.includes("bookmarks")) {
      dispatch(getLogBookmarkListAxios(sort));
    }
  };

  return (
    <TableRow sx={{ "&:last-child td, &:last-child th": { border: 0 } }}>
      {/* 북마크 */}
      <TableCell component="th" scope="row" width="10%" align="center">
        {isBookmark ? (
          <BookmarkStarFill
            css={css`
              font-size: 25px;
              color: #3c90f2;
            `}
            onClick={onToggleHandler}
          />
        ) : (
          <BookmarkStar
            css={css`
              font-size: 25px;
              color: #3c90f2;
            `}
            onClick={onToggleHandler}
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
            width: 100%;
          `}
        >
          {subjectString(subject)}
        </div>
      </TableCell>

      {/* 제목 */}
      <TableCell width="25%" align="center">
        {/* 로그 상세에서는 LINK X */}
        {window.location.href.includes("logdetail") ? (
          title
        ) : (
          <Link to={logUrl} style={{ textDecoration: "none", color: "black" }}>
            {title}
          </Link>
        )}
      </TableCell>

      {/* 날짜 */}
      <TableCell width="15%" align="center">
        {startTime}
      </TableCell>

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
      <TableCell width="10%" align="center">
        {member}명
      </TableCell>

      {/* 자기평가 */}
      <TableCell width="10%" align="center">
        <div
          css={css`
            border: 2px solid #111111;
            text-align: center;
            width: 100%;
          `}
        >
          <Star
            css={css`
              font-size: 17px;
            `}
          />

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
          <HandThumbsUp
            css={css`
              font-size: 17px;
            `}
          />
          {thumbCnt}
        </div>
      </TableCell>
    </TableRow>
  );
}

export default LogItem;
