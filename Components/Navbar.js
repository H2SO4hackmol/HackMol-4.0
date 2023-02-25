import React, { useEffect } from "react";
import Link from "next/link";

const Navbar = ({ user, login, logout }) => {
  return (
    <>
      {/* <div
        className={`sticky top-0 bg-gray-500 z-10 flex flex-col md:flex-row md:justify-start justify-center items-center py-2 overflow-hidden`}
      >
        <style jsx>{`
        li:hover{
            color: black;
        `}</style>
        <div className="nav self-start">
          <Link
            href="/"
            style={{
              cursor: "pointer",
              textDecoration: "none",
              color: "white",
            }}
          >
            <ul className="items-center">
              <li>myHospital.com</li>
            </ul>
          </Link>
        </div>
        <div>
          <ul className="flex space-x-2 text-md items-center">
            <Link
              href="/Add"
              style={{
                cursor: "pointer",
                textDecoration: "none",
                color: "white",
              }}
            >
              <li className="ml-[10px]">Add details</li>
            </Link>
            <Link
              href="/Contact"
              style={{
                cursor: "pointer",
                textDecoration: "none",
                color: "white",
              }}
            >
              <li className="ml-[10px]">Contact Us</li>
            </Link>
          </ul>
        </div>
        <div className="flex absolute right-1 top-2 cursor-pointer items-center">
          {user && (
            <button
              className="bg-violet-500 text-white px-2 py-2 rounded-md text-sm mx-3 cursor-pointer"
              onClick={logout}
            >
              Logout
            </button>
          )}

          {!user && (
            <button
              className="bg-violet-500 text-white px-2 py-2 rounded-md text-sm mx-3 cursor-pointer"
              onClick={login}
            >
              Login
            </button>
          )}
        </div>
      </div> */}

      <nav className="navbar navbar-expand-sm navbar-dark bg-danger fixed-top">
        <Link
          href="/"
          className="navbar-brand"
          style={{ textDecoration: "none" }}
        >
          HETSO
        </Link>
        <span className="navbar-text">Customer's Health is our Aim</span>
        <button
          type="button"
          className="navbar-toggler"
          data-toggle="collapse"
          data-target="#myMenu"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="myMenu">
          <ul className="navbar-nav navi">
            <li className="nav-item text-white z-10" id="ol">
              <Link
                href="/"
                className="navbar-brand"
                style={{ textDecoration: "none" }}
              >
                Home
              </Link>
            </li>
            <li className="nav-item">
              <a href="#about">About</a>
            </li>
            <li className="nav-item">
              <a href="#Contact">Contact</a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};

export default Navbar;
