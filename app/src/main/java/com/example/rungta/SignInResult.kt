package com.example.rungta

data class SignInResult(
    val data: com.example.rungta.UserData?,
    val errorMessage:String?,
)
data class UserData(
    val userId:String,
    val username:String,
    val ppUrl:String?,
    val email:String
)