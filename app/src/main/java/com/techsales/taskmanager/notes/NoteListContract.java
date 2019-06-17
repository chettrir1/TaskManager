package com.techsales.taskmanager.notes;

import com.techsales.taskmanager.BasePresenter;
import com.techsales.taskmanager.BaseView;

public interface NoteListContract {

    interface View extends BaseView<Presenter> {
        void showProgress();
    }

    interface Presenter extends BasePresenter {
        void getSavedNotes();
    }
}
