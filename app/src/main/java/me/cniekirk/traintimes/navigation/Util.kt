package me.cniekirk.traintimes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

@Composable
fun <T> NavController.ObserveResult(key: String, onResult: (T?) -> Unit) {
    val pageResult = currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<T?>(key, null)?.collectAsState()

    pageResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(key)
    }
}