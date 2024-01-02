package com.neesan.compselearningforrenewal.presentation.awaitTasks

import androidx.lifecycle.ViewModel
import com.neesan.compselearningforrenewal.domain.reository.TestRepository

class AwaitTasksViewModel(val testRepository: TestRepository) : ViewModel() {
    suspend fun functionOne() {
        testRepository.functionOne()
    }

}