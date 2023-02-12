import React, { useState, useEffect } from "react";

import Tabmenu from "components/GlobalComponents/Tabmenu";
import RankingList from "components/GlobalComponents/RankingList";
import RankingTableList from "components/Community/RankingTableList";
import Box from "@mui/material/Box";

function Ranking({ location, history }) {
  const TabMenus = [
    { name: "성실", route: "/community/ranking/sincerity", color: "#FFD704" },
    { name: "따봉", route: "/community/ranking/thumbs", color: "#3C90F2" },
    { name: "수다", route: "/community/ranking/chat", color: "#67C73A" },
    { name: "인기", route: "/community/ranking/popularity", color: "#DDDDDD" },
  ];
  const [type, setType] = useState(1);


  // location.pathname: 현재 url
  // 이중에 없으면 404
  useEffect(() => {
    if (location.pathname === "/community/ranking/sincerity") {
      setType(1);
    } else if (location.pathname === "/community/ranking/thumbs") {
      setType(2);
    } else if (location.pathname === "/community/ranking/chat") {
      setType(3);
    } else if (location.pathname === "/community/ranking/popularity") {
      setType(4);
    } else {
      history.push("/NotFound");
    }
  }, [location.pathname]);

  return (
    <>
      <h2>알맹이 랭킹</h2>

      <Box sx={{ py: "64px" }}>
        <RankingList width={150} />
      </Box>

      <Tabmenu Items={TabMenus} Location={location.pathname} />

      {/* 각 랭킹 리스트 */}
      <Box sx={{ border: "3px solid #111111" }} padding="48px 112px" mb="32px">
        <RankingTableList type={type} />
      </Box>
    </>
  );
}

export default Ranking;
