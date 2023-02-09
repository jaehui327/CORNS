import React, { useState } from "react";
import {
  Box,
  Button,
  Grid,
  Input,
  Modal,
  TextField,
  Typography,
} from "@mui/material";
import { XSquare } from "react-bootstrap-icons";

/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

function AddWordButton() {
  const [openModal, setOpenModal] = useState(false);
  const handleOpenModal = () => setOpenModal(true);
  const handleCloseModal = () => setOpenModal(false);
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: "40%",
    height: "10%",
    bgcolor: "background.paper",
    border: "3px solid #111",
    boxShadow: 24,
    p: "32px 0 200px",
  };

  return (
    <>
      <Button onClick={handleOpenModal} variant="contained">
        추가
      </Button>

      <Modal
        open={openModal}
        onClose={handleCloseModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Box sx={{ display: "flex", width: "100%", mr: "10%", mb: "5%" }}>
            <Typography variant="h5" sx={{ ml: "32px" }}>
              단어 등록
            </Typography>
            <XSquare
              css={css`
                margin-left: 75%;
                font-size: 30px;
                cursor: pointer;
              `}
              onClick={handleCloseModal}
            />
          </Box>
          <Grid
            container
            direction="row"
            justifyContent="space-evenly"
            alignItems="center"
            spacing={2}
          >
            <Grid
              item
              xs={5}
              sx={{
                boxShadow: "4px 4px 4px rgba(0,0,0,0.25)",
                border: "3px solid #111",
              }}
            >
              <Input
                rows={4}
                multiline={true}
                sx={{
                  width: "100%",
                  backgroundColor: "white",
                  padding: "1rem 5.5rem 1rem 1rem",
                }}
              ></Input>
            </Grid>
            <Grid
              item
              xs={5}
              sx={{
                boxShadow: "4px 4px 4px rgba(0,0,0,0.25)",
                border: "3px solid #111",
              }}
            >
              <Input
                rows={4}
                multiline={true}
                sx={{
                  width: "100%",
                  backgroundColor: "white",
                  padding: "1rem 5.5rem 1rem 1rem",
                }}
              ></Input>
            </Grid>
          </Grid>
          <Box sx={{ display: "flex", justifyContent: "center", mt: "4%" }}>
            <Button variant="contained">등록하기</Button>
          </Box>
        </Box>
      </Modal>
    </>
  );
}

export default AddWordButton;
