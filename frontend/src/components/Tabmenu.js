import React from "react";
import { Link } from "react-router-dom";

const Tabmenu = ({ Items, ...rest }) => {
  return (
    <div>
      {Items.map((item, index) => {
        return (
          <Link to={item.route} key={index}>
            <div>{item.name}</div>
          </Link>
        );
      })}
    </div>
  );
};

export default Tabmenu;
