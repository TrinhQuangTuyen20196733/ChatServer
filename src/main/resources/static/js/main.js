'use strict';

var contactPage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var contactForm = document.querySelector('#contactForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;

var contactID = null;
var senderID = null;
var conversationID = null;

var receiverID = null;


var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];


function connect(event) {
    contactID = document.querySelector('#contactID').value.trim();
    senderID = contactID;
    receiverID = document.querySelector('#receiverID').value.trim();
    conversationID = document.querySelector('#conversationID').value.trim();

    if(contactID && conversationID) {
        contactPage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/urlSocket');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Đăng ký đến conversation/{conversationID}
    stompClient.subscribe("/conversation/"+ conversationID, onMessageReceived);

    // Xác định người gửi tin nhắn
    var Contact = {
        contactID: contactID
        // Các thuộc tính tiếp theo

    };

    // Gửi các thông báo khi đã tham gia cuộc hội thoại
    stompClient.send(
        "/app/join/" + conversationID,
        {},
        JSON.stringify(Contact)
    )

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Lỗi không thể kết nô WebSocket server!';
    connectingElement.style.color = 'red';
}


function send(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {

        var MessageRequest = {
            messageContent: messageContent,
            messageType: "TEXT",
            mediaLocation: null,
            status: null,
            creationTime: null,
            senderID: senderID,
            receiverID: receiverID,
        };

        stompClient.send(
        "/app/chat/" + conversationID,
        {},
        JSON.stringify(MessageRequest));

        messageInput.value = '';
    }

    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    // Hiển thị tin nhắn lên cuộc hội thoaại
    messageElement.classList.add('chat-message');

    // Kiểm tra sender
    if(message.senderID == senderID) {
        messageElement.style.float = 'right';
        messageElement.style.marginLeft = '100%';
        messageElement.style.whiteSpace = 'nowrap';
    }

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.senderID);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.senderID);

    messageElement.appendChild(avatarElement);

    var contactElement = document.createElement('span');
    var contactText = document.createTextNode(message.senderID);
    contactElement.appendChild(contactText);
    messageElement.appendChild(contactElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.messageContent);
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

contactForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', send, true)
