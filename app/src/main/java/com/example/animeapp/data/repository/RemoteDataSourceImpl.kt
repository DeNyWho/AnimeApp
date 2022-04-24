package com.example.animeapp.data.repository

//import com.example.animeapp.data.local.dao.AnimeDao
//import com.example.animeapp.data.remote.api.AnimeApi
//import com.example.animeapp.data.remote.models.user.User
//import com.example.animeapp.util.Result
//import com.example.animeapp.util.SessionManager
//import com.example.animeapp.util.isNetworkConnected
//import javax.inject.Inject
//
//class RemoteDataSourceImpl @Inject constructor (
//    private val animeAPI: AnimeApi,
//    val animeDao: AnimeDao,
//    private val sessionManager: SessionManager
//): AnimeRepo {
//
//    override suspend fun createUser(user: User): Result<String> {
//        return try {
//            if (!isNetworkConnected(sessionManager.context)) {
//                Result.Error<String>("No Internet Connection!")
//            }
//
//            val result = animeAPI.createAccount(user)
//
//            if (result.success) {
//                sessionManager.updateSession(result.message, user.userName ?: "", user.email)
//                Result.Success("User Created Successfully!")
//            } else {
//                Result.Error(result.message)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.Error(e.message ?: "Some Problem Occurred!")
//        }
//    }
//
//    override suspend fun login(user: User): Result<String> {
//        return try {
//            if (!isNetworkConnected(sessionManager.context)) {
//                Result.Error<String>("No Internet Connection!")
//            }
//
//            val result = animeAPI.login(user)
//            if (result.success) {
//                val userSql = getUserSqlInfo(email = user.email)
//                sessionManager.updateSession(result.message, user.userName ?: userSql.userName!!, user.email)
//                Result.Success("User Created Successfully!")
//            } else {
//                Result.Error(result.message)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.Error(e.message ?: "Some Problem Occurred")
//        }
//    }
//
//    override suspend fun getUserSqlInfo(email: String): User {
//        return animeAPI.getUserInfo(email)
//    }
//
//
//    override suspend fun getUser(): Result<User> {
//        return try {
//            val name = sessionManager.getCurrentUserName()
//            val email = sessionManager.getCurrentUserEmail()
//
//            if (name == null || email == null) {
//                Result.Error<User>("User not Logged In")
//            }
//            Result.Success(User(name, email!!, ""))
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.Error(e.message ?: "Some Problem Occurred")
//        }
//    }
//
//
//    override suspend fun logout(): Result<String> {
//        return try {
//            sessionManager.logout()
//            Result.Success("Logged out Successfully!")
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Result.Error(e.message ?: "Some Problem Occurred")
//        }
//    }
//}