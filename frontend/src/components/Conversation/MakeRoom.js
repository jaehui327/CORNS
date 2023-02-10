// import React from 'react';
import axios from "axios";


const MakeRoom = async (subject, type, time, count= false) => {
    // alert(subject);
    // alert(type);
    // alert(time);
    // alert(count);
    var OPENVIDU_URL= "https://corns.co.kr:4430/";
    var OPENVIDU_SECRET = btoa("OPENVIDUAPP:a506w6w");

    const headers = {
        "Authorization": `Bearer ` + OPENVIDU_SECRET//,
        // "Access-Control-Allow-Credentials": true,
    }

    axios.get(OPENVIDU_URL + `/openvidu/api/sessions`, {
        headers:headers
    })
    .then((Response)=>{console.log(Response.data)})
    .catch((Error)=>{console.log(Error)});

}

export default MakeRoom;