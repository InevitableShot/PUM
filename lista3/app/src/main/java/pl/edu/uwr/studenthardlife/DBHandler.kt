package pl.edu.uwr.studenthardlife

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    private companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "listDBKotlin.db"
        private const val TABLE_KOTLIN = "KotlinTable"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_POINT = "point"
        private const val COLUMN_OPIS = "opis"
        private const val COLUMN_LISTNUMBER = "list_number"
        private const val COLUMN_TYPE = "type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val studentsTable =
            "CREATE TABLE $TABLE_KOTLIN(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_POINT INTEGER," +
                    "$COLUMN_OPIS TEXT," +
                    "$COLUMN_LISTNUMBER INTEGER," +
                    "$COLUMN_TYPE INTEGER)"
        db?.execSQL(studentsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_KOTLIN")
        onCreate(db)
    }

    fun addElement(element: TableList) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_POINT, element.point)
        contentValues.put(COLUMN_OPIS, element.opis)
        contentValues.put(COLUMN_LISTNUMBER, element.list_number)
        contentValues.put(COLUMN_TYPE, element.type)

        db.insert(TABLE_KOTLIN, null, contentValues)

        db.close()
    }

    fun deleteElement(element: Int) {
        val db = this.writableDatabase

        db.delete(
            TABLE_KOTLIN,
            "$COLUMN_ID=${element}",
            null
        )
        db.close()
    }

    fun updateElement(id: Int, opis: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_OPIS, opis)


        db.update(
            TABLE_KOTLIN,
            contentValues,
            "$COLUMN_ID=$id",
            null
        )

        db.close()
    }


    fun getElementList(num: Int): List<TableList> {
        val listEnd: MutableList<TableList> = ArrayList()

        val db = this.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_KOTLIN WHERE $COLUMN_LISTNUMBER=$num AND $COLUMN_TYPE=0",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                listEnd.add(
                    TableList(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                    )
                )
            } while (cursor.moveToNext())
        }

        db.close()
        cursor.close()
        return listEnd
    }

    fun getElementList1(ID: Int): List<String> {
        val listEnd: MutableList<String> = mutableListOf()

        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_KOTLIN WHERE $COLUMN_ID=$ID", null)

        if (cursor.moveToFirst()) {
            do {
                listEnd.add(cursor.getString(2))
            } while (cursor.moveToNext())
        }

        db.close()
        cursor.close()
        return listEnd
    }

    fun getElementList2(num: Int): List<TableList> {
        val listEnd: MutableList<TableList> = ArrayList()

        val db = this.readableDatabase
        try {
            val cursor = db.rawQuery(
                "SELECT * FROM $TABLE_KOTLIN WHERE $COLUMN_LISTNUMBER=$num AND $COLUMN_TYPE=1",
                null
            )

            if (cursor.moveToFirst()) {
                do {
                    listEnd.add(
                        TableList(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getInt(4)
                        )
                    )
                } while (cursor.moveToNext())
            }
            db.close()
            cursor.close()
        } catch (e: SQLiteException) {
            e.printStackTrace()
            return emptyList()
        }
        return if (listEnd.isEmpty()) {
            listOf(TableList(0, 0, "none", 0, 0))
        } else {
            listEnd
        }
    }

    fun addToGallery(singleItem: TableList): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_OPIS, singleItem.opis)
        contentValues.put(COLUMN_LISTNUMBER, singleItem.list_number)
        contentValues.put(COLUMN_TYPE, singleItem.type)

        val result = db.insert(TABLE_KOTLIN, null, contentValues)
        db.close()
        return result
    }
}