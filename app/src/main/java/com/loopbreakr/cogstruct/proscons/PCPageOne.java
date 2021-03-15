package com.loopbreakr.cogstruct.proscons;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;


public class PCPageOne extends Fragment {
    private PCViewModel pcViewModel;
    private Button returnButton, nextButton;
    private EditText behaviorInput;

    public PCPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pcViewModel = new ViewModelProvider(requireActivity()).get(PCViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pc_fragment_page_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
        getViewModelData();
    }

    private void getViewModelData() {
        pcViewModel.getPCBehavior().observe(getViewLifecycleOwner(), charSequence -> behaviorInput.setText(charSequence));
    }

    private void findViews(View view) {
        returnButton = view.findViewById(R.id.pc_return);
        nextButton = view.findViewById(R.id.pc_page_one_next);
        behaviorInput = view.findViewById(R.id.pc_behavior_input);
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();

        });

        nextButton.setOnClickListener(v ->{
            pcViewModel.setPCBehavior(behaviorInput.getText());
            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(R.id.action_PCPageOne_to_PCPageTwo);
        });
    }
}