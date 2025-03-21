package com.ahmedapps.roomdatabase.metier;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/ahmedapps/roomdatabase/metier/StudentViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "studentDao", "Lcom/ahmedapps/roomdatabase/data/StudentDao;", "appPreferences", "Lcom/ahmedapps/roomdatabase/data/AppPreferences;", "firestoreManager", "Lcom/ahmedapps/roomdatabase/data/FirestoreManager;", "(Lcom/ahmedapps/roomdatabase/data/StudentDao;Lcom/ahmedapps/roomdatabase/data/AppPreferences;Lcom/ahmedapps/roomdatabase/data/FirestoreManager;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
public final class StudentViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    @org.jetbrains.annotations.NotNull()
    private final com.ahmedapps.roomdatabase.data.StudentDao studentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ahmedapps.roomdatabase.data.AppPreferences appPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ahmedapps.roomdatabase.data.FirestoreManager firestoreManager = null;
    
    public StudentViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.StudentDao studentDao, @org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.AppPreferences appPreferences, @org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.FirestoreManager firestoreManager) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
}