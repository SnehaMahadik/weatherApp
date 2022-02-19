package com.sneha.myweatherapp.modals

import com.google.gson.annotations.SerializedName

data class List (

  @SerializedName("dt"         ) var dt         : Int?               = null,
  @SerializedName("main"       ) var main       : Main?              = Main(),
  @SerializedName("weather"    ) var weather    : ArrayList<Weather>? = arrayListOf(),
  @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
  @SerializedName("visibility" ) var visibility : Int?               = null,
  @SerializedName("dt_txt"     ) var dtTxt      : String

)