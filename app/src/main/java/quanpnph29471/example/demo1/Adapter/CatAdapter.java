package quanpnph29471.example.demo1.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import quanpnph29471.example.demo1.Model.Category;
import quanpnph29471.example.demo1.R;

public class CatAdapter extends BaseAdapter {
    List<Category> list_cat;
    Context context;

    public CatAdapter(List<Category> list_cat, Context context) {
        this.list_cat = list_cat;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list_cat.size();
    }

    @Override
    public Object getItem(int i) {
        return list_cat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list_cat.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view==null){
            row =View.inflate(context, R.layout.layout_item_cat,null);
        }else
            row =view;
        //lấy dữ liệu
        Category obj = list_cat.get(i);
        //ánh xạ
        TextView tv_id  = row.findViewById(R.id.tv_id);
        TextView tv_name = row.findViewById(R.id.tv_name);

        //gán dữ liệu
        tv_id.setText(obj.getId()+"");
        tv_name.setText(obj.getName());

        return row;
    }
}
