package kg.geektech.newsapp38.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kg.geektech.newsapp38.OnItemClickListener;
import kg.geektech.newsapp38.R;
import kg.geektech.newsapp38.databinding.ItemNewsBinding;
import kg.geektech.newsapp38.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    
    private ArrayList<News> list;

    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0,news);
        notifyItemInserted(0);
    }

    public void updateItem(News news) {
        int index = list.indexOf(news);
        list.set(index, news);
        notifyItemChanged(index);
    }

    public void addItems(List<News> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void removeList(int position) {
        list.remove(position);
    }


    public News getItem(int position) {
        return list.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;
        public ViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(getAdapterPosition());
                    return false;
                }
            });
        }

        public void bind(News news) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat data = new SimpleDateFormat("MM.dd HH:mm:ss");
            String a = data.format(news.getCreateDate());
            binding.tvData.setText(a);
            binding.tvTitle.setText(news.getTitle());
            if (getAdapterPosition() %2 ==1 ){
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
                binding.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.white));
                binding.tvData.setTextColor(ContextCompat.getColor(context,R.color.white));

            }

        }
    }
}
