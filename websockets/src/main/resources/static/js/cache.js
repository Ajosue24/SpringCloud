
var stompClient = null;

function connect() {
    var socket = new SockJS('/web-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected to cache: ' + frame);
        stompClient.subscribe('/subscription/cache', function (response) {
            updateCache(response.body);
        });
    });
    $("#connect").attr("disabled", true);
    $("#disconnect").attr("disabled", false);
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    $("#disconnect").attr("disabled", true);
    $("#connect").attr("disabled", false);
}


function sendCache() {
    stompClient.send("/vytra/cache", {}, JSON.stringify({
            'name': $("#name").val(),
            'function': $("#function").val()
        }
    ));
}

function updateCache(message) {
    $("#msg").append("<tr><td>" + message + "</td></tr>");
}


$(function () {
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendCache();
    });
});