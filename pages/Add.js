import Head from "next/head";
import React, { useEffect, useState } from "react";
import { getDatabase, ref, onValue, set } from "firebase/database";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {
  ref as ref_storage,
  uploadBytes,
  listAll,
  getStorage,
  getDownloadURL,
} from "firebase/storage";

const Add = ({ user, uid }) => {
  const database = getDatabase();

  const [name, setName] = useState("");
  const [district, setDistrict] = useState("");
  const [stat, setStat] = useState("");
  const [address, setAddress] = useState("");
  const [image, setImage] = useState("");
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [specialization, setSpecialization] = useState("");
  const [imageuploaded, setImageuploaded] = useState(false);
  const [loc, setLoc] = useState("");

  const [wardtype, setWardtype] = useState("");
  const [beds, setBeds] = useState("");
  const [charges, setCharges] = useState("");
  const [available, setAvailable] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "name") {
      setName(value);
    }
    if (name === "district") {
      setDistrict(value);
    }
    if (name === "stat") {
      setStat(value);
    }
    if (name === "address") {
      setAddress(value);
    }
    if (name === "email") {
      setEmail(value);
    }
    if (name === "username") {
      setUsername(value);
    }
    if (name === "specialization") {
      setSpecialization(value);
    }
    if (name === "wardtype") {
      setWardtype(value);
    }
    if (name === "beds") {
      setBeds(value);
    }
    if (name === "charges") {
      setCharges(value);
    }
    if (name === "available") {
      setAvailable(value);
    }
    if (name === "loc") {
      setLoc(value);
    }
  };

  const handleImageChange = async (e) => {
    // console.log(e);
    // console.log(e.target.files[0]);
    await setImage(e.target.files[0]);
    // console.log(image);
  };

  const storage = getStorage();
  const [imageURL, setImageURL] = useState();

  const handleImageUpload = async (e) => {
    e.preventDefault();
    if (image) {
      const storageRef = ref_storage(storage, "/" + name);
      const upload = await uploadBytes(storageRef, image);
      const snapshot = await upload;
      const uri = await getDownloadURL(snapshot.ref);
      setImageURL(uri);
      if (imageuploaded) {
        toast.success("Image uploaded successfully", {
          position: "top-left",
          autoClose: 1000,
          hideProgressBar: true,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
      }
      // console.log(url);
      // console.log(imageURL);

      // console.log(imageList);
    } else {
      toast.warn("Please select an image", {
        position: "top-left",
        autoClose: 1500,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
      setImageuploaded(false);
    }
    // console.log(image.name);
    // console.log(storage);
  };

  useEffect(() => {
    setImageuploaded(true);
    // console.log(imageURL);
  }, [imageURL]);

  const [flag, setFlag] = useState(false);
  const handleHospitalSubmit = async (e) => {
    e.preventDefault();
    if (name && district && stat && address && imageuploaded && loc) {
      await setFlag(true);
      toast.success("Data updated successfully", {
        position: "top-left",
        autoClose: 1000,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
      set(ref(database, "Details/" + uid + "/hospital_details"), {
        name,
        district,
        state: stat,
        address,
        profile: imageURL,
        location_link: loc,
      });

      setName("");
      setAddress("");
      setDistrict("");
      setStat("");
      setFlag(false);
    } else {
      toast.warn("Please fill all the details", {
        position: "top-left",
        autoClose: 1500,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    }
  };

  const handleWardSubmit = (e) => {
    e.preventDefault();
    if (wardtype && beds && charges && available) {
      if (Number(available) <= Number(beds) && Number(available) >= 0) {
        set(ref(database, "Details/" + uid + "/ward_details/" + wardtype), {
          wardtype,
          beds,
          charges,
          available,
        });
        toast.success("Data added successfully", {
          position: "top-left",
          autoClose: 1000,
          hideProgressBar: true,
          closeOnClick: true,
          pauseOnHover: false,
          draggable: true,
          progress: undefined,
          theme: "light",
        });
        setWardtype("");
        setBeds("");
        setCharges("");
        setAvailable("");
      } else {
        toast.error(
          "Number of beds available can't exceed total number of beds",
          {
            position: "top-left",
            autoClose: 1500,
            hideProgressBar: true,
            closeOnClick: true,
            pauseOnHover: false,
            draggable: true,
            progress: undefined,
            theme: "light",
          }
        );
      }
    } else {
      toast.warn("Please fill all the details", {
        position: "top-left",
        autoClose: 1500,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    }
  };

  const handleDoctorSubmit = (e) => {
    e.preventDefault();
    if (email && username && specialization) {
      toast.success("Data updated successfully", {
        position: "top-left",
        autoClose: 1000,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
      set(
        ref(
          database,
          "Details/" + uid + "/doctor_details/" + email.split("@")[0]
        ),
        {
          email,
          username,
          specialization,
        }
      );
      console.log(email.split("@")[0], username, specialization);
      setEmail("");
      setUsername("");
      setSpecialization("");
    } else {
      toast.warn("Please fill all the details", {
        position: "top-left",
        autoClose: 1500,
        hideProgressBar: true,
        closeOnClick: true,
        pauseOnHover: false,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    }
  };

  useEffect(() => {
    const data = ref(database, "Details/" + uid + "/hospital_details");
    onValue(data, (snapshot) => {
      const data1 = snapshot.val();
      if (data1 !== null) {
        setAddress(data1.address);
        setDistrict(data1.district);
        setName(data1.name);
        setStat(data1.state);
        setLoc(data1.location_link);
        if (data1.profile) {
          setImageURL(data1.profile);
          setImageuploaded(true);
        } else {
          setImageuploaded(false);
        }
      }
    });
  }, [flag, uid]);

  return (
    <>
      <Head>
        <title>myHospital.com | Add details</title>
      </Head>
      {user && (
        <div className="container mx-auto my-9">
          <ToastContainer
            position="top-left"
            autoClose={5000}
            hideProgressBar={true}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="light"
          />
          <h1 className="text-center">Add/Update your details here</h1>
          <fieldset className="my-5">
            <legend>Hospital Details</legend>
            <div className="mx-auto flex">
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="name"
                    className="leading-7 text-sm text-gray-600"
                  >
                    Name
                  </label>
                  <input
                    onChange={handleChange}
                    value={name}
                    type="text"
                    id="name"
                    name="name"
                    required
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="district"
                    className="leading-7 text-sm text-gray-600"
                  >
                    District
                  </label>
                  <input
                    type="text"
                    onChange={handleChange}
                    value={district}
                    id="district"
                    name="district"
                    required
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2
                 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
            </div>
            <div className="px-2 w-full">
              <div className="mb-4">
                <label
                  htmlFor="address"
                  className="leading-7 text-sm text-gray-600"
                >
                  Address
                </label>
                <textarea
                  className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  name="address"
                  onChange={handleChange}
                  value={address}
                  id="address"
                  cols="30"
                  rows="2"
                  required
                ></textarea>
              </div>
            </div>
            <div className="mx-auto flex">
              <div className="px-2 w-1/2">
                <label
                  htmlFor="stat"
                  className="leading-7 text-sm text-gray-600"
                >
                  State
                </label>
                <input
                  type="text"
                  onChange={handleChange}
                  value={stat}
                  id="stat"
                  name="stat"
                  required
                  className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                />
              </div>
              <div className="px-2 w-1/2">
                <label
                  htmlFor="loc"
                  className="leading-7 text-sm text-gray-600"
                >
                  Location link
                </label>
                <input
                  type="text"
                  onChange={handleChange}
                  value={loc}
                  id="loc"
                  name="loc"
                  required
                  className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                />
              </div>
            </div>
            <div className="mx-auto flex">
              <div className="px-2 w-1/2 my-2">
                <label
                  htmlFor="image"
                  className="leading-7 text-sm text-gray-600"
                >
                  Image
                </label>
                <input
                  type="file"
                  onChange={handleImageChange}
                  id="image"
                  name="image"
                  required
                  className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                />
              </div>
              <div className="px-2 w-1/2 my-[40px]">
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleImageUpload}
                >
                  Upload image
                </button>
              </div>
            </div>
            <button
              type="button"
              className="btn btn-primary mx-[50%]"
              onClick={handleHospitalSubmit}
            >
              Update
            </button>
          </fieldset>

          <fieldset className="my-5">
            <legend>Ward Details</legend>
            <div className="mx-auto flex">
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="wardtype"
                    className="leading-7 text-sm text-gray-600"
                  >
                    Ward type
                  </label>
                  <input
                    onChange={handleChange}
                    value={wardtype}
                    type="text"
                    id="wardtype"
                    name="wardtype"
                    required
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="beds"
                    className="leading-7 text-sm text-gray-600"
                  >
                    Beds
                  </label>
                  <input
                    type="number"
                    value={beds}
                    required
                    onChange={handleChange}
                    id="beds"
                    name="beds"
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2
                 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
            </div>
            <div className="px-2 w-full">
              <div className="mb-4">
                <label
                  htmlFor="charges"
                  className="leading-7 text-sm text-gray-600"
                >
                  Bed Charge
                </label>
                <input
                  type="number"
                  value={charges}
                  id="charges"
                  onChange={handleChange}
                  name="charges"
                  className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2
                 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                />
              </div>
            </div>
            <div className="mx-auto flex">
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="available"
                    className="leading-7 text-sm text-gray-600"
                  >
                    Beds Available
                  </label>
                  <input
                    type="number"
                    onChange={handleChange}
                    value={available}
                    id="available"
                    name="available"
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
            </div>
            <button
              type="button"
              className="btn btn-primary mx-[50%]"
              onClick={handleWardSubmit}
            >
              Update
            </button>
          </fieldset>

          <fieldset className="my-5">
            <legend>Doctor Details</legend>
            <div className="mx-auto flex">
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="username"
                    className="leading-7 text-sm text-gray-600"
                  >
                    Name
                  </label>
                  <input
                    onChange={handleChange}
                    value={username}
                    type="text"
                    id="username"
                    name="username"
                    required
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
              <div className="px-2 w-1/2">
                <div className="mb-4">
                  <label
                    htmlFor="email"
                    className="leading-7 text-sm text-gray-600"
                  >
                    Email
                  </label>
                  <input
                    type="email"
                    onChange={handleChange}
                    value={email}
                    id="email"
                    name="email"
                    required
                    className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2
                 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                  />
                </div>
              </div>
            </div>
            <div className="px-2 w-full">
              <div className="mb-4">
                <label
                  htmlFor="specialization"
                  className="leading-7 text-sm text-gray-600"
                >
                  Specialization
                </label>
                <input
                  type="text"
                  value={specialization}
                  onChange={handleChange}
                  required
                  id="specialization"
                  name="specialization"
                  className="w-full bg-white rounded border border-gray-300 focus:border-indigo-500 focus:ring-2
                 focus:ring-indigo-200 text-base outline-none text-gray-700 py-1 px-3 leading-8 transition-colors duration-200 ease-in-out"
                />
              </div>
            </div>
            <button
              type="button"
              className="btn btn-primary mx-[50%]"
              onClick={handleDoctorSubmit}
            >
              Update
            </button>
          </fieldset>
        </div>
      )}
      {!user && (
        <div>
          <h2 className="text-center">
            Please login to add/update your details
          </h2>
        </div>
      )}
    </>
  );
};

export default Add;
