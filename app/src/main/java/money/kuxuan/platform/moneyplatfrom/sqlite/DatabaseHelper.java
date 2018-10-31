package money.kuxuan.platform.moneyplatfrom.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 小狼 on 2018/10/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //数据库名字
    private static final String DB_NAME = "note.db";

    //本版号
    private static final int VERSION = 1;

    //创建表
    private static final  String CREATE_TABLE_NOTE = "CREATE TABLE note(_id integer primary key autoincrement,"+
            "imgurl text,title text, content text,url text,price text,interest text, peoplenum text, productid text)";

    //删除表
    private static final String DROP_TABLE_NOTE = "drop table if exists note";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE_NOTE);
        db.execSQL(CREATE_TABLE_NOTE);
    }
}
