package quanpnph29471.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import quanpnph29471.example.demo1.Adapter.CatAdapter;
import quanpnph29471.example.demo1.Adapter.ProductAdapter;
import quanpnph29471.example.demo1.Adapter.SpinnerAdapter;
import quanpnph29471.example.demo1.DAO.CategoryDAO;
import quanpnph29471.example.demo1.DAO.ProductDAO;
import quanpnph29471.example.demo1.Model.Category;
import quanpnph29471.example.demo1.Model.Product;

public class ProductActivity extends AppCompatActivity {
    TextInputLayout ed_name,ed_price;
    Button btn_add,btn_update, btn_del;
    ListView lv_pro;

    ProductAdapter productAdapter;
    ProductDAO productDAO;

    Product objCurrent;
    List<Product> list;

    ArrayList<Category> listCat =  new ArrayList<>();
    CategoryDAO categoryDAO;
    SpinnerAdapter spinnerAdapter;
    Spinner spinner;
    int idSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ed_name = findViewById(R.id.ed_namePro);
        ed_price = findViewById(R.id.ed_Price);

        btn_add =findViewById(R.id.btnAdd);
        btn_del = findViewById(R.id.btnDel);
        btn_update = findViewById(R.id.btnUpdate);
        lv_pro = findViewById(R.id.lv_pro);

        spinner = findViewById(R.id.spinner);

        categoryDAO = new CategoryDAO(this);
        listCat = categoryDAO.getList();
        spinnerAdapter = new SpinnerAdapter(listCat,this);
        spinner.setAdapter(spinnerAdapter);

        productDAO = new ProductDAO(this);
        list = productDAO.getList();

        productAdapter = new ProductAdapter(list,this);

        lv_pro.setAdapter(productAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idSP = listCat.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_pro = ed_name.getEditText().getText().toString();
                int price = Integer.valueOf(ed_price.getEditText().getText().toString());

                Product objPro = new Product(name_pro,price,idSP);
                long check = productDAO.insert(objPro);
                if(check>0){
                    list.clear();
                    list.addAll(productDAO.getList());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(ProductActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        lv_pro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                objCurrent = list.get(i);
                ed_name.getEditText().setText(objCurrent.getName());
                ed_price.getEditText().setText(objCurrent.getPrice()+"");
//                ed_cat.getEditText().setText(objCurrent.getId_cat()+"");


                int value = -1;
                for(int a = 0;a<listCat.size();a++){
                    if(list.get(i).getId() == objCurrent.getId_cat()){
                        value=a;
                        break;
                    }
                }
                spinner.setSelection(value);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_pro = ed_name.getEditText().getText().toString();
                int price = Integer.valueOf(ed_price.getEditText().getText().toString());


                objCurrent.setName(name_pro);
                objCurrent.setPrice(price);
                objCurrent.setId_cat(idSP);

                int check = productDAO.update(objCurrent);
                if(check>0){
                    list.clear();
                    list.addAll(productDAO.getList());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(ProductActivity.this, "Update thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = productDAO.delete(objCurrent);
                if(check>0){
                    list.clear();
                    list.addAll(productDAO.getList());
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(ProductActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(ProductActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }
}