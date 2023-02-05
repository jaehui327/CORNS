import { React, useState } from "react";
import { Button, Typography } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";

import { addSubject } from "store/reducers/roomFilterReducer";

function SubjectBtn({ subject }) {
  const dispatch = useDispatch();
  const filter = useSelector((state) => state.roomFilterReducer);

  const { subjectNo, imgUrl, value } = subject;
  const [toggle, setToggle] = useState(false);

  // 버튼을 눌렀을 때 색깔이 바뀌게 하는 toggle 함수
  const btnColorToggle = (e) => {
    setToggle((prev) => !prev);
  };

  const checkFilter = () => {
    dispatch(addSubject(subjectNo, "ADD"));
  };

  return (
    <>
      <Button
        variant="contained"
        sx={{
          height: "40px",
          padding: "0 30px",
          mr: "32px",
          border: "3px solid #111",
          borderRadius: "0",
          color: "#111",
          backgroundColor: toggle ? "#98DA7A" : "#ddd",
          "&:hover": {
            backgroundColor: "#BAE8A4",
          },
        }}
        onClick={(btnColorToggle, checkFilter)}
      >
        {value}
      </Button>
    </>
  );
}

export default SubjectBtn;
