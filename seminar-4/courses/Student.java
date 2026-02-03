package courses;

import java.util.List;

public record Student(
    String id,
    String name,
    double gpa,
    List<String> completedCourses
) {
}
