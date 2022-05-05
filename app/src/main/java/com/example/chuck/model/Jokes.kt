package com.example.chuck.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Jokes {
    @field:Json(name = "title")
    var title: String = ""

    @field:Json(name = "id")
    var id: Int = 0

    @field:Json(name = "imageUrl")
    var imageUrl: String = ""

    @field:Json(name = "ranking")
    var ranking: String = ""

    @field:Json(name = "fileURL")
    var fileURL: String = ""

    @field:Json(name = "websiteURL")
    var websiteURL: String = ""
}
