import * as React from "react";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import WordList from "./WordList";

function not(a, b) {
  return a.filter((value) => b.indexOf(value) === -1);
}

function intersection(a, b) {
  return a.filter((value) => b.indexOf(value) !== -1);
}

export default function TransferList() {
  const [checked, setChecked] = React.useState([]);
  const [left, setLeft] = React.useState([5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17]);
  const [right, setRight] = React.useState([0, 1, 2, 3, 4]);

  const leftChecked = intersection(checked, left);
  const rightChecked = intersection(checked, right);

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

  const handleToggle = (word) => {
    const currentIndex = checked.indexOf(word);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(word);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };

  return (
    <Grid container spacing={2} justifyContent="center" alignItems="center">
      <Grid item>
        <WordList words={left} handletoggle={handleToggle} />
      </Grid>
      <Grid item>
        <Grid container direction="column" alignItems="center">
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
      <Grid item>
        <WordList words={right} handleToggle={handleToggle} />
      </Grid>
    </Grid>
  );
}
