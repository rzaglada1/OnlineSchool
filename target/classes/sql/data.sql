
# courses
INSERT INTO courses (name, create_date)
VALUES ('Java', now());
INSERT INTO courses (name, create_date)
VALUES ('Python', now());
INSERT INTO courses (name, create_date)
VALUES ('C++', now());

# person
INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Романенко', 'Наталія', now(), '+380987654534', 'Romanenko@gmail.com', 'STUDENT');
INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Водерацький', 'Михайло', now(), '+380927554536', 'Voderackiy@gmail.com', 'STUDENT');
INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Ломачевська', 'Олена', now(), '+380983333534', 'Lomachevska@gmail.com', 'STUDENT');
INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Заворотнюк', 'Галина', now(), '+380987654298', 'Zavorotnyok@gmail.com', 'STUDENT');

INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Ройда', 'Іван', now(), '+380987657408', 'Royda@gmail.com', 'TEACHER');
INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Палкін', 'Сергій', now(), '+380989484298', 'Palkin@gmail.com', 'TEACHER');
INSERT INTO persons (name, last_name, create_date, phone, email, role)
VALUES ('Зоря', 'Олена', now(), '+380989484444', 'Zorya@gmail.com', 'TEACHER');

# students in courses
INSERT INTO courses_persons (course_id, person_id)
VALUES (1, 1);
INSERT INTO courses_persons (course_id, person_id)
VALUES (1, 2);
INSERT INTO courses_persons (course_id, person_id)
VALUES (1, 3);
INSERT INTO courses_persons (course_id, person_id)
VALUES (2, 4);
INSERT INTO courses_persons (course_id, person_id)
VALUES (2, 3);
INSERT INTO courses_persons (course_id, person_id)
VALUES (3, 4);
INSERT INTO courses_persons (course_id, person_id)
VALUES (3, 1);

# lectures
INSERT INTO lectures (name, create_date, lecture_date, course_id, person_id)
VALUES ('Introduce in Java', now(), date('2022-03-11 20:35:27.000000'), 1, 5);
INSERT INTO lectures (name, create_date, lecture_date, course_id, person_id)
VALUES ('Lecture 2 Java', now(), date('2021-03-11 20:35:27.000000'), 1, 5);
INSERT INTO lectures (name, create_date, lecture_date, course_id, person_id)
VALUES ('Lecture 3 Java', now(), date('2021-03-11 20:35:27.000000'), 1, 5);
INSERT INTO lectures (name, create_date, lecture_date, course_id, person_id)
VALUES ('Lecture 1 Python', now(),now(), 2, 6);
INSERT INTO lectures (name, create_date, lecture_date, course_id, person_id)
VALUES ('Lecture 2 Python', now(),now(), 2, 6);
INSERT INTO lectures (name, create_date, lecture_date, course_id, person_id)
VALUES ('Lecture 1 C++', now(),now(), 3, 7);


# add materials
INSERT INTO add_materials (name, create_date, resource_type, lecture_id)
VALUES ('Add materials 1', now(), 'BOOK', 1);
INSERT INTO add_materials (name, create_date, resource_type, lecture_id)
VALUES ('Add materials 2', now(), 'VIDEO', 1);
INSERT INTO add_materials (name, create_date, resource_type, lecture_id)
VALUES ('Add materials 3', now(), 'URL', 1);
INSERT INTO add_materials (name, create_date, resource_type, lecture_id)
VALUES ('Add materials 4', now(), 'URL', 2);
INSERT INTO add_materials (name, create_date, resource_type, lecture_id)
VALUES ('Add materials 5', now(), 'BOOK', 2);

# homework
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 1', now(),  1);
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 2', now(),  1);
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 3', now(),  2);
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 4', now(),  2);
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 5', now(),  2);
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 6', now(),  3);
INSERT INTO homework (name, create_date, lecture_id)
VALUES ('Homework 7', now(),  4);




