package com.ahmedapps.roomdatabase.metier;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0015J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0017J\u000e\u0010\u001c\u001a\u00020\u0012H\u0082@\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0015H\u0002R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006 "}, d2 = {"Lcom/ahmedapps/roomdatabase/metier/StudentViewModel;", "Landroidx/lifecycle/ViewModel;", "studentDao", "Lcom/ahmedapps/roomdatabase/data/StudentDao;", "appPreferences", "Lcom/ahmedapps/roomdatabase/data/AppPreferences;", "firestoreManager", "Lcom/ahmedapps/roomdatabase/data/FirestoreManager;", "(Lcom/ahmedapps/roomdatabase/data/StudentDao;Lcom/ahmedapps/roomdatabase/data/AppPreferences;Lcom/ahmedapps/roomdatabase/data/FirestoreManager;)V", "_students", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/ahmedapps/roomdatabase/data/Student;", "students", "Lkotlinx/coroutines/flow/StateFlow;", "getStudents", "()Lkotlinx/coroutines/flow/StateFlow;", "addStudent", "", "student", "getPassingGrade", "", "getSortOrder", "", "setPassingGrade", "grade", "setSortOrder", "order", "syncWithFirestore", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassingStatusForAllStudents", "passingGrade", "app_debug"})
public final class StudentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.ahmedapps.roomdatabase.data.StudentDao studentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ahmedapps.roomdatabase.data.AppPreferences appPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ahmedapps.roomdatabase.data.FirestoreManager firestoreManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.ahmedapps.roomdatabase.data.Student>> _students = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.ahmedapps.roomdatabase.data.Student>> students = null;
    
    public StudentViewModel(@org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.StudentDao studentDao, @org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.AppPreferences appPreferences, @org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.FirestoreManager firestoreManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.ahmedapps.roomdatabase.data.Student>> getStudents() {
        return null;
    }
    
    private final java.lang.Object syncWithFirestore(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void addStudent(@org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.Student student) {
    }
    
    public final double getPassingGrade() {
        return 0.0;
    }
    
    public final void setPassingGrade(double grade) {
    }
    
    private final void updatePassingStatusForAllStudents(double passingGrade) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSortOrder() {
        return null;
    }
    
    public final void setSortOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String order) {
    }
}