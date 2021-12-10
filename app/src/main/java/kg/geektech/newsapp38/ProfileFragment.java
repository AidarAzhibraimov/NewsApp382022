package kg.geektech.newsapp38;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import kg.geektech.newsapp38.databinding.FragmentHomeBinding;
import kg.geektech.newsapp38.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private static final int GALLERY = 1000;
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ivPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY && resultCode == RESULT_OK && null != data){
            Uri imageSelect = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageSelect);
            } catch (IOException e) {
                e.printStackTrace();
            }
            binding.ivPhoto.setImageBitmap(image);
        }
        }
}