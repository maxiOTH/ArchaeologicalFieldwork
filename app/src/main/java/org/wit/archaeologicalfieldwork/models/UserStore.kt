package org.wit.archaeologicalfieldwork.models

import java.lang.Exception

interface UserStore {
    suspend   fun findAll(): List<UserModel>
    suspend  fun findById(id:Long) : UserModel?
    suspend   fun create(user: UserModel)
    suspend  fun update(user: UserModel)
    suspend fun delete(user: UserModel)
    suspend fun findByEmail(email:String) :UserModel?
    fun getCurrentUser() : UserModel
    fun setCurrentUser(user: UserModel)
    fun clear()
}
class UserExistsException : Exception(){

}
