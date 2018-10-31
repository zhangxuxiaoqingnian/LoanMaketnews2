package money.kuxuan.platform.moneyplatfrom.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Note;

import java.util.ArrayList;

import money.kuxuan.platform.moneyplatfrom.Bean.NoteEntity;

/**
 * Created by 小狼 on 2018/10/27.
 */

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 添加数据
     *
     * @param note
     *  /**
     *
     */
    public void create(NoteEntity note) {

        String sql = "insert into note(imgurl, title, content, url,price,interest,peoplenum,productid)values(?,?,?,?,?,?,?,?)";
        Object[] args = {note.imgurl,note.title,note.content,note.url,note.price,note.interest,note.peoplenum,note.productid};
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL(sql, args);
        db.close();
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void remove(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "delete from note where _id = ?";
        Object[] args = {id};
        db.execSQL(sql, args);
        db.close();
    }
    /**
     * 删除全部数据
     */
    public int deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int noteNum = db.delete("note", null, null);
        db.close();
        return noteNum;
    }

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    public  NoteEntity findById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from note where _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        NoteEntity note = null;
        if (cursor.moveToNext()) {
            note = new NoteEntity();

//            note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MetaData.NoteTable._ID)));
//            note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MetaData.NoteTable.TITLE)));
//            note.setContent(cursor.getString(cursor.getColumnIndexOrThrow(MetaData.NoteTable.CONTENT)));
//            note.setCreateDate(cursor.getString(cursor.getColumnIndexOrThrow(MetaData.NoteTable.CREATE_DATE)));
//            note.setUpdateDate(cursor.getString(cursor.getColumnIndexOrThrow(MetaData.NoteTable.UPDATE_DATE)));
//        }
            cursor.close();
            db.close();

        }
            return note;
    }

        /**
         * 查询所有
         *
         * @return
         */
    public ArrayList<NoteEntity> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from note";
        Cursor cursor = db.rawQuery(sql,null);


        ArrayList<NoteEntity> notes = new ArrayList<>();
        NoteEntity note = null;
        while (cursor.moveToNext()) {
            note = new NoteEntity();

            note._id=cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            note.imgurl=cursor.getString(cursor.getColumnIndexOrThrow("imgurl"));
            note.title=cursor.getString(cursor.getColumnIndexOrThrow("title"));
            note.content=cursor.getString(cursor.getColumnIndexOrThrow("content"));
            note.url=cursor.getString(cursor.getColumnIndexOrThrow("url"));
            note.price=cursor.getString(cursor.getColumnIndexOrThrow("price"));
            note.interest=cursor.getString(cursor.getColumnIndexOrThrow("interest"));
            note.peoplenum=cursor.getString(cursor.getColumnIndexOrThrow("peoplenum"));
            note.productid=cursor.getString(cursor.getColumnIndexOrThrow("productid"));
            notes.add(note);
        }
        cursor.close();
        db.close();
        return notes;
    }

        /**
         * 分页查询
         *
         * @param limit 默认查询的数量
         * @param skip 跳过的行数
         * @return
         */
        public ArrayList<NoteEntity> findLimit(int limit, int skip){
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "select * from note order by _id desc limit ? offset ?";
            String[] strs = new String[]{String.valueOf(limit), String.valueOf(skip)};
            Cursor cursor = db.rawQuery(sql, strs);


            ArrayList<NoteEntity> notes = new ArrayList<>();
            NoteEntity note = null;
            while (cursor.moveToNext()) {
                note = new NoteEntity();
/**
 * "CREATE TABLE note(_id integer primary key autoincrement,"+
 "imgurl text,title text, content text,url text,price text,interest text, peoplenum text, productid text)";

 */
                note._id=cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                note.imgurl=cursor.getString(cursor.getColumnIndexOrThrow("imgurl"));
                note.title=cursor.getString(cursor.getColumnIndexOrThrow("title"));
                note.content=cursor.getString(cursor.getColumnIndexOrThrow("content"));
                note.url=cursor.getString(cursor.getColumnIndexOrThrow("url"));
                note.price=cursor.getString(cursor.getColumnIndexOrThrow("price"));
                note.interest=cursor.getString(cursor.getColumnIndexOrThrow("interest"));
                note.peoplenum=cursor.getString(cursor.getColumnIndexOrThrow("peoplenum"));
                note.productid=cursor.getString(cursor.getColumnIndexOrThrow("productid"));

                notes.add(note);
            }
            cursor.close();
            db.close();
            return notes;
        }

}
