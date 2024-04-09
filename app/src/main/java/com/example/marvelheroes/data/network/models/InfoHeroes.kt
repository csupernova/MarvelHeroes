package com.example.marvelheroes.data.network.models

import com.squareup.moshi.Json


data class InfoHeroes(
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    @field:Json(name = "attributionHTML")
    val attributionHtml: String,
    val etag: String,
    val data: Data,
)

data class Data(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>,
)

data class Result(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    @field:Json(name = "resourceURI")
    val resourceUri: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val urls: List<Url>,
)

data class Thumbnail(
    val path: String,
    val extension: String,
)

data class Comics(
    val available: Long,
    @field:Json(name = "collectionURI")
    val collectionUri: String,
    val items: List<Item>,
    val returned: Long,
)

data class Item(
    @field:Json(name = "resourceURI")
    val resourceUri: String,
    val name: String,
)

data class Series(
    val available: Long,
    @field:Json(name = "collectionURI")
    val collectionUri: String,
    val items: List<Item2>,
    val returned: Long,
)

data class Item2(
    @field:Json(name = "resourceURI")
    val resourceUri: String,
    val name: String,
)

data class Stories(
    val available: Long,
    @field:Json(name = "collectionURI")
    val collectionUri: String,
    val items: List<Item3>,
    val returned: Long,
)

data class Item3(
    @field:Json(name = "resourceURI")
    val resourceUri: String,
    val name: String,
    val type: String,
)

data class Events(
    val available: Long,
    @field:Json(name = "collectionURI")
    val collectionUri: String,
    val items: List<Item4>,
    val returned: Long,
)

data class Item4(
    @field:Json(name = "resourceURI")
    val resourceUri: String,
    val name: String,
)

data class Url(
    val type: String,
    val url: String,
)
