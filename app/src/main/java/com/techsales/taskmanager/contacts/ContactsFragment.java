package com.techsales.taskmanager.contacts;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.techsales.taskmanager.BaseFragment;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.viewmodel.contacts.ContactsviewModel;
import com.techsales.taskmanager.databinding.FragmentContactsBinding;

import java.util.List;

import javax.inject.Inject;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class ContactsFragment extends BaseFragment implements ContactsContract.View {
    private FragmentContactsBinding binding;

    @Inject
    ContactsContract.Presenter presenter;

    public static ContactsFragment getInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, null, false);
        binding.contentState.setContent(binding.content);
        binding.swipeContainer.setColorSchemeResources(R.color.colorBlue, R.color.colorYellow, R.color.colorRed, R.color.colorGreen);
        binding.swipeContainer.setOnRefreshListener(() -> presenter.getAllContacts());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        presenter.getAllContacts();
        super.onResume();
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }

    @Override
    public void showProgress() {
        binding.contentState.showProgress(getResources().getString(R.string.contacts_loading_message));
    }

    @Override
    public void showLoadingError(String message) {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.ic_loading_error, message);
    }

    @Override
    public void showLoadingSuccess(List<ContactsviewModel> viewModel) {
        hideSwipeContainer();
        if (getContext() != null) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), VERTICAL);
            binding.rvContacts.addItemDecoration(dividerItemDecoration);
        }
        ContactsRecyclerAdapter adapter = new ContactsRecyclerAdapter(viewModel, presenter);
        binding.rvContacts.setAdapter(adapter);
        binding.contentState.showContent();
    }

    @Override
    public void showNoNetworkAvailable() {
        hideSwipeContainer();
        binding.contentState.showError(R.drawable.no_internet, getString(R.string.error_network_not_available));
    }

    @Override
    public void onContactsItemClick(ContactsviewModel items, int position) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + items.getPhone()));
        startActivity(intent);
    }

    private void hideSwipeContainer() {
        if (binding.swipeContainer.isRefreshing() && binding.swipeContainer != null)
            binding.swipeContainer.setRefreshing(false);
    }
}
