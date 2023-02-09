import * as React from "react";
import SubjectBtn from "./SubjectBtn";
import SubjectsContainer from "store/containers/SubjectsContainer";

import FormControl from "@mui/material/FormControl";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Tooltip from "@mui/material/Tooltip";
import Button from "@mui/material/Button";
import { PlusCircleFill, XSquare } from "react-bootstrap-icons";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

import makeRoom from "components/Conversation/MakeRoom";

/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";


const style = {
  display: "flex",
  justifyContent: "space-around",
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "80%",
  height: "50%",
  bgcolor: "background.paper",
  border: "3px solid #111",
  boxShadow: 24,
  p: 4,
};

const datas = [
  { id: 1, subject: "일상" },
  { id: 2, subject: "비즈니스" },
  { id: 3, subject: "소개팅" },
  { id: 4, subject: "오픽" },
  { id: 5, subject: "토스" },
  { id: 6, subject: "자유" },
];

export default function RoomCreateModal() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleChange = (e) => {
    setOpen(e.target.value);
  };

  
    makeRoom();
  


  return (
    <div>
      <Tooltip title="쫑알룸 만들기" placement="top-start">
        <PlusCircleFill
          style={{
            fontSize: "64px",
            color: "#ffa903",
            position: "fixed",
            bottom: "40px",
            right: "105px",
            borderRadius: "40px",
            border: "3px solid #111",
            boxShadow: "4px 4px 4px rgb(0,0,0,.5)",
            cursor: "pointer",
          }}
          onClick={handleOpen}
        />
      </Tooltip>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Box
            css={css`
              margin-left: 50px;
            `}>
            <input
             css={css`
             border: 3px solid #111;
             width: 1300px;
             height: 40px;
             margin-top: 100px;
           `}
            placeholder="제목을 입력해주세요."></input>
            <Box
            css={css`
              margin: 5px;
            `}
            ><div
            css={css`
              margin: 5px;
            `}
            >주제</div>
              <SubjectsContainer/>  
            </Box>
            <Box
            css={css`
            margin: 5px;
            `}
              >
              시간      
              <FormControl
                variant="standard"
                sx={{
                  backgroundColor: "#fff",
                  border: "3px solid #111",
                  width: "50%",
                  height: "100%",
                  pl: "1%",
                  margin: "5px"
                }}
              >
              <Select onChange={handleChange} min-width="100px">
                <MenuItem value={5}>5분</MenuItem>
                <MenuItem value={10}>10분</MenuItem>
                <MenuItem value={15}>15분</MenuItem>
                <MenuItem value={20}>20분</MenuItem>
                <MenuItem value={25}>25분</MenuItem>
                <MenuItem value={30}>30분</MenuItem>
              </Select>
              </FormControl>
            </Box>
            <Box
            css={css`
            margin: 5px;
            `}
              >
              인원
              <FormControl
                variant="standard"
                sx={{
                  backgroundColor: "#fff",
                  border: "3px solid #111",
                  width: "50%",
                  height: "100%",
                  pl: "1%",
                  margin: "5px"
                }}
              >
              <Select onChange={handleChange} min-width="100px">
                <MenuItem value="2">2</MenuItem>
                <MenuItem value="3">3</MenuItem>
                <MenuItem value="4">4</MenuItem>
              </Select>
              </FormControl>
            </Box>
            <Button 
            css={css`
            float: right
            `} variant="contained" onClick={makeRoom}>방만들기</Button>
          </Box>
          <XSquare
            css={css`
              cursor: pointer;
            `}
            onClick={handleClose}
          />
        </Box>
      </Modal>
    </div>
  );
}
