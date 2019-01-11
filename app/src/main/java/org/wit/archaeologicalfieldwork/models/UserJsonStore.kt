package org.wit.archaeologicalfieldwork.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.helpers.exists
import org.wit.archaeologicalfieldwork.helpers.read
import org.wit.archaeologicalfieldwork.helpers.write
import java.util.*

private val JSON_FILE = "users.json"
private val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
private val listType = object : TypeToken<ArrayList<UserModel>>() {}.type


private fun generateRandomId(): Long {
    return Random().nextLong()
}

class UserJsonStore : UserStore, AnkoLogger {


    val context: Context
    var users = mutableListOf<UserModel>()
    var user = UserModel()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    suspend override fun findAll(): List<UserModel> {
        return users
    }

    suspend override fun findById(id:Long) : UserModel? {
        val foundSite: UserModel? = users.find { it.id == id }
        return foundSite
    }

    suspend override fun create(user: UserModel) {
        user.id = generateRandomId()
        if(findAll().find { p -> p.email == user.email } != null){
            throw UserExistsException()
        }
        users.add(user)
        serialize()
    }

    suspend override fun update(user: UserModel) {
        val userList = findAll() as ArrayList<UserModel>
        var foundUser: UserModel? = userList.find { p -> p.id == user.id }
        if (foundUser != null) {
            foundUser.password  = user.password
            foundUser.email = user.email
        }
        serialize()
    }

    suspend override fun delete(user: UserModel) {
        users.remove(user)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(users, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        users = Gson().fromJson(jsonString, listType)
    }

    override fun clear() {
        users.clear()
    }

    suspend  override fun findByEmail(email: String): UserModel? {
        val foundSite: UserModel? = users.find { it.email == email }
        return foundSite

    }

    override  fun getCurrentUser(): UserModel {

        return user
    }

    override fun setCurrentUser(user: UserModel) {

        this.user= user
    }
}