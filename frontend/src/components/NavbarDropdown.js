import React from "react";
import { NavLink } from "react-router-dom";
import { PersonCircle } from "react-bootstrap-icons";
import Collapse from "@mui/material/Collapse";

function NavbarDropdown() {
  const [open, setOpen] = React.useState(false);

  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <>
      <PersonCircle onClick={handleClick} />
      <ul>
        <Collapse in={open} timeout="auto" unmountOnExit>
          <li>
            <NavLink to="/mypage">마이페이지</NavLink>
          </li>
          <li>
            <NavLink to="/friend-list">친구신청목록</NavLink>
          </li>
        </Collapse>
      </ul>
    </>
  );
}

export default NavbarDropdown;
