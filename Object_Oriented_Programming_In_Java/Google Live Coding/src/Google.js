
'use strict';

const functions = require('firebase-functions'); // Cloud Functions for Firebase library

const DialogflowApp = require('actions-on-google').DialogflowApp; // Google Assistant helper library

const actionMap = new Map();

exports.dialogflowFirebaseFulfillment= functions.https.onRequest((request, response) => {

    console.log('Dialogflow Request headers: ' + JSON.stringify(request.headers));

console.log('Dialogflow Request body: ' + JSON.stringify(request.body));

const app = new DialogflowApp({request, response});

const actionMap = new Map();

actionMap.set('input.lucky_number', luckyNumberIntent);

function luckyNumberIntent() {

    const color = app.getArgument('color');

    app.tell(`Your lucky number is ${color.length}`)

}

app.handleRequest(actionMap);

});