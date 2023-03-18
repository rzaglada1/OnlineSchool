
# courses
INSERT INTO online_school.courses (name, create_count, create_date)
VALUES ('Java', 1, now());
INSERT INTO online_school.courses (name, create_count, create_date)
VALUES ('Python', 2, now());
INSERT INTO online_school.courses (name, create_count, create_date)
VALUES ('C++', 3, now());

# person
INSERT INTO online_school.persons (name, last_name, create_count, create_date, phone, email, role)
VALUES ('Романенко', 'Наталія', 1, now(), '+380987654534', 'Romanenko@gmail.com', 'STUDENT');
INSERT INTO online_school.persons (name, last_name, create_count, create_date, phone, email, role)
VALUES ('Водерацький', 'Михайло', 2, now(), '+380927554536', 'Voderackiy@gmail.com', 'STUDENT');
INSERT INTO online_school.persons (name, last_name, create_count, create_date, phone, email, role)
VALUES ('Ломачевська', 'Олена', 3, now(), '+380983333534', 'Lomachevska@gmail.com', 'STUDENT');
INSERT INTO online_school.persons (name, last_name, create_count, create_date, phone, email, role)
VALUES ('Заворотнюк', 'Галина', 4, now(), '+380987654298', 'Zavorotnyok@gmail.com', 'STUDENT');

INSERT INTO online_school.persons (name, last_name, create_count, create_date, phone, email, role)
VALUES ('Ройда', 'Іван', 5, now(), '+380987657408', 'Royda@gmail.com', 'TEACHER');
INSERT INTO online_school.persons (name, last_name, create_count, create_date, phone, email, role)
VALUES ('Палкін', 'Сергій', 6, now(), '+380989484298', 'Palkin@gmail.com', 'TEACHER');

# students in courses
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (1, 1);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (1, 2);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (1, 3);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (2, 4);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (2, 3);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (3, 5);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (3, 4);
INSERT INTO online_school.courses_persons (course_id, person_id)
VALUES (3, 1);

# lectures
INSERT INTO online_school.lectures (name, create_count, create_date, lecture_date, course_id)
VALUES ('Introduce in Java', 1, now(), date('2022-03-11 20:35:27.000000'), 1);
INSERT INTO online_school.lectures (name, create_count, create_date, lecture_date, course_id)
VALUES ('Lecture 2 Java', 2, now(), date('2021-03-11 20:35:27.000000'), 1);
INSERT INTO online_school.lectures (name, create_count, create_date, lecture_date, course_id)
VALUES ('Lecture 3 Java', 3, now(), date('2021-03-11 20:35:27.000000'), 1);
INSERT INTO online_school.lectures (name, create_count, create_date, lecture_date, course_id)
VALUES ('Lecture 1 Python', 4, now(),now(), 2);
INSERT INTO online_school.lectures (name, create_count, create_date, lecture_date, course_id)
VALUES ('Lecture 2 Python', 5, now(),now(), 2);
INSERT INTO online_school.lectures (name, create_count, create_date, lecture_date, course_id)
VALUES ('Lecture 1 C++', 6, now(),now(), 3);

INSERT INTO online_school.lectures (name, create_count, create_date, course_id)
VALUES ('Lecture 1 Python', 4, now(), 2);
INSERT INTO online_school.lectures (name, create_count, create_date, course_id)
VALUES ('Lecture 2 Python', 5, now(), 2);
INSERT INTO online_school.lectures (name, create_count, create_date, course_id)
VALUES ('Lecture 1 C++', 6, now(), 3);

# add materials
INSERT INTO online_school.add_materials (name, create_count, create_date, resource_type, lecture_id)
VALUES ('Add materials 1', 1, now(), 'BOOK', 1);
INSERT INTO online_school.add_materials (name, create_count, create_date, resource_type, lecture_id)
VALUES ('Add materials 2', 2, now(), 'VIDEO', 1);
INSERT INTO online_school.add_materials (name, create_count, create_date, resource_type, lecture_id)
VALUES ('Add materials 3', 3, now(), 'URL', 1);
INSERT INTO online_school.add_materials (name, create_count, create_date, resource_type, lecture_id)
VALUES ('Add materials 4', 4, now(), 'URL', 2);
INSERT INTO online_school.add_materials (name, create_count, create_date, resource_type, lecture_id)
VALUES ('Add materials 5', 5, now(), 'BOOK', 2);

# homework
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 1', 1, now(),  1);
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 2', 2, now(),  1);
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 3', 3, now(),  2);
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 4', 4, now(),  2);
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 5', 5, now(),  2);
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 6', 6, now(),  3);
INSERT INTO online_school.homework (name, create_count, create_date, lecture_id)
VALUES ('Homework 7', 7, now(),  4);




