package quanpnph29471.example.demo1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import quanpnph29471.example.demo1.DbHelper;
import quanpnph29471.example.demo1.Model.Category;

public class CategoryDAO {
    DbHelper dbHelper ;
    SQLiteDatabase db;

    public CategoryDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Ham lay danh sach the loai
    public ArrayList<Category> getList (){
        ArrayList<Category> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_cat",null);
        if(c.getCount()>0){
            c.moveToFirst();
            do {
                Category obj = new Category();
                obj.setId(c.getInt(0));
                obj.setName(c.getString(1));

                //bo cho doi tuong vao danh sach
                list.add(obj);
            }while (c.moveToNext());
        }
        return list ;
    }

    public long insert (Category obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        return db.insert("tb_cat",null,values);
    }

    public int update (Category obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        return db.update("tb_cat",values,"id=?",new String[]{String.valueOf(obj.getId())});
    }

    public int delete (Category obj){
        return db.delete("tb_cat","id=?",new String[]{String.valueOf(obj.getId())});
    }


}
