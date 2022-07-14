package com.sudo.weather.data.models.responses.weather


import org.json.JSONObject



data class Test(
    val Unit: String = "",
    val UnitType: Int = 0,
    val Value: Double = 0.0
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Test? {

            jsonObject?.run {
                return Test(
                    optString("Unit"),
                    optInt("UnitType"),
                    optDouble("Value")
                )
            }
            return null
        }
    }
}