package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.viewmodel.contacts.ContactsviewModel;
import com.techsales.taskmanager.data.model.viewmodel.notes.NotesViewModel;

import java.util.List;

public interface NoteListContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showDataLoadingSuccess(List<NotesViewModel> viewModel);

        void showDataFetchError(String message);

        void onNotesItemClick(NotesViewModel items, int position);
    }

    interface Presenter extends BasePresenter, NoteListAdapter.NoteListItemClickListener {
        void getSavedNotes();
    }
}
