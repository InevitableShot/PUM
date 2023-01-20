package pl.edu.uwr.studenthardlife

data class TableList(val point: Int, val opis: String, val list_number: Int, val type: Int) {
    var id: Int = 0

    constructor(id: Int, point: Int, opis: String, list_number: Int, type: Int) : this(
        point,
        opis,
        list_number,
        type
    ) {
        this.id = id
    }
}
