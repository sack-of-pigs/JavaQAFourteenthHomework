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
    void searchInSimpleTasksMultiple() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Позвонить папе");
        SimpleTask task2 = new SimpleTask(2, "Позвонить маме");
        SimpleTask task3 = new SimpleTask(3, "Заплатить за интернет");

        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {task1, task2};
        Task[] actual = todos.search("Позвонить");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInSimpleTasksOne() {
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
    void searchInSimpleTasksNone() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Позвонить маме");
        SimpleTask task3 = new SimpleTask(3, "Заплатить за интернет");

        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {};
        Task[] actual = todos.search("Сходить");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInEpicsMultiple() {
        Todos todos = new Todos();

        Epic epic1 = new Epic(1, new String[]{"Купить молоко", "Полить цветы", "Выгулять собаку"});
        Epic epic2 = new Epic(2, new String[]{"Купить хлеб", "Постирать вещи", "Убраться"});

        todos.add(epic1);
        todos.add(epic2);

        Task[] expected = {epic1, epic2};
        Task[] actual = todos.search("Купить");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInEpicsOne() {
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
    void searchInEpicsNone() {
        Todos todos = new Todos();

        Epic epic1 = new Epic(1, new String[]{"Анализ требований", "Проектирование", "Кодирование"});
        Epic epic2 = new Epic(2, new String[]{"Тестирование", "Документирование", "Развёртывание"});

        todos.add(epic1);
        todos.add(epic2);

        Task[] expected = {};
        Task[] actual = todos.search("Исследование");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void searchInMeetingsMultiple() {
        Todos todos = new Todos();
        Meeting meeting1 = new Meeting(1, "Планирование спринта", "Проект А", "10:00");
        Meeting meeting2 = new Meeting(2, "Обзор кода", "Проект Б", "14:00");
        Meeting meeting3 = new Meeting(3, "Встреча с клиентом", "Проект А", "16:00");

        todos.add(meeting1);
        todos.add(meeting2);
        todos.add(meeting3);

        Task[] expected2 = {meeting1, meeting3};
        Task[] actual2 = todos.search("Проект А");

        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    void searchInMeetingsOne() {
        Todos todos = new Todos();
        Meeting meeting1 = new Meeting(1, "Планирование спринта", "Проект А", "10:00");
        Meeting meeting2 = new Meeting(2, "Обзор кода", "Проект Б", "14:00");
        Meeting meeting3 = new Meeting(3, "Встреча с клиентом", "Проект А", "16:00");

        todos.add(meeting1);
        todos.add(meeting2);
        todos.add(meeting3);

        Task[] expected1 = {meeting2};
        Task[] actual1 = todos.search("Обзор");

        Assertions.assertArrayEquals(expected1, actual1);
    }

    @Test
    void searchInMeetingsNone() {
        Todos todos = new Todos();
        Meeting meeting1 = new Meeting(1, "Планирование спринта", "Проект А", "10:00");
        Meeting meeting2 = new Meeting(2, "Обзор кода", "Проект Б", "14:00");
        Meeting meeting3 = new Meeting(3, "Встреча с клиентом", "Проект А", "16:00");

        todos.add(meeting1);
        todos.add(meeting2);
        todos.add(meeting3);

        Task[] expected1 = {};
        Task[] actual1 = todos.search("Проект В");

        Assertions.assertArrayEquals(expected1, actual1);
    }
}
