package kg.geektech.newsapp38.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektech.newsapp38.EditNewsFragment;
import kg.geektech.newsapp38.OnItemClickListener;
import kg.geektech.newsapp38.R;
import kg.geektech.newsapp38.databinding.FragmentHomeBinding;
import kg.geektech.newsapp38.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter(getContext());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                News news = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", news);
                getParentFragmentManager().setFragmentResult("key1",bundle);
                getItem(position);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.editNewsFragment);
            }

            @Override
            public void onLongClick(int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Удалить публикацию?");
                alert.setMessage("Вы действительно хотите удалить публикацию?");
            alert.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    adapter.notifyItemRemoved(position);
                    adapter.removeList(position);
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
            }
        });
    }

    private void getItem(int position) {
        getParentFragmentManager().setFragmentResultListener("ed_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("edit_news");
                Log.e("Home", "text = " + news.getTitle());
                adapter.changeItem(news, position);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();

            }
        });
        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("news");
                Log.e("Home", "text = " + news.getTitle());
                adapter.addItem(news);
            }
        });



        initList();
    }

    private void initList() {
        binding.rvHome.setAdapter(adapter);
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}