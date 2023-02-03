/** @jsxImportSource @emotion/react */

function Room({match}) {
    const {session} = match.params;
    let url = '';
    if(window.location.href.includes("location")){
      url = "http://i8a506.p.ssafy.io:3028/frontend/src/Room/View.html?session=" + session;
    }
    else{
      //로컬
      url = "http://localhost:5500/src/Room/View.html?session=" + session;
    }
    // 
    if(window.location.href.includes("location")){
      return (
        <iframe
        src={url} 
        id="roomOpenviduID"
        title="Frame test"
        // remove these styles later
        frameborder='0' 
        style={{ minHeight: '100vh', width: '100vw', border: '0px', display: 'block' }}
      />
    );
    }
    else{

      return (
        <iframe
        src={url}
        id="roomOpenviduID"
        title="Frame test"
        // remove these styles later
        frameborder='0'
        style={{ minHeight: '100vh', width: '100vw', border: '0px', display: 'block' }}
      />
    );

    }  }
  
export default Room;
