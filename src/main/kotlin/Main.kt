fun main(){

    val note = Note(1,1,"Exo", "D", 1,0,0,"http://zxc.com",false)
    val note1 = Note(2,2,"Exo", "D", 1,0,0,"http://zxc.com",false)
    val note2 = Note(3,3,"Exo", "D", 1,0,0,"http://zxc.com",false)

    val service = NoteService()

    service.add(note)
    service.add(note1)
    service.add(note2)

    val comment1t1 = Comment(1,1,1, "KISS")
    val comment2t1 = Comment(1,2,1, "MISS")
    val comment1t2 = Comment(2,1,1, "JISS")
    val comment2t2 = Comment(2,2,1, "PSS")

    service.createComment(note, comment1t1)
    service.createComment(note, comment2t1)
    service.createComment(note1, comment1t2)
    service.createComment(note1, comment2t2)

    service.deleteComment(note,comment1t1)

    println(service.getComments(note))
}

