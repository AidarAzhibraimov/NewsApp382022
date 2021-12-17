package kg.geektech.newsapp38.ui.board;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import kg.geektech.newsapp38.R;
import kg.geektech.newsapp38.SliderAdapter;


public class BoardFragment extends Fragment {

    LinearLayout dotsLayout;
    SliderAdapter adapter;
    ViewPager2 pager2;
    int list[];
    private Button btn_skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        btn_skip = view.findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(v -> {
            NavController  navController2 = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
            navController2.navigateUp();
        });
        BoardAdapter adapter = new BoardAdapter(navController);
        viewPager.setAdapter(adapter);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });


    }
}