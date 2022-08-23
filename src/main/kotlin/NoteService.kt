class NoteService: CrudService<Note>{
    private var notes = emptyArray<Note>()

    var comments = emptyArray<Comment>()

    private var noteId: Int = 0

    override fun add(note: Note): Note {
        val size = notes.size
        noteId += notes.hashCode()
        notes += note.copy(id = size + 1)
        return notes.last()
    }

    override fun createComment(note: Note, comment: Comment): Comment {
        val size = comments.size
        comments += comment.copy(noteId = note.id, ownerId = size + 1)
        return comments.last()
    }

    override fun delete(note: Note): Boolean {
        var flag = false
        for ((index, newNote) in notes.withIndex())
            if (newNote.id == note.id) {
                if(!note.deleteNote) {
                    notes[index] = note.copy(deleteNote = true)
                    flag = true
                    for ((index, note) in comments.withIndex())
                        if (newNote.id == note.noteId) {
                            comments[index] = note.copy(deleteComment = true)
                        }
                }
            }
        return flag
    }

    override fun deleteComment(note: Note, comment: Comment): Boolean {
        var flag = false

        for ((index, comment) in comments.withIndex())
            if (note.id == comment.noteId && comment.ownerId == comment.noteId) {
                comments[index] = comment.copy(deleteComment = true)
                flag = true
            }

        return flag
    }

    override fun edit(elem: Note, message: String): Note {
        var flagElem : Note = elem
        for ((index, oneElem) in notes.withIndex())
            if (elem.id == oneElem.id) {
                notes[index] = oneElem.copy(text = message)
                flagElem = oneElem.copy(text = message)
            }
        return flagElem
    }

    override fun editComment(elem: Note, comment: Comment, message: String): Comment {
        var flagComments : Comment = comment
        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && oneElem.noteId == comment.ownerId) {
                comments[index] = oneElem.copy(text = message)
                flagComments = comments[index]
            }
        return flagComments
    }

    override fun get(userId: Int): Array<Note> {
        var notesGetArray = emptyArray<Note>()
        for (oneElem in notes)
            if (userId == oneElem.ownerId && !oneElem.deleteNote) {
                notesGetArray += oneElem
            }
        return notesGetArray
    }

    override fun getById(elemId: Int): Note {
        return notes[elemId]
    }

    override fun getComments(elem: Note): Array<Comment> {
        var commentsGetArray = emptyArray<Comment>()
        for (oneElem in comments)
            if (elem.id == oneElem.noteId && !oneElem.deleteComment) {
                commentsGetArray += oneElem
            }
        return commentsGetArray
    }

    override fun restoreComment(elem: Note, comment: Comment): Comment {
        var flagComment = comment

        for ((index, oneElem) in comments.withIndex())
            if (elem.id == oneElem.noteId && oneElem.deleteComment) {
                comments[index] = oneElem.copy(deleteComment = false)
                flagComment = comments[index]
            }
        return flagComment
    }

}
