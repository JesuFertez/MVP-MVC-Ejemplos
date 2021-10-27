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
import com.artesanoskuad.mvpejemplo.controller.DonationController;
import com.artesanoskuad.mvpejemplo.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private DonationController controller;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        controller = new DonationController();
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnNuevaDonacion.setOnClickListener(v -> {
            saveDonationProfe();
        });
    }

    public void saveDonationProfe() {

        Integer valorDonacion = stringToIntegerValue(binding.etDonacion.getText().toString());
        boolean guardarDonacion = controller.save(valorDonacion);
        if(guardarDonacion) {
            Integer nuevoTotal = controller.getTotalAmount();
            binding.tvTotalDonaciones.setText(String.valueOf(nuevoTotal));
            displayMessage("Total: " + nuevoTotal);
            binding.etDonacion.setText("");
        } else {
            displayMessage("Debe ingresar un monto superior a 0");
        }
    }

    private void displayMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Integer stringToIntegerValue(String value) {
        if (value.trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }

}