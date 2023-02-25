import React, { useEffect } from "react";
import Link from "next/link";

const Navbar = ({ user, login, logout }) => {
  return (
    <>
      <div
        className={`sticky top-0 bg-danger z-10 flex flex-col md:flex-row md:justify-start justify-center items-center py-2 overflow-hidden`}
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
              <li className="text-bold-2xl navbar-brand">HETSO</li>
            </ul>
          </Link>
        </div>
        <div>
          <ul className="flex space-x-2 text-md items-center">
            <Link
              href="/#about"
              style={{
                cursor: "pointer",
                textDecoration: "none",
                color: "white",
              }}
            >
              <li className="ml-[10px]">About Us</li>
            </Link>
            <Link
              href="/#Contact"
              style={{
                cursor: "pointer",
                textDecoration: "none",
                color: "white",
              }}
            >
              <li className="ml-[10px]">Contact Us</li>
            </Link>
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
      </div>

      {/* <nav
        className={`navbar navbar-expand-sm navbar-dark bg-danger fixed-top`}
      >
        <Link
          href="/"
          className={styles.navbarBrand}
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
          <ul className={`${styles.navi} navbar-nav`}>
            <Link
              href="/"
              // className={styles.navbarBrand}
              style={{ textDecoration: "none" }}
            >
              <li className={styles.navItem} id="ol">
                Home
              </li>
            </Link>
            <Link
              href="#about"
              className={styles.navbarBrand}
              style={{ textDecoration: "none" }}
            >
              <li className={styles.navItem}>About</li>
            </Link>
            <Link
              href="#Contact"
              className={styles.navbarBrand}
              style={{ textDecoration: "none" }}
            >
              <li className={styles.navItem}>Contact</li>
            </Link>
          </ul>
        </div>
      </nav> */}
    </>
  );
};

export default Navbar;
