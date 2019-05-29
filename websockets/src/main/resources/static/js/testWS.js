var stompClient = null;

function connect1() {
    var socket = new SockJS('/web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/position.' + type, function (greeting) {
            alert(greeting);
        });
        /*stompClient.subscribe('/topic/greetings', function (greeting) {
            alert(greeting)
            //showGreeting(JSON.parse(greeting.body).content);
        });*/
    });
}

function connect2() {
    var socket = new SockJS('/web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            alert(greeting)
            //showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect1() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName2() {
    stompClient.send("/app/hello", {}, JSON.stringify("hola"));
}

function sendName1() {
    stompClient.send('/app/position/' + type, {}, JSON.stringify("hola"));
}
function setConnected(isConnected){
    alert(isConnected);
}