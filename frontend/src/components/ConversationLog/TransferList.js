import * as React from 'react';
import Grid from '@mui/material/Grid';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';

import DeleteIcon from '@mui/icons-material/DeleteForeverOutlined';
import EditIcon from '@mui/icons-material/EditOutlined';
import SaveIcon from '@mui/icons-material/CheckBoxOutlined';


function not(a, b) {
  return a.filter((value) => b.indexOf(value) === -1);
}

function intersection(a, b) {
  return a.filter((value) => b.indexOf(value) !== -1);
}

export default function TransferList() {
  const [checked, setChecked] = React.useState([]);
  const [left, setLeft] = React.useState([0, 1, 2, 3, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]);
  const [right, setRight] = React.useState([4, 5, 6, 7]);

  const leftChecked = intersection(checked, left);
  const rightChecked = intersection(checked, right);

  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };

  const handleAllRight = () => {
    setRight(right.concat(left));
    setLeft([]);
  };

  const handleCheckedRight = () => {
    setRight(right.concat(leftChecked));
    setLeft(not(left, leftChecked));
    setChecked(not(checked, leftChecked));
  };

  const handleCheckedLeft = () => {
    setLeft(left.concat(rightChecked));
    setRight(not(right, rightChecked));
    setChecked(not(checked, rightChecked));
  };

  const handleAllLeft = () => {
    setLeft(left.concat(right));
    setRight([]);
  };

  const todoList = (items) => (
    <Paper sx={{ width: "100%", height: "600px", overflow: 'auto' }}>
      <List dense component="div" role="list">
        {items.map((value) => {
          const labelId = `transfer-list-item-${value}-label`;

          return (
            <ListItem
              key={value}
              role="listitem"
              button
              onClick={handleToggle(value)}
            >
              <ListItemIcon>
                <Checkbox
                  checked={checked.indexOf(value) !== -1}
                  tabIndex={-1}
                  disableRipple
                  inputProps={{
                    'aria-labelledby': labelId,
                  }}
                />
              </ListItemIcon>
              <ListItemText id={labelId} primary={`List item ${value + 1}`} />
              <ListItemIcon>
                <EditIcon color="warning"></EditIcon>
                <DeleteIcon color="error"></DeleteIcon>
              </ListItemIcon>
            </ListItem>
          );
        })}
      </List>
    </Paper>
  );

  const doneList = (items) => (
    <Paper sx={{ width: "100%", height: "600px", overflow: 'auto' }}>
      <List dense component="div" role="list">
        {items.map((value) => {
          const labelId = `transfer-list-item-${value}-label`;

          return (
            <ListItem
              key={value}
              role="listitem"
              button
              onClick={handleToggle(value)}
            >
              <ListItemIcon>
                <Checkbox
                  checked={checked.indexOf(value) !== -1}
                  tabIndex={-1}
                  disableRipple
                  inputProps={{
                    'aria-labelledby': labelId,
                  }}
                />
              </ListItemIcon>
              <ListItemText id={labelId} primary={`List item ${value + 1}`} />
              <ListItemIcon>
                <SaveIcon color="success"></SaveIcon>
              </ListItemIcon>
            </ListItem>
          );
        })}
      </List>
    </Paper>
  );

  return (
    <Grid container sx={{ mt: "32px" }} justifyContent="center" alignItems="center">
      <Grid item xs={5.5} sx={{ boxShadow:"4px 4px 4px rgba(0,0,0,0.25)", border: "3px solid #111" }}>
        <Grid sx={{ ml: "16px", mr: "16px" }}>
          <Grid container direction="row" justifyContent="space-between" alignItems="center">
            <h3>외워야할 쫑알단어</h3>
            <Button variant="contained">추가</Button>
          </Grid>
          </Grid>
        {todoList(left)}
      </Grid>
      <Grid item xs={1}> 
        <Grid container direction="column" justifyContent="center" alignItems="center">
          <Button
            sx={{ my: 0.5 }}
            variant="outlined"
            size="small"
            onClick={handleCheckedRight}
            disabled={leftChecked.length === 0}
            aria-label="move selected right"
          >
            &gt;
          </Button>
          <Button
            sx={{ my: 0.5 }}
            variant="outlined"
            size="small"
            onClick={handleCheckedLeft}
            disabled={rightChecked.length === 0}
            aria-label="move selected left"
          >
            &lt;
          </Button>
        </Grid>
      </Grid>
      <Grid item xs={5.5} sx={{ boxShadow:"4px 4px 4px rgba(0,0,0,0.25)", border: "3px solid #111" }}>
        <Grid sx={{ ml: "16px"}}><h3>외운 쫑알단어</h3></Grid>
        {doneList(right)}
      </Grid>
    </Grid>
  );
}