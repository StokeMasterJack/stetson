package ss.app1.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class User(var id: Int, var login: String, var url: String)


/*
slow things: don't do it on the main thread, do it async
can't update ui from the non-ui thread

coroutines

    suspend functions

    val job:Job = launch{
    }
    val deferred: Deferred<User> = async{
    }

    coroutine scopes:  structured concur

 */
interface GitHubService {

    @GET("users")
    suspend fun fetchUsers(): List<User>

}

object Retro {

    private fun mkRetrofit(baseUrl: String): Retrofit {
        val httpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun mkGitHubService(): GitHubService {
        val r = mkRetrofit("https://api.github.com/")
        return r.create(GitHubService::class.java)
    }

    fun foo(): LiveData<UiState<List<User>>> {
        val ss = mkGitHubService()
        return liveData {
            try {
                val returnedUsers: List<User> = ss.fetchUsers()
                val ui = UiState.Success(returnedUsers)
                emit(ui)
            } catch (e: Exception) {
                emit(UiState.Error(e))
            }
        }
    }

    suspend fun fetchUsers(): UiState<List<User>> {
        val s = mkGitHubService()
        return try {
            val users = s.fetchUsers()
            UiState.Success(data = users)
        } catch (e: Exception) {
            UiState.Error(exception = e)
        }
    }


}

sealed class UiState<out T> {

    object NotStarted : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isFinal: Boolean get() = isSuccess || isError
    val isNotStarted: Boolean get() = this is NotStarted


    val friendlyString: String
        get() {
            return when (this) {
                is NotStarted -> "NotStarted"
                is Loading -> "Loading"
                is Success -> data.toString()
                is Error -> "Error: $exception"
            }
        }


}

enum class GhAction {
    Fetch, Clear
}

//object GhLive {
//
//    private val action: MutableLiveData<GhAction> = MutableLiveData(GhAction.Clear)
//
//    fun update(a: GhAction) {
//        action.postValue(a)
//    }
//
//    val users: LiveData<UiState<List<User>>> = action.switchMap {
//        val a = it ?: GhAction.Clear
//        liveData {
//            when (a) {
//                GhAction.Clear -> {
//                    val list = emptyList<User>()
//                    val ui = UiState.Success(list)
//                    emit(ui)
//                }
//                GhAction.Fetch -> {
//                    try {
//                        val returnedUsers: List<User> = Retro.fetchUsers()
//                        val ui = UiState.Success(returnedUsers)
//                        emit(ui)
//                    } catch (e: Exception) {
//                        emit(UiState.Error(e))
//                    }
//                }
//            }
//        }
//    }
//
//}
