import React from 'react';
import Tabmenu from '../../components/Tabmenu';


function Ranking() {
  const TabMenus = [
    { name: "성실", route: "/community" },
    { name: "따봉", route: "/community" },
    { name: "수다", route: "/community" },
    { name: "인기", route: "/community" },
  ];
  
  
  return (
    <>
      <Tabmenu Items={TabMenus}/>
    </>
  );

}

export default Ranking;