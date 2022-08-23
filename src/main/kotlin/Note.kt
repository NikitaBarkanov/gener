data class Note(
    var id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var comments: Int = 0,
    val readComments: Int,
    val viewUrl: String,
    val deleteNote: Boolean = false
)