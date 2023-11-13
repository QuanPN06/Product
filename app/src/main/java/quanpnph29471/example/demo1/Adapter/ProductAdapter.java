package quanpnph29471.example.demo1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import quanpnph29471.example.demo1.Model.Category;
import quanpnph29471.example.demo1.Model.Product;
import quanpnph29471.example.demo1.R;

public class ProductAdapter extends BaseAdapter {
    List<Product> list;
    Context context;

    public ProductAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view==null){
            row =View.inflate(context, R.layout.layout_item_product,null);
        }else
            row =view;
        //lấy dữ liệu
        Product obj = list.get(i);
        //ánh xạ
        TextView tv_name = row.findViewById(R.id.tv_nameProduct);
        TextView tv_price = row.findViewById(R.id.tv_price);
        TextView tv_cat = row.findViewById(R.id.tv_category);
        //gán dữ liệu

        tv_name.setText(obj.getName());
        tv_price.setText("Giá: "+ obj.getPrice());
        tv_cat.setText("Loại sản phẩm: "+obj.getId_cat()+"");
        return row;
    }
}
