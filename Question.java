import java.util.*;

class Question {
	String questionText;
	List<String> options;
	int correctOption;

	public Question(String questionText, List<String> options, int correctOption) {
		this.questionText = questionText;
		this.options = options;
		this.correctOption = correctOption;
	}

	public void displayQuestion() {
		System.out.println(questionText);
		for (int i = 0; i < options.size(); i++) {
			System.out.println((i + 1) + ". " + options.get(i));
		}  
	}
}

class User {
	String username;
	String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}

class OnlineExaminationSystem {
	static List<Question> questions = new ArrayList<>();
	static List<User> users = new ArrayList<>();
	static User loggedInUser;
	static boolean sessionOpen = false;

	public static void main(String[] args) {
		initializeQuestions();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.println("\nEnter your choice:");
			int choice = sc.nextInt();
			sc.nextLine(); // Consume newline
			switch (choice) {
			case 1:
				login(sc);
				break;
			case 2:
				register(sc);
				break;
			case 3:
				System.out.println("Exiting the system");
				sc.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please select a valid option!");
			}
		}
	}

	public static void initializeQuestions() {
		List<String> options1 = Arrays.asList("Guido van Rossum", "James Gosling", "Dennis Ritchie",
				"Bjarne Stroustrup");
		List<String> options2 = Arrays.asList("JRE", "JIT", "JDK", "JVM");
		List<String> options3 = Arrays.asList("Object-oriented", "Use of pointers", "Portable","Dynamic and Extensible");
		List<String> options4 = Arrays.asList(".js", ".txt", ".class", ".java");
		List<String> options5 = Arrays.asList("Polymorphism", "Inheritance", "Compilation", "Encapsulation");

		questions.add(new Question("Who invented Java Programming?", options1, 1));
		questions.add(
				new Question("Which component is used to compile, debug and execute the Java program?", options2, 2));
		questions.add(new Question("Which one of the following is not a Java feature?", options3, 1));
		questions.add(new Question("What is the extension of Java code files?", options4, 3));
		questions.add(new Question("Which of the following is not an OOPS concept in Java?", options5, 2));
	}

	public static void register(Scanner sc) {
		System.out.print("Enter a username: ");
		String username = sc.nextLine();
		System.out.print("Enter a password: ");
		String password = sc.nextLine();

		// Check if the username is already taken
		for (User user : users) {
			if (user.username.equals(username)) {
				System.out.println("Username already taken. Please choose another one.");
				return;
			}
		}
		User newUser = new User(username, password);
		users.add(newUser);
		System.out.println("Registration successful! You can log in now.");
	}

	public static void login(Scanner sc) {
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		for (User user : users) {
			if (user.username.equals(username) && user.password.equals(password)) {
				loggedInUser = user;
				sessionOpen = true;
				startExam(sc);
				return;
			}
		}
		System.out.println("Invalid credentials. Please try again.");
	}

	public static void startExam(Scanner sc) {
		System.out.println("\nWelcome, " + loggedInUser.username + "!");
		int correctAnswers = 0;

		for (Question question : questions) {
			question.displayQuestion();
			System.out.print("Select your answer (1-" + question.options.size() + "): ");
			int userChoice = sc.nextInt();
			if (userChoice == question.correctOption + 1) {
				System.out.println("Correct!");
				correctAnswers++;
			} else {
				System.out.println("Incorrect!");
			}
		}
		System.out.println("Exam completed! You answered " + correctAnswers + " out of " + questions.size()
				+ " questions correctly.");
		sessionOpen = false;
	}
}