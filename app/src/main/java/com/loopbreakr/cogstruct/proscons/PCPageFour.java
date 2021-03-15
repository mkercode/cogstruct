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
import android.widget.TextView;
import android.widget.Toast;

import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.activities.TJActivity;

import java.util.List;


public class PCPageFour extends Fragment {
    private PCViewModel pcViewModel;
    private Button editButton, submitButton;
    private TextView behaviorText, changeProsText, dontChangeProsText, changeConsText, dontChangeConsText;
    private String behavior;

    public PCPageFour() {
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
        return inflater.inflate(R.layout.pc_fragment_page_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
        getViewModelData();
    }

    private void findViews(View view) {
        editButton = view.findViewById(R.id.pc_edit);
        submitButton = view.findViewById(R.id.pc_submit);

        behaviorText = view.findViewById(R.id.pc_behavior_review);
        changeProsText = view.findViewById(R.id.review_change_pros);
        dontChangeProsText = view.findViewById(R.id.review_dont_change_pros);
        changeConsText = view.findViewById(R.id.review_change_cons);
        dontChangeConsText = view.findViewById(R.id.review_dont_change_cons);
    }

    private void setButtons() {
        editButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.popBackStack(R.id.tjPageThree, true);
            controller.navigate(R.id.tjPageThree);
        });
        submitButton.setOnClickListener(v ->{
            submitData();
            Toast.makeText(requireActivity().getApplicationContext(), "Saved in logs", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
    }

    private void getViewModelData() {
        if(!pcViewModel.getPCBehavior().isEmpty() && !pcViewModel.getPCBehavior().equals("")){
            behaviorText.setText(pcViewModel.getPCBehavior());
            behaviorText.setTextColor(getResources().getColor(R.color.lightGrey));
        }

        fillTextView(pcViewModel.getChangePros(), changeProsText);
        fillTextView(pcViewModel.getDontChangePros(), dontChangeProsText);
        fillTextView(pcViewModel.getChangeCons(), changeConsText);
        fillTextView(pcViewModel.getDontChangeCons(), dontChangeConsText);
    }

    private void fillTextView(List<String> list, TextView textView) {
        if (list != null && !list.isEmpty()) {
            StringBuilder displayThoughts = new StringBuilder();
            for (String pc : list) {
                displayThoughts.append("-").append(pc).append("\n");
            }
            textView.setText(displayThoughts.toString());
        }
    }

    private void submitData() {
        ((PCActivity)requireActivity()).sendToFirestore(pcViewModel.getPCBehavior(),pcViewModel.getChangeProsString(), pcViewModel.getDontChangeProsString(), pcViewModel.getChangeConsString(), pcViewModel.getDontChangeConsString());
    }


}