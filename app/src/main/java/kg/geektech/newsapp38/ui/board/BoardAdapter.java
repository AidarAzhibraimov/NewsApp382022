package kg.geektech.newsapp38.ui.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import kg.geektech.newsapp38.Prefs;
import kg.geektech.newsapp38.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final String[] titles = new String[] {"Добро пожаловать!", "Добавление новостей","Редактирование"};
    private final String[] descriptions = new String[] {"Самое простое приложение для новостей.",
            "Очень просто и легко можно добавить свою новость",
            "А так же в один клик можно редактировать свою запись!"};
    private final String[] images = new String[]{"lottie-1.json","lottie-2.json",
            "lottie-3.json"};
    private Context context;

    private final NavController navController;
    public BoardAdapter(NavController navController){
        this.navController = navController;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

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

        private final Button btn_start;
        private final TextView textTitle;
        private final TextView tvDescription;
        private DotsIndicator dotsIndicator;
        private LottieAnimationView lottieAnimationView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            lottieAnimationView = itemView.findViewById(R.id.lav_actionBar);
            Button btn_skip = itemView.findViewById(R.id.btn_skip);

            btn_start = itemView.findViewById(R.id.btn_start);
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Prefs(context).saveBoardState();
                    navController.navigateUp();
                }
            });
        }



        public void bind(int position) {

            lottieAnimationView.setAnimation(images[position]);
            textTitle.setText(titles[position]);
            tvDescription.setText(descriptions[position]);
            if (position == titles.length -1){
                btn_start.setVisibility(View.VISIBLE);
            }else {
                btn_start.setVisibility(View.INVISIBLE );
            }
        }
    }
}
