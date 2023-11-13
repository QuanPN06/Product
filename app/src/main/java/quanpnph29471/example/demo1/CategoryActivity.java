package quanpnph29471.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import quanpnph29471.example.demo1.Adapter.CatAdapter;
import quanpnph29471.example.demo1.DAO.CategoryDAO;
import quanpnph29471.example.demo1.Model.Category;

public class CategoryActivity extends AppCompatActivity {
    TextInputLayout ed_nameCat;
    Button btn_add,btn_update, btn_del;
    ListView lv_cat;

    CatAdapter catAdapter;
    CategoryDAO categoryDAO;

    Category currentCat;
    List<Category> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ed_nameCat = findViewById(R.id.ed_nameCat);
        btn_add= findViewById(R.id.btnAdd);
        btn_del =findViewById(R.id.btnDel);
        btn_update = findViewById(R.id.btnUpdate);
        lv_cat = findViewById(R.id.lv_cat);

        categoryDAO = new CategoryDAO(this);
        list = categoryDAO.getList();
        catAdapter = new CatAdapter(list,this);

        lv_cat.setAdapter(catAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_cat = ed_nameCat.getEditText().getText().toString();
                Category objCat = new Category(name_cat);
                long check = categoryDAO.insert(objCat);
                if(check>0){
                    list.clear();
                    list.addAll(categoryDAO.getList());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(CategoryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(CategoryActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

//        lv_cat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                currentCat = list.get(i);
//                ed_nameCat.getEditText().setText(currentCat.getName());
//                return true;
//            }
//        });

        lv_cat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentCat = list.get(i);
                ed_nameCat.getEditText().setText(currentCat.getName());
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_cat = ed_nameCat.getEditText().getText().toString();
                currentCat.setName(name_cat);
                int check = categoryDAO.update(currentCat);
                if(check>0){
                    list.clear();
                    list.addAll(categoryDAO.getList());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(CategoryActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(CategoryActivity.this, "Update thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = categoryDAO.delete(currentCat);
                if(check>0){
                    list.clear();
                    list.addAll(categoryDAO.getList());
                    catAdapter.notifyDataSetChanged();
                    Toast.makeText(CategoryActivity.this, "Xoa thành công", Toast.LENGTH_SHORT).show();
                    ed_nameCat.getEditText().setText("");
                }else Toast.makeText(CategoryActivity.this, "Xoa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}