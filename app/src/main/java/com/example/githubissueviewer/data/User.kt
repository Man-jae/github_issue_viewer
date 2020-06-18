package com.example.githubissueviewer.data

data class User(
    var userName: String,
    var userImage: String
) {
    companion object {
        fun convert(user: UserResponse): User {
            return User(
                userName = user.userName,
                userImage = user.userImage
            )
        }
    }
}