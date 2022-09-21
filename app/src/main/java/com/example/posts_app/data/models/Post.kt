package com.example.posts_app.data.models

data class Post(
    private val userId: Int,
    private val id: Int,
    private val title: String,
    private val body: String,
    private val isFavourite: Boolean
)

class PostProvider(){
    companion object{
        val postList = listOf(
                Post(1,1,"Lorem ipsum pecadum mortiferus", "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",false),
                Post(1,2,"Lorem ipsum pecadum mortiferus", "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",false),
                Post(2,3,"Lorem ipsum pecadum mortiferus", "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",false),
                Post(2,4,"Lorem ipsum pecadum mortiferus", "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",false),
                Post(3,5,"Lorem ipsum pecadum mortiferus", "KLSKlklklskslksdssddssdsdsdsddsdssdsddsddssdssddsssdsdddssds",false),
        )
    }
}
