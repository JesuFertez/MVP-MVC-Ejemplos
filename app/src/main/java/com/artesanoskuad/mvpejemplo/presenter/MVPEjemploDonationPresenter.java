package com.artesanoskuad.mvpejemplo.presenter;

import com.artesanoskuad.mvpejemplo.model.Donation;
import com.artesanoskuad.mvpejemplo.model.DonationModel;

public class MVPEjemploDonationPresenter implements DonationPresenter {

    private DonationView donationView;
    private DonationModel donationModel;

    public MVPEjemploDonationPresenter(DonationView donationView, DonationModel donationModel) {
        this.donationView = donationView;
        this.donationModel = donationModel;
    }

    @Override
    public void addDonation(String newDonation) {

        Integer value = stringToIntegerValue(newDonation);
        if (value > 0) {
            boolean isSaveDonation = donationModel.saveDonation(new Donation(value));
            if (isSaveDonation) {
                donationView.displayConfirmationMessage();
                donationView.updateTotalDonation(donationModel.getTotalAmount());
                donationView.clearEditText();
            } else {
                donationView.displayErrorMessage();
            }
        } else {
            donationView.displayErrorMessage();
        }
    }

    @Override
    public Integer stringToIntegerValue(String value) {
        if (value.trim().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }
}
