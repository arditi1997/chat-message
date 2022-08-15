'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var roomForm = document.querySelector('#roomForm');
var roomPage = document.querySelector('#room-page');
var roomName = document.querySelector('#roomName');
var connectingElement = document.querySelector('.connecting');
var stompClient = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    var usernameAuth = document.getElementById("usernameAuth").innerText;
    console.log(usernameAuth, "valie")
    if (usernameAuth) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected(options) {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
    var roomId = "2"
    stompClient.send("/app/chat.addUser", {}, JSON.stringify(roomId))

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}
function createRoom(event) {
    console.log(roomName.value.trim())
        var room = {
            name: roomName.value.trim(),
        };
        stompClient.send("/app/chat.addRoom", {}, JSON.stringify(room));
    event.preventDefault();
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    var usernameAuth = document.getElementById("usernameAuth").innerText;
    if (messageContent && stompClient) {
        var message = {
            user: {
                userName: usernameAuth
            },
            content: messageInput.value,
            messageType: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');
    var usernameAuth = document.getElementById("usernameAuth").innerText;
    console.log(usernameAuth, "valie")
    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.user.userName + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.message.user.userName + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.user.userName);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
roomForm.addEventListener('submit', createRoom, true)
messageForm.addEventListener('submit', sendMessage, true)
