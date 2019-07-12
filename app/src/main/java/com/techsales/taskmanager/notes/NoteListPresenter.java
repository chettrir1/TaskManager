package com.techsales.taskmanager.notes;

import com.google.android.gms.common.internal.service.Common;
import com.techsales.taskmanager.R;
import com.techsales.taskmanager.data.model.notes.Notes;
import com.techsales.taskmanager.data.model.viewmodel.dashboard.DashboardBottomRecyclerViewModel;
import com.techsales.taskmanager.data.model.viewmodel.notes.NotesViewModel;
import com.techsales.taskmanager.di.TaskManagerComponent;
import com.techsales.taskmanager.utils.Commons;

import java.util.List;

import io.reactivex.disposables.Disposable;

class NoteListPresenter implements NoteListContract.Presenter {
    private final TaskManagerComponent component;
    private final NoteListContract.View view;
    private Disposable disposable;

    NoteListPresenter(TaskManagerComponent component, NoteListContract.View view) {
        this.component = component;
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        Commons.dispose(disposable);
    }

    @Override
    public void getSavedNotes() {
        disposable = component.data().getAllNotes()
                .subscribe(notes -> {
                    if (Commons.isNotEmpty(notes)) {
                        List<NotesViewModel> viewModel = Notes.mapToViewModel(component.context(), notes);
                        view.showDataLoadingSuccess(viewModel);
                    } else {
                        view.showDataFetchError(component.context().getString(R.string.status_error_empty_task));
                    }
                }, throwable -> view.showDataFetchError(component.context().getString(R.string.error_server)));

    }

    @Override
    public void onNoteListItemClicked(NotesViewModel items, int position) {
        view.onNotesItemClick(items, position);
    }
}
