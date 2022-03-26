package com.example.kotlinproject.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

class Post {
    
    var name: String? = null
    var documentId: String? = null
    var title: String? = null
    var contents: String? = null

    @ServerTimestamp
    private val date: Date? = null

    constructor()

    constructor(name: String?, documentId: String?, title: String?, contents: String?) {
        this.name = name
        this.documentId = documentId
        this.title = title
        this.contents = contents
    }

    override fun toString(): String {
        return "Post(name=$name, documentId=$documentId, title=$title, contents=$contents, date=$date)"
    }


}