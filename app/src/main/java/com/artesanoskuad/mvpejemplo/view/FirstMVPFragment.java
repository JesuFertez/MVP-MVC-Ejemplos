package com.artesanoskuad.mvpejemplo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.artesanoskuad.mvpejemplo.R;
import com.artesanoskuad.mvpejemplo.databinding.FragmentFirstBinding;
import com.artesanoskuad.mvpejemplo.model.DonationModel;
import com.artesanoskuad.mvpejemplo.presenter.DonationPresenter;
import com.artesanoskuad.mvpejemplo.presenter.DonationView;
import com.artesanoskuad.mvpejemplo.presenter.MVPEjemploDonationPresenter;

public class FirstMVPFragment extends Fragment implements DonationView {

    private FragmentFirstBinding binding;
    private DonationPresenter presenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        presenter = new MVPEjemploDonationPresenter(this,new DonationModel());
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstMVPFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnNuevaDonacion.setOnClickListener(v -> {
            //saveDonationProfe();
            presenter.addDonation(binding.etDonacion.getText().toString());
        });
    }



    private void displayMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Integer stringToIntegerValue(String value) {
        if (value.trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }


    @Override
    public void updateTotalDonation(int totalAmount) {
        binding.tvTotalDonaciones.setText(String.valueOf(totalAmount));
    }

    @Override
    public void displayConfirmationMessage() {
    displayMessage("Donacion exitosa");

    }

    @Override
    public void displayErrorMessage() {

        displayMessage("No se pudo realizar la donación");
    }

    @Override
    public void clearEditText() {
        binding.etDonacion.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}