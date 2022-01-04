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

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import kg.geektech.newsapp38.R;
import kg.geektech.newsapp38.databinding.FragmentBoardBinding;
import kg.geektech.newsapp38.databinding.FragmentProfileBinding;


public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;
    private DotsIndicator dotsIndicator;
    private ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        Button btn_skip = view.findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(v -> {
            NavController  navController2 = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
            navController2.navigateUp();
        });
        BoardAdapter adapter = new BoardAdapter(navController);
        viewPager.setAdapter(adapter);
        binding.dotsIndicator.setViewPager2(viewPager);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });


    }

}