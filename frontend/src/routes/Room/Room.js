/** @jsxImportSource @emotion/react */

function Room({match}) {
  const {session} = match.params.session;
    alert('session' + {session})
    return(
      <div></div>
    )
    // if(window.location.href.includes("location")){
    //   const url1 = "http://i8a506.p.ssafy.io:3028/frontend/src/Room/View.html?session=" + {session};
    //   return (
    //     <iframe
    //     src={url1}
    //     id="roomOpenviduID"
    //     title="Frame test"
    //     // remove these styles later
    //     frameBorder='0' 
    //     style={{ minHeight: '100vh', width: '100vw', border: '0px', display: 'block' }}
    //   />
    // );
    // }
    // else{
    //   const url2 = "http://localhost:5500/src/Room/View.html?session=" + {session};
    //   return (
    //     <iframe
    //     src={url2}
    //     id="roomOpenviduID"
    //     title="Frame test"
    //     // remove these styles later
    //     frameBorder='0'
    //     style={{ minHeight: '100vh', width: '100vw', border: '0px', display: 'block' }}
    //   />
    // );

    // }
  
  }
  
export default Room;
