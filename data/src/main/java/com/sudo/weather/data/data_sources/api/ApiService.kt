package com.sudo.weather.data.data_sources.api

interface ApiService {
    suspend fun <T> get(url: String, header: Map<String, Any>, refresh: Boolean = false): T
    suspend fun <T> post(url: String,  header: Map<String, Any>, body: T, refresh: Boolean = true): T
    suspend fun <T> put(url: String,  header: Map<String, Any>, body: T, refresh: Boolean = true): T
    suspend fun <T> patch(url: String,  header: Map<String, Any>, body: T, refresh: Boolean = true): T
    suspend fun <T> delete(url: String,  header: Map<String, Any>, refresh: Boolean = true): T
}