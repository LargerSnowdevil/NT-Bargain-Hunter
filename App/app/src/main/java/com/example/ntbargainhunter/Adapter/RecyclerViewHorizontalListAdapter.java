package com.example.ntbargainhunter.Adapter;

import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.example.ntbargainhunter.R;
        import com.example.ntbargainhunter.Model.Category;
        import java.util.List;

public class RecyclerViewHorizontalListAdapter extends RecyclerView.Adapter<RecyclerViewHorizontalListAdapter.CategoryViewHolder>{
    private List<Category> horizontalCategory;
    Context context;

    public RecyclerViewHorizontalListAdapter(List<Category> horizontalCategory, Context context){
        this.horizontalCategory = horizontalCategory;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View CategoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontalcategorylist, parent, false);
        CategoryViewHolder gvh = new CategoryViewHolder(CategoryView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.imageView.setImageResource(horizontalCategory.get(position).getCategoryImage());
        holder.txtview.setText(horizontalCategory.get(position).getCategoryName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryName = horizontalCategory.get(position).getCategoryName().toString();
                Toast.makeText(context, categoryName + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtview;
        public CategoryViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.idCategoryImage);
            txtview=view.findViewById(R.id.idCategoryName);
        }
    }
}