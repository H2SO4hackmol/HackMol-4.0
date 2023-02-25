import React from "react";

const Footer = () => {
  return (
    <div>
      <footer className="container-fluid bg-dark mt-5">
        <div className="container">
          <div className="row py-3">
            <div className="col-md-6">
              <span className="pr-2 text-light">Follow Us :</span>
              <a href="#" className="pr-2 fi-color">
                <i
                  className="fab fa-1x fa-twitter"
                  style={{ color: "#dc3545", marginRight: "3px" }}
                >
                  {" "}
                </i>
              </a>
              <a href="#" className="pr-2 fi-color">
                <i
                  className="fab fa-1x fa-facebook"
                  style={{ color: "#dc3545", marginRight: 3 + "px" }}
                >
                  {" "}
                </i>
              </a>
              <a href="#" className="pr-2 fi-color">
                <i
                  className="fab fa-1x fa-youtube"
                  style={{ color: "#dc3545", marginRight: 3 + "px" }}
                >
                  {" "}
                </i>
              </a>
              <a href="#" className="pr-2 fi-color">
                <i
                  className="fas fa-1x fa-rss"
                  style={{ color: "#dc3545", marginRight: 3 + "px" }}
                >
                  {" "}
                </i>
              </a>
            </div>
            <div className="col-md-6">
              <small className="text-light">
                Designed by Team H2SO4 &copy; 2023
              </small>
              <small className="ml-2">Visit Again</small>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default Footer;
