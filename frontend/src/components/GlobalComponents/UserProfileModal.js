import React, { useState, useEffect } from "react";
import axios from "axios";
import UserProfile from "../../components/GlobalComponents/UserProfile";
import { Box, Typography, Modal } from "@mui/material";
import { XSquare } from "react-bootstrap-icons";
import FriendsBtn from "./FriendsBtn";
import RequestForm from "./RequestForm";

/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

// user detail get axios
const GetUserModalDetail = async (from_id, to_id, setUser, setRelation) => {
  console.log("get user detail", from_id, to_id);
  try {
    // 추후 back에서 url 수정 예정
    const apiUrl =
      from_id === to_id.toString()
        ? `user/${from_id}`
        : `growth/${from_id}/${to_id}`;
    console.log(apiUrl);
    const response = await axios.get(`${process.env.REACT_APP_HOST}/${apiUrl}`);
    if (response.status === 200) {
      // url 수정되면 추후 수정
      setUser(response.data.user ? response.data.user : response.data.toUser);
      setRelation(response.data.relation ? response.data.relation : false);
    }
  } catch (e) {
    console.log(e);
  }
};

function UserProfileModal({ openModal, toId, handleCloseModal }) {
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: "70%",
    height: "50%",
    bgcolor: "background.paper",
    border: "3px solid #111",
    boxShadow: 24,
    p: "32px 0 200px",
  };

  const [user, setUser] = useState({});
  const [relation, setRelation] = useState(false);
  const [openForm, setOpenForm] = useState(false);

  const handleOpenForm = () => setOpenForm(true);
  const handleCloseForm = () => setOpenForm(false);

  useEffect(() => {
    if (!openModal) {
      return;
    }
    GetUserModalDetail(
      sessionStorage.getItem("userId"),
      toId,
      setUser,
      setRelation
    );
  }, [openForm]);

  return (
    user && (
      <>
        <Box sx={style}>
          <Box sx={{display: 'flex', width: "100%", mr: "10%", mb: "5%"}}>
            <Typography variant="h5" sx={{ ml: "32px" }}>
              {user.nickname} 페이지
            </Typography>
            <XSquare css={css`margin-left: 80%; font-size: 30px; cursor: pointer`} onClick={handleCloseModal}/>
          </Box>
          {/* <UserProfile user={user} />
          <Box
            sx={{ display: "flex", justifyContent: "center", mt: "4%" }}
            onClick={handleOpen}
          >
            <FriendsBtn status={"1"} />
          </Box> */}
        </Box>

        {/* <Modal open={handleOpenForm} onClose={handleCloseForm}>
          <Box>
            <RequestForm />
          </Box>
        </Modal> */}
      </>
    )
  );
}

export default UserProfileModal;
