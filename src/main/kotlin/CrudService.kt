interface CrudService<T>{
    fun add(elem: T) : T
    fun createComment(elem: Note, comment: Comment) : Comment
    fun delete(elem: T) : Boolean
    fun deleteComment(elem: T, comment: Comment) : Boolean
    fun edit(elem: T, message: String): T
    fun editComment(elem: T, comment: Comment, text: String): Comment
    fun get(userId: Int) : Array<T>
    fun getById(elemId: Int) : T
    fun getComments(elem: T) : Array<Comment>
    fun restoreComment(elem: T, comment: Comment) : Comment
}