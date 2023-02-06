import { Checkbox, ListItem, ListItemIcon, ListItemText } from "@mui/material";
import React from "react";

function WordItem({ word, handletoggle }) {
  const [checked, setChecked] = React.useState([]);

  const labelId = `transfer-list-item-${word}-label`;

  console.log(handletoggle);

  return (
    <ListItem key={word} role="listitem" button onClick={(word) => handletoggle}>
      <ListItemIcon>
        <Checkbox
          checked={checked.indexOf(word) !== -1}
          tabIndex={-1}
          disableRipple
          inputProps={{
            "aria-labelledby": labelId,
          }}
        />
      </ListItemIcon>
      <ListItemText id={labelId} primary={`List item ${word + 1}`} />
    </ListItem>
  );
}

export default WordItem;
