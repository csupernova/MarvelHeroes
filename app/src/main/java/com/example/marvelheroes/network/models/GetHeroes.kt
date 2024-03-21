package com.example.marvelheroes.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHeroes(
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    @SerialName("attributionHTML")
    val attributionHtml: String,
    val etag: String,
    val data: Data,
)

@Serializable
data class Data(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>,
)

@Serializable
data class Result(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    @SerialName("resourceURI")
    val resourceUri: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val urls: List<Url>,
)

@Serializable
data class Thumbnail(
    val path: String,
    val extension: String,
)

@Serializable
data class Comics(
    val available: Long,
    @SerialName("collectionURI")
    val collectionUri: String,
    val items: List<Item>,
    val returned: Long,
)

@Serializable
data class Item(
    @SerialName("resourceURI")
    val resourceUri: String,
    val name: String,
)

@Serializable
data class Series(
    val available: Long,
    @SerialName("collectionURI")
    val collectionUri: String,
    val items: List<Item2>,
    val returned: Long,
)

@Serializable
data class Item2(
    @SerialName("resourceURI")
    val resourceUri: String,
    val name: String,
)

@Serializable
data class Stories(
    val available: Long,
    @SerialName("collectionURI")
    val collectionUri: String,
    val items: List<Item3>,
    val returned: Long,
)

@Serializable
data class Item3(
    @SerialName("resourceURI")
    val resourceUri: String,
    val name: String,
    val type: String,
)

@Serializable
data class Events(
    val available: Long,
    @SerialName("collectionURI")
    val collectionUri: String,
    val items: List<Item4>,
    val returned: Long,
)

@Serializable
data class Item4(
    @SerialName("resourceURI")
    val resourceUri: String,
    val name: String,
)

@Serializable
data class Url(
    val type: String,
    val url: String,
)
