# To-Do List Application

This is a simple To-Do List application built for Android using Java. The app allows users to add, edit, and delete tasks. Each task includes a task name, a completion date, and a completion time. The tasks are stored in an SQLite database, ensuring data persistence across app restarts.

## Features

- Add new tasks with a name, date, and time.
- Edit existing tasks.
- Delete tasks.
- Persistent storage using SQLite database.

## Prerequisites

- Android Studio
- Android SDK

## Getting Started

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/to-do-list.git
```

### Step 2: Open the Project in Android Studio

1. Open Android Studio.
2. Click on `Open an existing Android Studio project`.
3. Navigate to the cloned repository and select it.

### Step 3: Build the Project

1. Click on `Build` in the menu.
2. Select `Rebuild Project`.

### Step 4: Run the App

1. Connect your Android device or start an Android emulator.
2. Click on `Run` in the menu.
3. Select `Run 'app'`.

## Project Structure

- `MainActivity.java`: The main activity that handles the user interface and user interactions.
- `RecyclerAdapter.java`: The adapter for the RecyclerView that displays the list of tasks.
- `TaskModal.java`: A model class representing a task.
- `DatabaseHelper.java`: A helper class for managing the SQLite database.

## Usage

1. **Add Task**:
   - Enter task name, date, and time.
   - Click on the `Add Task` button.

2. **Edit Task**:
   - Click on the edit icon next to a task.
   - Modify the task details.
   - Click on the `Add Task` button to save changes.

3. **Delete Task**:
   - Click on the delete icon next to a task.

## Screenshots

Include some screenshots of your app here.


## Acknowledgements

- Android Developers documentation
- Stack Overflow community

## Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/fooBar`).
3. Commit your changes (`git commit -am 'Add some fooBar'`).
4. Push to the branch (`git push origin feature/fooBar`).
5. Create a new Pull Request.

## Contact

If you have any questions or suggestions, feel free to reach out.

- Email: dinkararya09@gmail.com
- GitHub: https://github.com/Dinkar18/

---

Thank you for using the To-Do List application!
