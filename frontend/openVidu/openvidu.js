var OV;
var session;


/* OPENVIDU METHODS */

function joinSession() {

	var mySessionId = document.getElementById("sessionId").value;
	var myUserName = document.getElementById("userName").value;

	// --- 1) Get an OpenVidu object ---

	OV = new OpenVidu();

	// --- 2) Init a session ---

	session = OV.initSession();

	// --- 3) Specify the actions when events take place in the session ---
	// 현재 세션에 연결된 사용자들 띄우기
	// let connections = 
	getConnections(mySessionId).then(data => {
		if(data.numberOfElements > 0){
			for(let i = 0 ; i < data.numberOfElements ; i++){
				console.log(data.content[i]);
			}	
		}
	});


	// if(!connections){
	// 	alert("잘못된 접근입니다.");
	// }
	// else{
	// 	for(var i = 0 ; i < connections.length ; i++){
	// 		alert(connections[i])
	// 	}
	// }

	// On every new Stream received...
	session.on('streamCreated', event => {
		
		
	});

}

var APPLICATION_SERVER_URL = "http://localhost:5000/";

var OPENVIDU_URL= "http://localhost:4443/";
var OPENVIDU_SECRET = "MY_SECRET";

function getToken(mySessionId) {
	return createSession(mySessionId).then(sessionId => createToken(sessionId));
}

function createSession(sessionId) {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: "POST",
			// url: APPLICATION_SERVER_URL + "api/sessions",
			url: OPENVIDU_URL + "/openvidu/api/sessionsz",
			data: JSON.stringify({ customSessionId: sessionId }),
			headers: { "Content-Type": "application/json",
						 "Authorization" : "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU"},
			success: response => resolve(response), // The sessionId
			error: (error) => reject(error)
		});
	});
}

function createToken(sessionId) {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'POST',
			url: APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
			data: JSON.stringify({}),
			headers: { "Content-Type": "application/json" },
			success: (response) => resolve(response), // The token
			error: (error) => reject(error)
		});
	});
}

function getConnections(sessionId){
	//http://localhost:4443/openvidu/api/sessions/ses_UUafG5URGM/connection/

	return new Promise((resolve, reject) => {
		$.ajax({
			url: OPENVIDU_URL + "openvidu/api/sessions/"+ sessionId + "/connection/",
			type: "GET",
			headers: { 	"Authorization" : "Basic T1BFTlZJRFVBUFA6TVlfU0VDUkVU",
						"Access-Control-Allow-Origin" : "*"},
			success: (data) => { resolve(data); },
			error: (data) => { reject(data); }
		});
	});
	
}