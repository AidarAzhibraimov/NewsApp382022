package kg.geektech.newsapp38.ui.board;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import kg.geektech.newsapp38.R;
import kg.geektech.newsapp38.SliderAdapter;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] titles = new String[] {"Добро пожаловать!", "Добавление новостей","Редактирование"};
    private String[] descriptions = new String[] {"Самое простое приложение для новостей.",
            "Очень просто и легко можно добавить свою новость",
            "А так же в один клик можно редактировать свою запись!"};
    private int[] images = new int[]{R.drawable.ic_news,R.drawable.ic_addnews,
            R.drawable.ic_editing};

    private NavController navController;
    public BoardAdapter(NavController navController){
        this.navController = navController;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btn_start;
        private Button btn_skip;
        private TextView textTitle;
        private TextView tvDescription;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            image = itemView.findViewById(R.id.iv_board);
            btn_skip = itemView.findViewById(R.id.btn_skip);

            /*btn_skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigate(R.id.navigation_home);
                }
            });*/

            btn_start = itemView.findViewById(R.id.btn_start);
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigateUp();
                }
            });
        }


        public void bind(int position) {
            textTitle.setText(titles[position]);
            tvDescription.setText(descriptions[position]);
            image.setImageResource(Integer.parseInt(String.valueOf(images[position])));
            if (position == titles.length -1){
                btn_start.setVisibility(View.VISIBLE);
            }else {
                btn_start.setVisibility(View.INVISIBLE );
            }
        }
    }
}
