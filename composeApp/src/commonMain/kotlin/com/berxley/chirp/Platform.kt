package com.berxley.chirp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform