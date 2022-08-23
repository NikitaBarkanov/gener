data class Comment(
    val noteId: Int,
    val ownerId: Int,
    val replyTo: Int,
    val text: String,
    val deleteComment: Boolean = false
)