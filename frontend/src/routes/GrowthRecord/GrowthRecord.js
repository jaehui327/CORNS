import {Route, Switch} from 'react-router-dom'
import Navbar from "../../components/Navbar";
import Sidebar from "../../components/Sidebar";
import MyProfile from './MyProfile'
import ExperienceDetail from './ExperienceDetail';
import Indicators from './indicators';

function GrowthRecord() {
  const SidebarItems = [
    { name: "내정보", route: "/growthRecord" },
    { name: "경험치", route: "/growthRecord/experienceDetail" },
    { name: "지표보기", route: "/growthRecord/indicators" },
  ];

  return (
    <>
      <Navbar />
      <hr />

      <Sidebar Items={SidebarItems}/>

      <hr />
      <Switch>
        <Route exact path="/growthRecord" component={MyProfile} />
        <Route exact path="/growthRecord/experienceDetail" component={ExperienceDetail} />
        <Route exact path="/growthRecord/indicators" component={Indicators} />
      </Switch>

    </>
  );
}

export default GrowthRecord;
