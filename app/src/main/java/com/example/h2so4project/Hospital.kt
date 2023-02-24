package com.example.h2so4project

data class Hospital (var name : String ?=null,
                     var address : String ?= null,
                     var state: String ?= null,
                     var district : String ?= null,
                     var uid : String?= null,
                     var General : Int? = null,
                     var Private : Int? = null,
                     var Dengue : Int? = null,
                     var available :String? = null,
                     var charges : String? = null,
                     var username : String? = null,
                     var email : String? = null,
                     var specialization : String? = null,
                     var profile : String? = null,
                     var location_link : String? = null)