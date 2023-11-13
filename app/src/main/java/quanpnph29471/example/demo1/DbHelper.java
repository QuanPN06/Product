package quanpnph29471.example.demo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data";
    private static final int DB_VERSION = 1;


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String TABLE_CAT_CREATE =
            " CREATE TABLE IF NOT EXISTS " +
            " tb_cat (" +
            "    id   INTEGER PRIMARY KEY AUTOINCREMENT," +
            "    name TEXT    NOT NULL" +
            "                 UNIQUE" +
            ");";

    public static final String TABLE_PRODUCT_CREATE =
            " CREATE TABLE IF NOT EXISTS" +
                    " tb_product (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT NOT NULL," +
                    " price INTEGER DEFAULT (0) NOT NULL," +
                    "id_cat INTEGER NOT NULL CONSTRAINT fk_pro_cat REFERENCES tb_cat (id) );";

    String insert_cat  = "INSERT INTO tb_cat (name) VALUES ('Tivi') , ('MayTinh'),('Tu lanh') ";

    String insert_pro  = "INSERT INTO tb_product (name,price,id_cat) VALUES ('TV Samsung',100,1)," +
            "('TV LG',200,1) ,('May tinh 1',1099,2)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CAT_CREATE);
        db.execSQL(TABLE_PRODUCT_CREATE);
        db.execSQL(insert_cat);
        db.execSQL(insert_pro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i<i1){
            db.execSQL("DROP TABLE IF EXISTS tb_product");//xoa bang product truoc vi la bang ben nhieu
            db.execSQL("DROP TABLE IF EXISTS tb_cat");
        }
    }
}
