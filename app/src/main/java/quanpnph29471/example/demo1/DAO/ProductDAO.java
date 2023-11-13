package quanpnph29471.example.demo1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import quanpnph29471.example.demo1.DbHelper;
import quanpnph29471.example.demo1.Model.Category;
import quanpnph29471.example.demo1.Model.Product;

public class ProductDAO {
    DbHelper dbHelper ;
    SQLiteDatabase db;

    public ProductDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Ham lay danh sach the loai
    public ArrayList<Product> getList (){
        ArrayList<Product> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tb_product",null);
        if(c.getCount()>0){
            c.moveToFirst();
            do {
                Product obj = new Product();
                obj.setId(c.getInt(0));
                obj.setName(c.getString(1));
                obj.setPrice(c.getInt(2));
                obj.setId_cat(c.getInt(3));

                //bo cho doi tuong vao danh sach
                list.add(obj);
            }while (c.moveToNext());
        }
        return list ;
    }

    public long insert (Product obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        values.put("price",obj.getPrice());
        values.put("id_cat",obj.getId_cat());
        return db.insert("tb_product",null,values);
    }

    public int update (Product obj){
        ContentValues values = new ContentValues();
        values.put("name",obj.getName());
        values.put("price",obj.getPrice());
        values.put("id_cat",obj.getId_cat());
        return db.update("tb_product",values,"id=?",new String[]{String.valueOf(obj.getId())});
    }

    public int delete (Product obj){
        return db.delete("tb_product","id=?",new String[]{String.valueOf(obj.getId())});
    }

    public void getCategory (int id){
        Cursor c = db.rawQuery("Select id , id_cat ,tb_cat.name " +
                "from tb_product " +
                "INNER JOIN tb_cat ON tb_product.id_cat = tb_cat.id;",null);
    }

}
