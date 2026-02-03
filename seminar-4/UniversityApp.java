

public class UniversityApp {

    public static void main(String[] args) {
        // testExceptionHandling();  // Тест для Задания 1 / Test for Task 1
        // testCourseProcessing();   // Тест для Задания 2 / Test for Task 2

        System.out.println("=== University Management System ===\n");

        // TODO: Step 1 - Initialize all systems
        // Create StudentDataManager
        // Create CourseProcessor instances (undergrad, grad, lab)

        // TODO: Step 2 - Load student data
        // Try to load from file using try-with-resources
        // If file doesn't exist, create sample students
        // Handle InvalidStudentDataException

        // TODO: Step 3 - Demonstrate course enrollment
        // Enroll alice in CS201 (should succeed)
        // Enroll bob in CS201 (should fail - missing prerequisites)
        // Enroll bob in CS501 grad course (should fail - low GPA)

        // TODO: Step 4 - Save data
        // Save updated student data to file

        System.out.println("\n=== Application Complete ===");
    }

    /*private static void testExceptionHandling() {
        System.out.println("\n=== Student Data Manager - Exception Handling ===\n");

        StudentDataManager manager = new StudentDataManager();

        // Test 1: Save students
        System.out.println("Test 1: Saving students");
        Student alice = new Student("S001", "Alice Johnson", 3.7, List.of("CS101", "CS102"));
        Student bob = new Student("S002", "Bob Smith", 2.8, List.of("CS101"));
        Student carol = new Student("S003", "Carol Williams", 3.9, List.of("CS101", "CS102", "CS201"));

        manager.saveStudent(alice);
        manager.saveStudent(bob);
        manager.saveStudent(carol);
        System.out.println("Saved 3 students\n");

        // Test 2: Try to save duplicate (should throw unchecked exception)
        System.out.println("Test 2: Attempting to save duplicate student");
        try {
            manager.saveStudent(alice); // Same ID
            System.out.println("ERROR: Should have thrown DuplicateStudentException!");
        } catch (DuplicateStudentException e) {
            System.out.println("Correctly caught exception: " + e.getMessage());
        }
        System.out.println();

        // Test 3: Find existing student
        System.out.println("Test 3: Finding existing student");
        try {
            Student found = manager.findStudent("S001");
            System.out.println("Found: " + found);
        } catch (StudentNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        System.out.println();

        // Test 4: Try to find non-existent student (should throw checked exception)
        System.out.println("Test 4: Finding non-existent student");
        try {
            Student notFound = manager.findStudent("S999");
            System.out.println("ERROR: Should have thrown StudentNotFoundException!");
        } catch (StudentNotFoundException e) {
            System.out.println("Correctly caught exception: " + e.getMessage());
        }
        System.out.println();

        // Test 5: Save to file
        System.out.println("Test 5: Saving to file");
        try {
            manager.saveToFile();
            System.out.println("Saved to students.txt\n");
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }

        // Test 6: Load from file using try-with-resources
        System.out.println("Test 6: Loading from file");
        manager.clear();
        try {
            manager.loadFromFile();
            System.out.println("Loaded students:");
            for (Student s : manager.getAllStudents()) {
                System.out.println("  " + s);
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        System.out.println();

        // Test 7: Test StudentDataReader directly with try-with-resources
        System.out.println("Test 7: Using StudentDataReader with try-with-resources");
        try (StudentDataReader reader = new StudentDataReader("students.txt")) {
            System.out.println("Reading students:");
            Student s;
            while ((s = reader.readStudent()) != null) {
                System.out.println("  " + s);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("=== All tests completed ===");
    }*/

    /*private static void testCourseProcessing() {
        System.out.println("\n=== University Course Enrollment System ===\n");

        // Create test students
        Student alice = new Student("S001", "Alice Johnson", 3.7, List.of("CS101", "CS102", "MATH201"));
        Student bob = new Student("S002", "Bob Smith", 2.8, List.of("CS101"));
        Student carol = new Student("S003", "Carol Williams", 3.9, List.of("CS101", "CS102", "CS201", "CS202"));

        System.out.println("Students:");
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(carol);
        System.out.println();

        // Create processors
        CourseProcessor undergradProcessor = new UndergraduateCourseProcessor();
        CourseProcessor gradProcessor = new GraduateCourseProcessor();
        CourseProcessor labProcessor = new LabCourseProcessor();

        // Test 1: Undergraduate course enrollment
        System.out.println("Test 1: Undergraduate Course (CS201)");
        System.out.println("Prerequisites: CS101, CS102");
        System.out.println("Capacity: 25/30");

        boolean result1 = undergradProcessor.enrollStudent(
            alice, "CS201", List.of("CS101", "CS102"), 25, 30
        );
        System.out.println("Alice enrolled: " + result1 + " (Expected: true)\n");

        boolean result2 = undergradProcessor.enrollStudent(
            bob, "CS201", List.of("CS101", "CS102"), 25, 30
        );
        System.out.println("Bob enrolled: " + result2 + " (Expected: false - missing CS102)\n");

        // Test 2: Graduate course enrollment
        System.out.println("Test 2: Graduate Course (CS501)");
        System.out.println("Prerequisites: CS201, CS202");
        System.out.println("Capacity: 15/20");
        System.out.println("Requires GPA >= 3.0");

        boolean result3 = gradProcessor.enrollStudent(
            carol, "CS501", List.of("CS201", "CS202"), 15, 20
        );
        System.out.println("Carol enrolled: " + result3 + " (Expected: true)\n");

        boolean result4 = gradProcessor.enrollStudent(
            bob, "CS501", List.of("CS101"), 15, 20
        );
        System.out.println("Bob enrolled: " + result4 + " (Expected: false - GPA too low)\n");

        // Test 3: Lab course enrollment
        System.out.println("Test 3: Lab Course (LAB301)");
        System.out.println("Prerequisites: CS201");
        System.out.println("Capacity: 18/20 (reserves 2 spots for equipment)");

        boolean result5 = labProcessor.enrollStudent(
            alice, "LAB301", List.of("CS201"), 18, 20
        );
        System.out.println("Alice enrolled: " + result5 + " (Expected: false - at capacity limit)\n");

        boolean result6 = labProcessor.enrollStudent(
            alice, "LAB301", List.of("CS201"), 17, 20
        );
        System.out.println("Alice enrolled: " + result6 + " (Expected: true)\n");

        // Test 4: Course at full capacity
        System.out.println("Test 4: Full Course");
        boolean result7 = undergradProcessor.enrollStudent(
            alice, "CS999", List.of(), 30, 30
        );
        System.out.println("Alice enrolled in full course: " + result7 + " (Expected: false)\n");

        // Test 5: Polymorphism demonstration
        System.out.println("Test 5: Polymorphism - same interface, different behavior");
        CourseProcessor[] processors = {
            undergradProcessor,
            gradProcessor,
            labProcessor
        };

        for (CourseProcessor processor : processors) {
            System.out.println("Testing with: " + processor.getClass().getSimpleName());
            processor.enrollStudent(alice, "TEST101", List.of(), 5, 20);
        }
    }*/
}
