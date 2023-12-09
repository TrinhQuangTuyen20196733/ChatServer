let localVideo;
let remoteVideo;
let webRtcPeer;

$(document).ready(function () {
    localVideo = document.getElementById('localVideo');
    remoteVideo = document.getElementById('remoteVideo');
});

function startCall() {
    const sessionId = prompt('Enter your session ID:');
    if (!sessionId) return;

    const options = {
        localVideo: localVideo,
        remoteVideo: remoteVideo,
        onicecandidate: onIceCandidate,
    };

    webRtcPeer = kurentoUtils.WebRtcPeer.WebRtcPeerSendrecv(options, function (error) {
        if (error) return onError(error);

        this.generateOffer(onOffer);
    });

    $.post(`/api/videocall/startCall/${sessionId}`, function (sdpOffer) {
        webRtcPeer.processOffer(sdpOffer, onAnswer);
    });
}

function onOffer(error, offerSdp) {
    if (error) return onError(error);

    $.post('/api/videocall/startCall', { sdpOffer: offerSdp }, function (sdpAnswer) {
        webRtcPeer.processAnswer(sdpAnswer);
    });
}

function onAnswer(error, answerSdp) {
    if (error) return onError(error);
}

function onIceCandidate(candidate) {
    $.post('/api/videocall/icecandidate', { candidate: candidate }, function () {});
}

function stopCall() {
    webRtcPeer.dispose();
    webRtcPeer = null;

    const sessionId = prompt('Enter the session ID to stop the call:');
    if (!sessionId) return;

    $.post(`/api/videocall/stopCall/${sessionId}`, function (response) {
        alert(response);
    });
}

function onError(error) {
    console.error(error);
    alert('An error occurred. Check the console for details.');
}
