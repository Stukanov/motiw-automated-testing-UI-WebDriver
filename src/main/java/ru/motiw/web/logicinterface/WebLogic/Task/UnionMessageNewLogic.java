package ru.motiw.web.logicinterface.WebLogic.Task;

import ru.motiw.web.model.Tasks.Task;

/**
 * Создать задачу
 */
public interface UnionMessageNewLogic {


   void creatingTask(Task task);

   void creatingTaskWithTheTaskOfIWG(Task task);

   void creationOfATaskCheckpoints(Task task);


}



