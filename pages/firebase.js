// Import the functions you need from the SDKs you need
// import * as firebase from "firebase";
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCpbwWEpk1GvCIL8g5ECgXTEagtjqZImWI",
  authDomain: "hospital-details-99294.firebaseapp.com",
  databaseURL: "https://hospital-details-99294-default-rtdb.firebaseio.com",
  projectId: "hospital-details-99294",
  storageBucket: "hospital-details-99294.appspot.com",
  messagingSenderId: "206016585505",
  appId: "1:206016585505:web:cc9bc1c01f8b7bf787a860",
  measurementId: "G-CK87DLYYYM",
};

// Initialize Firebase
// const auth = getAuth(app);
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
// export default firebase;
