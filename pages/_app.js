import "@/styles/globals.css";
import "bootstrap/dist/css/bootstrap.css";
import Navbar from "../Components/Navbar";
import { auth } from "./firebase";
import { signInWithPopup, GoogleAuthProvider } from "firebase/auth";
import { useAuthState } from "react-firebase-hooks/auth";
import { useEffect, useState } from "react";
// import { getAuth } from "firebase/auth";

export default function App({ Component, pageProps }) {
  const googleAuth = new GoogleAuthProvider();
  const login = async () => {
    try {
      const res = await signInWithPopup(auth, googleAuth);
      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };
  const logout = async () => {
    await auth.signOut();
  };

  const [user, setUser] = useAuthState(auth);
  const [uid, setUid] = useState(null);
  useEffect(() => {
    // setUser(user);
    // console.log(user.uid);
    if (user) {
      setUid(user.uid);
      // console.log(uid);
    }
  }, [user]);

  return (
    <>
      <Navbar user={user} login={login} logout={logout} />
      <Component {...pageProps} user={user} uid={uid ? uid : null} />
    </>
  );
}
