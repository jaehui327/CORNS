import React from "react";
import Tabmenu from "../../components/GlobalComponents/Tabmenu";

function Indicators({ location }) {
  const TabMenus = [
    {
      name: "최근 일주일 일별 대화량",
      route: "/growthRecord/indicators/weeklyLiner",
      color: "#FFD704",
    },
    {
      name: "대화 주제 비율",
      route: "/growthRecord/indicators/subjectCircle",
      color: "#FFD704",
    },
    {
      name: "일일 경험치 획득량",
      route: "/growthRecord/indicators/dailyBar",
      color: "#FFD704",
    },
  ];

  return (
    <>
      <Tabmenu Items={TabMenus} Location={location.pathname} />
    </>
  );
}

export default Indicators;
