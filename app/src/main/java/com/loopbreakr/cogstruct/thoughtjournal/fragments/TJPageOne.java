package com.loopbreakr.cogstruct.thoughtjournal.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.TjFragmentPageOneBinding;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import org.jetbrains.annotations.NotNull;

public class TJPageOne extends Fragment {
    private TJViewModel tjViewModel;
    private Button returnButton, nextButton;
    private TjFragmentPageOneBinding binding;

    public TJPageOne() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJViewModel.class);
      }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.tj_fragment_page_one, container, false);
        binding.setViewModel(tjViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        returnButton = view.findViewById(R.id.tj_return);
        nextButton = view.findViewById(R.id.page_one_next);
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        nextButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(R.id.action_tjPageOne_to_tjPageTwo);
        });
    }

}