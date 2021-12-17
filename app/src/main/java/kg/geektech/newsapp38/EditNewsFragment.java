package kg.geektech.newsapp38;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kg.geektech.newsapp38.databinding.FragmentEditNewsBinding;
import kg.geektech.newsapp38.databinding.FragmentHomeBinding;
import kg.geektech.newsapp38.models.News;


public class EditNewsFragment extends Fragment {

    private FragmentEditNewsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String text = binding.editText.getText().toString();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                close();
            }
        });
        getParentFragmentManager().setFragmentResultListener("key1", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("key");
                Log.e("key", "text = " + text);
                String a = news.getTitle().toString();
                binding.editText.setText(a);
            }
        });
    }
    private void save() {
        String text = binding.editText.getText().toString();
        /*if (binding.editText.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Поле не должно быть пустым", Toast.LENGTH_SHORT).show();
        }else {*/
            News news = new News(text, System.currentTimeMillis());
            Bundle bundle = new Bundle();
            bundle.putSerializable("edit_news", news);
            getParentFragmentManager().setFragmentResult("ed_news", bundle);
            close();
        }
   /* }*/
    private void close(){
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}