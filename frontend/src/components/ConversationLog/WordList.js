import { Button, Checkbox, List, ListItem, ListItemIcon, ListItemText, Paper } from "@mui/material";
import React from "react";

import WordItem from "./WordItem";

function WordList({ words, handletoggle }) {
  // const { wordSq, wordEng, wordKor } = word;

  return (
    <Paper sx={{ width: 570, height: 600, overflow: "auto" }}>
      <List dense component="div" role="list">
        <h3>외운 쫑알 단어</h3>
        {words.map((word, index) => {
          return <WordItem key={index} word={word} handletoggle={handletoggle} />;
        })}
      </List>
    </Paper>
  );
}

export default WordList;
