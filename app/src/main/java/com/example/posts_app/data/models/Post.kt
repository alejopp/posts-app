package com.example.posts_app.data.models

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val isFavourite: Boolean
)

class PostProvider() {
    companion object {
        val postList = listOf(
            Post(
                1,
                1,
                "Lorem ipsum pecadum mortiferus",
                "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",
                false
            ),
            Post(
                1,
                2,
                "Lorem ipsum pecadum mortiferus",
                "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",
                false
            ),
            Post(
                2,
                3,
                "Lorem ipsum pecadum mortiferus",
                "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",
                false
            ),
            Post(
                2,
                4,
                "Lorem ipsum pecadum mortiferus",
                "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",
                false
            ),
            Post(
                3,
                5,
                "Lorem ipsum pecadum mortiferus",
                "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",
                true
            ),
        )
    }
}
