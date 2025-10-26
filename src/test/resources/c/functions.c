#include <stdio.h>

typedef struct {
    int id;
    char name[50];
    double score;
} Student;

void printStudent(Student s) {
    printf("ID: %d\\n", s.id);
    printf("Name: %s\\n", s.name);
    printf("Score: %.2f\\n", s.score);
}

int main() {
    Student st = {1, "Alice", 92.5};
    printStudent(st);
    return 0;
}
