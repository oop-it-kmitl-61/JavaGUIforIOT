const functions = require('firebase-functions');
var moment = require('moment-timezone');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
// const timestamp = (req, res) => res.status(200).send(new Date());


exports.tempHistoryStore = functions.database.ref('/Sensor/Data/Temperature')
    .onWrite((snapshot, context) => {
      // Grab the current value of what was written to the Realtime Database.
      const temp = snapshot.after.val();
      var d = moment().tz("Asia/Bangkok").format('YYYYMMDD HH:mm:ss');
      var date = d.slice(0, 8);
      var time = d.slice(9, 14);
      var sec = d.slice(15);

      var refer = snapshot.after.ref.parent.parent.child("tempHistory").child(date)
      return refer.child(time).child(sec).set(temp);
    });

exports.smokeHistoryStore = functions.database.ref('/Sensor/Data/Smoke')
    .onWrite((snapshot, context) => {
      // Grab the current value of what was written to the Realtime Database.
      const temp = snapshot.after.val();
      var d = moment().tz("Asia/Bangkok").format('YYYYMMDD HH:mm:ss');
      var date = d.slice(0, 8);
      var time = d.slice(9, 14);
      var sec = d.slice(15);

      var refer = snapshot.after.ref.parent.parent.child("smokeHistory").child(date)
      return refer.child(time).child(sec).set(temp);
    });