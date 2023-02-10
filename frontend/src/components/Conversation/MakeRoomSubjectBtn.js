import { React, useState} from "react";
import { Button } from "@mui/material";


function MakeRoomSubjectBtn({ subject }) {
  
    const { subjectNo, value } = subject;
    const [toggle, setToggle] = useState(true);

    // 버튼을 눌렀을 때 색깔이 바뀌게 하는 toggle 함수
    const btnColorToggle = (e) => {
      setToggle((prev) => !prev);
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
        //   backgroundColor: "#ddd",
          backgroundColor: toggle ? "#98DA7A" : "#ddd",
          "&:hover": {
            backgroundColor: "#BAE8A4",
          },
        }}
        id={subjectNo}
        onClick={() => {
          btnColorToggle();
        //   checkFilter();
        }}
      >
        { value }
      </Button>
    </>
  );
}

export default MakeRoomSubjectBtn;
