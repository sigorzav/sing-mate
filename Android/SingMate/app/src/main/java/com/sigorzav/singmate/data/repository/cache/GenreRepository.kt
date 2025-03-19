package com.sigorzav.singmate.data.repository.cache

import com.sigorzav.singmate.data.remote.common.CommonAPI
import com.sigorzav.singmate.model.CommonCode
import com.sigorzav.singmate.util.HttpStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * 장르 조회 (캐싱)
 */
class GenreRepository @Inject constructor(private val commonAPI: CommonAPI){

    private var _cachedGenres = MutableStateFlow<List<CommonCode>>(emptyList())
    val cachedGenres: StateFlow<List<CommonCode>> = _cachedGenres.asStateFlow()

    fun fetchGenres(): Flow<List<CommonCode>> = flow {
        if (_cachedGenres.value.isEmpty()) {
            val response = commonAPI.searchCode("SONG", "GENRE")
            if (response.statusCode == HttpStatus.OK.code) {
                _cachedGenres.value = response.data
            }
        }
    }
}