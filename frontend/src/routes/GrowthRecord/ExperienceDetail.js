import { React, useEffect } from "react";

import ExperienceIndicator from "components/GrowthRecord/ExperienceIndicator";
import ExperienceTable from "components/GrowthRecord/ExperienceTable";
import { Box, Typography } from "@mui/material";
function ExperienceDetail() {
  return (
    <Box sx={{ border: "3px solid #111", p: "2rem", boxSizing: "border-box" }}>
      <Typography variant="h5" sx={{ mb: "4rem" }}>
        경험치
      </Typography>
      <ExperienceIndicator />
      <ExperienceTable />
    </Box>
  );
}

export default ExperienceDetail;
