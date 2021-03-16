package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.proscons.PCViewModel;

import java.util.Arrays;
import java.util.List;

public class PCLogsFragment extends Fragment {
    LogsViewModel logsViewModel;
    PCViewModel pcViewModel;
    private TextView behaviorText, changeProsText, dontChangeProsText, changeConsText, dontChangeConsText;

    public PCLogsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        pcViewModel = new  ViewModelProvider(requireActivity()).get(PCViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logs_fragment_pc, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        setViewModelData();
        setTextViews();
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.logsToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOverflowIcon(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_white_dots));
        toolbar.setOnMenuItemClickListener(item -> {
            NavController controller = Navigation.findNavController(requireView());
            switch (item.getItemId()) {
                case R.id.action_editLog :
                    controller.navigate(R.id.action_PCLogFragment_to_PCLogEditFragment);
                    return true;
                case R.id.action_deleteLog:
                    DocumentSnapshot snapshot = logsViewModel.getSnapshot();
                    snapshot.getReference().delete().addOnFailureListener(e ->
                            Log.e("DELETING...", "deleteSnapshot: " + snapshot.getData(), e)).addOnSuccessListener(aVoid ->
                            Log.d("DELETING...", "deleteSnapshot: " + snapshot.getData()));
                    controller.popBackStack(R.id.allLogsFragment, true);
                    controller.navigate(R.id.allLogsFragment);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        });
    }

    private void findViews(View view) {
        behaviorText = view.findViewById(R.id.pc_behavior_log_text);
        changeProsText = view.findViewById(R.id.log_change_pros_text);
        dontChangeProsText = view.findViewById(R.id.log_dont_change_pros_text);
        changeConsText = view.findViewById(R.id.log_change_cons_text);
        dontChangeConsText = view.findViewById(R.id.log_dont_change_cons_text);
    }


    private void setViewModelData() {
        DocumentSnapshot snapshot = logsViewModel.getSnapshot();
        pcViewModel.setPCBehavior(snapshot.getString("behavior"));
        pcViewModel.setChangePros(Arrays.asList(snapshot.getString("changePros").split("\\s*,\\s*")));
        pcViewModel.setDontChangePros(Arrays.asList(snapshot.getString("dontChangePros").split("\\s*,\\s*")));
        pcViewModel.setChangeCons(Arrays.asList(snapshot.getString("changeCons").split("\\s*,\\s*")));
        pcViewModel.setDontChangeCons(Arrays.asList(snapshot.getString("dontChangeCons").split("\\s*,\\s*")));
    }

    private void setTextViews() {
    behaviorText.setText(pcViewModel.getPCBehavior());
    behaviorText.setTextColor(getResources().getColor(R.color.lightGrey));

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

    @Override
    public void onStart() {
        super.onStart();
        behaviorText.setText(pcViewModel.getPCBehavior());
        behaviorText.setTextColor(getResources().getColor(R.color.lightGrey));

    }
}