/** @jsxImportSource @emotion/react */

function Room() {
    window.sessionStorage.setItem("userName", JSON.stringify("gdgd"));
    return (
        <iframe
        src="http://localhost:5500/src/Room/View.html"
        id="unique-id"
        title="Frame test"
        // remove these styles later
        style={{ minHeight: `100vh`, width: '100vw' }}
      />
    );
  }
  
export default Room;
