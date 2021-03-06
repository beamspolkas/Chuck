package com.example.chuck.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Prefs(context: Context) {

    companion object {
        private const val PREFS_FILENAME = "shared_prefs_name"

        private const val KEY_MY_STRING = "my_string"
        private const val KEY_MY_BOOLEAN = "my_boolean"
        private const val KEY_MY_ARRAY = "string_array"
        private const val KEY_MY_OBJECT = "my_object"
        private const val KEY_USER_DATA = "user_data"
    }
    private val gson = Gson()
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var myString: String
        get() = sharedPrefs.getString(KEY_MY_STRING, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_MY_STRING, value) }

    var myBoolean: Boolean
        get() = sharedPrefs.getBoolean(KEY_MY_BOOLEAN, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_MY_BOOLEAN, value) }

    var myStringArray: Array<String>
        get() = sharedPrefs.getStringSet(KEY_MY_ARRAY, emptySet())?.toTypedArray()?: emptyArray()
        set(value) = sharedPrefs.edit { putStringSet(KEY_MY_ARRAY, value.toSet()) }
    
    var myObject: MyObject?
        get() {
            val jsonString = sharedPrefs.getString(KEY_USER_DATA, null) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<MyObject>() {}.type)
        }
        set(value) = sharedPrefs.edit { putString(KEY_MY_OBJECT, gson.toJson(value)) }
}
data class MyObject(val name: String)