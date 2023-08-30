package com.application.dictionaryapp.networks

import android.annotation.SuppressLint
import android.util.Log
import com.application.dictionaryapp.helper.Resource
import retrofit2.Response
import javax.inject.Inject

abstract class BaseDataSource {
    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>) : Resource<T>{
        try {
            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    return Resource.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        }catch (e : Exception){
            return error(e.message ?: e.toString())
        }
    }

    @SuppressLint("LongLogTag")
    private fun <T> error(message: String) : Resource<T>{
        Log.e(TAG, "error: $message", )
        return Resource.Failure("Network call has failed for a following reason: $message")
    }

    companion object{
        private const val TAG = "BaseDataSource::class.java"
    }
}