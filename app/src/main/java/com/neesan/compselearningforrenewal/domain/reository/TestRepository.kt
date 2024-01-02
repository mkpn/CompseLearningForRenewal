package com.neesan.compselearningforrenewal.domain.reository

import kotlinx.coroutines.delay

class TestRepository {

    suspend fun functionOne(): String {
        delay(2000)  // 2秒待機
        return "Result of Function One"
    }

    suspend fun functionTwo(): String {
        delay(2000)  // 2秒待機
        return "Result of Function Two"
    }

    suspend fun functionThree(): String {
        delay(2000)  // 2秒待機
        return "Result of Function Three"
    }
}