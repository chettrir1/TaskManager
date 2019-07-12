package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;
import com.techsales.taskmanager.data.model.notes.Notes;

public interface AddNotesContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showEmptyFields(String message);

        void showNoteAddSuccess();

        void showNoteAddedError(String message);
    }

    interface Presenter extends BasePresenter {
        void insertNotes(String title, String description);
    }
}
