var OV;
var session;

var roomListId = "roomViewUser";

// serverurl
let serverUrl = "https://corns.co.kr:4463/";

// 칸이 차 있는지
var userArray = [false,false,false,false];


var mySessionId;
var myUserName;
var jRoomNo;
var userId;

/* OPENVIDU METHODS */

function joinSession() {

	mySessionId = document.getElementById("sessionId").value;
	myUserName = document.getElementById("userName").value;
	jRoomNo = document.getElementById("jRoomNo").value;
	userId = document.getElementById("userId").value;

	// $("#sessionId").val(session);
	// $("#userName").val(username);
	// $("#jRoomNo").val(jroomno);
	// $("#userId").val(userid);
	// 방 입장처리 한다.
	//intoRoom(connectionId, recordId, token)


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
				// console.log(data.content[i]);
				// console.log(data.content[i].stream);
			}	
		}
	});

	// On every new Stream received...
	session.on('streamCreated', event => {
		// Subscribe to the Stream to receive it. HTML video will be appended to element with roomListId id
			var roomno;
			if(userArray[0] === false){
				roomno = "roomViewUser2";
				userArray[0] = true;
			}
			else if(userArray[1] === false){
				roomno = "roomViewUser3";
				userArray[1] = true;
			}
			else if(userArray[2] === false){
				roomno = "roomViewUser4";
				userArray[2] = true;
			}

			var subscriber = session.subscribe(event.stream, roomno);
	
			// When the HTML video has been appended to DOM...
			subscriber.on('videoElementCreated', event => {
				// 누군가 새로운 사람이 들어오면 여기에 뜬다.
				// 2번부터 띄워준다.
				appendUserData(event.element, subscriber.stream.connection, roomno);
			});
		
		
	});

	// On every Stream destroyed...
	session.on('streamDestroyed', event => {
		// Delete the HTML element with the user's nickname. HTML videos are automatically removed from DOM
		removeUserData(event.stream.connection);
	});

	// On every asynchronous exception...
	session.on('exception', (exception) => {
		console.warn(exception);
	});


	// --- 4) Connect to the session with a valid user token ---
	// Get a token from the OpenVidu deployment
	getToken(mySessionId).then(token => {

		console.log("token : " + token.token);
		token = token.token;

		// First param is the token got from the OpenVidu deployment. Second param can be retrieved by every user on event
		// 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
		session.connect(token, { clientData: myUserName })
			.then(() => {

				// --- 5) Set page layout for active call ---
				// 내가 무조건 1번에 뜨게 한다.
				// --- 6) Get your own camera stream with the desired properties ---
				var publisher = OV.initPublisher("roomViewUser1", {
					audioSource: undefined, // The source of audio. If undefined default microphone
					videoSource: undefined, // The source of video. If undefined default webcam
					publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
					publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
					// resolution: videoWidth + 'x' + videoHeight,//'480x250',  // The resolution of your video
					resolution: '640x360',
					frameRate: 30,			// The frame rate of your video
					insertMode: 'APPEND',	// How the video is inserted in the target element roomListId
					mirror: true       	// Whether to mirror your local video or not
				});

				// --- 7) Specify the actions when events take place in our publisher ---

				// When our HTML video has been added to DOM...
				publisher.on('videoElementCreated', function (event) {
					console.log("my");
					console.log(event.stream);
					// initMainVideo(event.element, myUserName);
					appendUserData(event.element, myUserName, "");
					// event.element['muted'] = true;
				});

				// --- 8) Publish your stream ---

				session.publish(publisher);

			})
			.catch(error => {
				console.log('There was an error connecting to the session:', error.code, error.message);
			});
	});

}

// 방 입장 처리
function intoRoom(connectionId, recordId, token){
	$.ajax({
		type : "POST",            // HTTP method type(GET, POST) 형식이다.
		url : serverUrl + "/room/user",      // 컨트롤러에서 대기중인 URL 주소이다.
		data : JSON.stringify({
			"roomNo": jRoomNo,
			"roomUser": {
			   "connectionId": connectionId, 
			   "recordId": recordId,
			   "token": token
			},
			"userId": userId
		  }),
		success : function(res){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
			/*

			
			
			*/
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
			alert("통신 실패.")
		}
	});
}

function appendUserData(videoElement, connection, roomno) {
	
	if(roomno !== ""){var dataNode = document.createElement('div');
		dataNode.className = "data-node";
		dataNode.id = "data-" + connection.connectionId;
		dataNode.roomno = "__" + roomno;
		videoElement.parentNode.insertBefore(dataNode, videoElement.nextSibling);
	}
}


function removeUserData(connection) {

	// 부모의 아이디 찾아서 false 처리
	var dataNode = document.getElementById("data-" + connection.connectionId);
	var pid = dataNode.parentElement.id;

	if(pid === "roomViewUser2"){
		userArray[0] = false;
	}
	else if(pid === "roomViewUser3"){
		userArray[1] = false;
	}
	else if(pid === "roomViewUser4"){
		userArray[2] = false;
	}
}


var OPENVIDU_URL= "https://corns.co.kr:4430/";

var OPENVIDU_SECRET = btoa("OPENVIDUAPP:a506w6w");
function getToken(mySessionId) {
	return createToken(mySessionId);
}

function createSession(sessionId) {
	console.log(sessionId)
	// return new Promise((resolve, reject) => {
	// 	$.ajax({
	// 		type: "POST",
	// 		// url: APPLICATION_SERVER_URL + "api/sessions",
	// 		url: OPENVIDU_URL + "openvidu/api/sessions",
	// 		data: JSON.stringify({ customSessionId: sessionId }),
	// 		headers: { 	"Content-Type": "application/json",
	// 					 "Authorization" : "Basic " + OPENVIDU_SECRET,
	// 					 "Access-Control-Allow-Origin" : "*"},
	// 		success: response => resolve(response), // The sessionId
	// 		error: (error) => reject(error)
	// 	});
	// });
}

function createToken(sessionId) {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'POST',
			url: OPENVIDU_URL + 'openvidu/api/sessions/' + sessionId + '/connection',
			data: JSON.stringify({}),
			headers: { "Content-Type": "application/json",
						"Authorization" : "Basic " + OPENVIDU_SECRET,
						"Access-Control-Allow-Origin" : "*"},
			success: (response) => resolve(response), // The token
			error: (error) => reject(error)
		});
	});
}


function getConnection(sessionId){
	return new Promise((resolve, reject) => {
		$.ajax({
			url: OPENVIDU_URL + "openvidu/api/sessions/"+ sessionId + "/connection/",
			type: "GET",
			headers: { 	"Authorization" : "Basic " + OPENVIDU_SECRET,
						"Access-Control-Allow-Origin" : "*"},
			success: (data) => { resolve(data); },
			error: (data) => { reject(data); }
		});
	});
	
}

function getConnections(sessionId){
	return new Promise((resolve, reject) => {
		$.ajax({
			url: OPENVIDU_URL + "openvidu/api/sessions/"+ sessionId + "/connection/",
			type: "GET",
			headers: { 	"Authorization" : "Basic " + OPENVIDU_SECRET,
						"Access-Control-Allow-Origin" : "*"},
			success: (data) => { resolve(data); },
			error: (data) => { reject(data); }
		});
	});
	
}