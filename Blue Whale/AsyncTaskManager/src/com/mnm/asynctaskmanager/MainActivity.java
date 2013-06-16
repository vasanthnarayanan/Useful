package com.mnm.asynctaskmanager;

import com.mnm.asynctaskmanager.core.AsyncTaskManager;
import com.mnm.asynctaskmanager.core.OnTaskCompleteListener;
import com.mnm.asynctaskmanager.core.Task;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTaskCompleteListener {

    private AsyncTaskManager mAsyncTaskManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	// Create manager and set this activity as context and listener
	mAsyncTaskManager = new AsyncTaskManager(this, this);
	// Handle task that can be retained before
	mAsyncTaskManager.handleRetainedTask(getLastNonConfigurationInstance());
    }

    public void onRunButtonClick(View view) {
	// Create and run task and progress dialog
	mAsyncTaskManager.setupTask(new Task(getResources()));
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
	// Delegate task retain to manager
	return mAsyncTaskManager.retainTask();
    }

    @Override
    public void onTaskComplete(Task task) {
	if (task.isCancelled()) {
	    // Report about cancel
	    Toast.makeText(this, R.string.task_cancelled, Toast.LENGTH_LONG)
		    .show();
	} else {
	    // Get result
	    Boolean result = null;
	    try {
		result = task.get();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    // Report about result
	    Toast.makeText(this,
		    getString(R.string.task_completed, (result != null) ? result.toString() : "null"),
		    Toast.LENGTH_LONG).show();
	}
    }
}