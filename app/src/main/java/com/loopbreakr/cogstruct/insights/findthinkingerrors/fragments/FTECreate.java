package com.loopbreakr.cogstruct.insights.findthinkingerrors.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.FteFragmentCreateBinding;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.models.FTEViewModel;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.adapters.FTEViewPagerAdapter;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_BWT;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_DP;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_FF;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_FOTP;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_G;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_MF;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_P;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments.FTE_SS;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;


public class FTECreate extends Fragment {
    private FTEViewModel fteViewModel;
    private FteFragmentCreateBinding binding;
    private ImageView vpBack, vpNext;
    private Button backButton, reviewButton;
    private ViewPager viewPager;
    List<Fragment> fragmentList;

    public FTECreate() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fteViewModel = new ViewModelProvider(requireActivity()).get(FTEViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fte_fragment_create, container, false);
        binding.setViewModel(fteViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
        setViewPager();
    }

    private void setViewPager() {
        FTEViewPagerAdapter adapter =  new  FTEViewPagerAdapter(requireActivity().getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // add the fragments to viewpager adapter
        fragmentList = Arrays.asList(new FTE_BWT(), new FTE_DP(), new FTE_SS(), new FTE_FF(), new FTE_FOTP(), new FTE_G(), new FTE_MF(),new FTE_P());
        for(Fragment fragment: fragmentList){
            adapter.add(fragment);
        }
        //attach the adapter to the viewpager in view
        viewPager.setAdapter(adapter);
    }

    private void findViews(View view) {
        backButton = view.findViewById(R.id.fte_back);
        reviewButton = view.findViewById(R.id.fte_review);
        viewPager = view.findViewById(R.id.fte_viewpager);
        vpBack = view.findViewById(R.id.fte_vp_back);
        vpNext = view.findViewById(R.id.fte_vp_next);
    }

    private void setButtons() {
        //set behavior for viewpager navigation
        vpBack.setOnClickListener(v -> {
            if(viewPager.getCurrentItem() != 0){
                viewPager.setCurrentItem(getItem(-1), true);
            }
            else{ viewPager.setCurrentItem(getFragmentCount(), true); }
        });
        vpNext.setOnClickListener(v -> {
            if(viewPager.getCurrentItem() != getFragmentCount()){
                viewPager.setCurrentItem(getItem(+1), true);
            }
            else{ viewPager.setCurrentItem(0, true); }
        });

        //set host fragment navigation
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.FTESelectCreate, true);
            controller.navigate(R.id.FTESelectCreate);
        });
        reviewButton.setOnClickListener( v -> controller.navigate(R.id.action_FTECreate_to_FTEReview));
    }

    @Override
    public void onResume() {
        super.onResume();
        setViewPager();
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    private int getFragmentCount(){
        return fragmentList.size()-1;
    }
}