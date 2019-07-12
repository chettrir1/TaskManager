package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface AddNotesContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showEmptyFields(String message);

        void showNoteAddSuccess();

        void showNoteAddedError(String message);
    }

    interface Presenter extends BasePresenter {
        void insertNotes(String title, String description);

        void updateNote(int id, String title, String description);
    }
}
