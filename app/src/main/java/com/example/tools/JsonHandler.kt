package com.example.tools

import org.json.JSONException
import org.json.JSONObject

class JsonHandler {
    private var jsonObject: JSONObject = JSONObject()

    var json: String
        get() = jsonObject.toString()
        @Throws(JSONException::class)
        set(jsonString) {
            jsonObject = JSONObject(jsonString)
        }

    fun getValue(key: String?): String? {
        return jsonObject.optString(key, null)
    }

    @Throws(JSONException::class)
    fun setValue(key: String, value: String?) {
        jsonObject.put(key, value)
    }

    @Throws(JSONException::class)
    fun setJSON(jsonString: String) {
        jsonObject = JSONObject(jsonString)
    }

    fun toMap(): MutableMap<String, String?> {
        val map = linkedMapOf<String, String?>()
        val keys = jsonObject.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            map[key] = jsonObject.optString(key)
        }
        return map
    }
}