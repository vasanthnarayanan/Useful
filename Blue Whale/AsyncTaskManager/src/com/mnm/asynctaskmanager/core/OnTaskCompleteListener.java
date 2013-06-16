package com.mnm.asynctaskmanager.core;

public interface OnTaskCompleteListener {
    // Notifies about task completeness
    void onTaskComplete(Task task);
}