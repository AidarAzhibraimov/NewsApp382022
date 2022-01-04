package kg.geektech.newsapp38;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import java.io.IOException;

import kg.geektech.newsapp38.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private static final int GALLERY = 1000;
    private FragmentProfileBinding binding;
    private static int Load_Image = 1001;
    private boolean aBoolean = false;
    private boolean bBoolean = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Prefs prefs = new Prefs(requireContext());
        textSave(prefs);
        bBoolean = true;
        if (!prefs.getImage().equals("")) {
            Glide.with(binding.ivPhoto).load(prefs.getImage()).circleCrop().into(binding.ivPhoto);
            aBoolean = true;
        }
        binding.ivPhoto.setOnClickListener(v -> {

            if (binding.ivPhoto.getDrawable() == ContextCompat.getDrawable(requireActivity(), R.drawable.ic_def)) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY);
            } else {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);

                navController.navigate(R.id.profileImageFragment);
            }
        });
    }

    private void textSave(Prefs prefs) {
        binding.etUsernameSec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.isName(s.toString());
            }
        });
        binding.etUsernameSec.setText(prefs.getIsName());
        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.isEmail(s.toString());
            }
        });
        binding.etEmail.setText(prefs.getIsEmail());
        binding.etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.isPhone(s.toString());
            }
        });
        binding.etPhone.setText(prefs.getPhone());
        binding.etGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.isGender(s.toString());
            }
        });
        binding.etGender.setText(prefs.getGender());
        binding.etDateOfBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.isDate(s.toString());
            }
        });
        binding.etDateOfBirth.setText(prefs.getDate());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY && resultCode == RESULT_OK && null != data) {
            Uri imageSelect = data.getData();
            Prefs prefs = new Prefs(getContext());
            prefs.isImage(imageSelect);
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