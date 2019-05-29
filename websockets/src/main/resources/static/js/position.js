var stompClient = null;
var stompGeneralClient = null;

var userId = Math.random() * (999 - 1) + 1;
var type = 'driver'

function connect() {
    var socket = new SockJS('/web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected to position: ' + frame);
        stompClient.subscribe('/topic/position.' + type + '.' + userId, function (response) {
            updateAdministration(response.body);
        });
    });
    $("#connect").attr("disabled", true);
    $("#disconnect").attr("disabled", false);
}


function connectGeneral() {
    var socket = new SockJS('/web-socket');
    stompGeneralClient = Stomp.over(socket);
    stompGeneralClient.connect({}, function (frame) {
        console.log('Connected to general position: ' + frame);
        stompGeneralClient.subscribe('/topic/general-position.' + type, function (response) {
            updateGeneral(response.body);
        });
    });
    $("#connectGeneral").attr("disabled", true);
    $("#disconnectGeneral").attr("disabled", false);
}


function disconnectGeneral() {
    if (stompGeneralClient !== null) {
        stompGeneralClient.disconnect();
    }
    $("#disconnectGeneral").attr("disabled", true);
    $("#connectGeneral").attr("disabled", false);
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    $("#disconnect").attr("disabled", true);
    $("#connect").attr("disabled", false);
}


function sendPosition() {
    stompClient.send('/app/position.' + type + '.' + userId, {}, JSON.stringify({
            'latitude': $("#latitude").val(),
            'longitude': $("#longitude").val()
        }
    ));
}

function updateAdministration(message) {
    $("#msg").append("<tr><td>" + message + "</td></tr>");
}

function updateGeneral(message) {
    $("#general-msg").append("<tr><td>" + message + "</td></tr>");
}


$(function () {
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendPosition();
    });
    $("#connectGeneral").click(function () {
        connectGeneral();
    });
    $("#disconnectGeneral").click(function () {
        disconnectGeneral();
    });
});