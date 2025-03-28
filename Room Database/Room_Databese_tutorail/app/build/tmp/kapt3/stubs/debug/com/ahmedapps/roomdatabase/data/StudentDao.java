package com.ahmedapps.roomdatabase.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H'J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/ahmedapps/roomdatabase/data/StudentDao;", "", "getAllStudents", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/ahmedapps/roomdatabase/data/Student;", "insert", "", "student", "(Lcom/ahmedapps/roomdatabase/data/Student;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassingStatus", "studentId", "", "isPassing", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface StudentDao {
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.ahmedapps.roomdatabase.data.Student student, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM students ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.ahmedapps.roomdatabase.data.Student>> getAllStudents();
    
    @androidx.room.Query(value = "UPDATE students SET isPassing = :isPassing WHERE id = :studentId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePassingStatus(long studentId, boolean isPassing, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}