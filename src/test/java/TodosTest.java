import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInSimpleTasks() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Позвонить маме");
        SimpleTask task3 = new SimpleTask(3, "Заплатить за интернет");

        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {task1};
        Task[] actual = todos.search("молоко");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInEpics() {
        // Подготовка
        Todos todos = new Todos();

        Epic epic1 = new Epic(1, new String[]{"Анализ требований", "Проектирование", "Кодирование"});
        Epic epic2 = new Epic(2, new String[]{"Тестирование", "Документирование", "Развёртывание"});

        todos.add(epic1);
        todos.add(epic2);

        Task[] expected = {epic1};
        Task[] actual = todos.search("Проектирование");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInMeetings() {
        // Подготовка
        Todos todos = new Todos();
        Meeting meeting1 = new Meeting(1, "Планирование спринта", "Проект А", "10:00");
        Meeting meeting2 = new Meeting(2, "Обзор кода", "Проект Б", "14:00");
        Meeting meeting3 = new Meeting(3, "Встреча с клиентом", "Проект А", "16:00");

        todos.add(meeting1);
        todos.add(meeting2);
        todos.add(meeting3);

        // поиск по теме
        Task[] expected1 = {meeting2};
        Task[] actual1 = todos.search("Обзор");

        Assertions.assertArrayEquals(expected1, actual1);

        // поиск по проекту
        Task[] expected2 = {meeting1, meeting3};
        Task[] actual2 = todos.search("Проект А");

        Assertions.assertArrayEquals(expected2, actual2);
    }
}
