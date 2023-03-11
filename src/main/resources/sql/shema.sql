#  homework 39

create database if not exists online_school;

# create table  courses
CREATE TABLE online_school.courses
(
    id bigint auto_increment,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    primary key (id)
);

# create table lectures
CREATE TABLE online_school.lectures
(
    id bigint auto_increment,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    course_id bigint not null,
    primary key (id),
    constraint lecture_fk foreign key (course_id) references courses (id) on delete cascade
);

# create table add_materials
CREATE TABLE online_school.add_materials
(
    id bigint auto_increment,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    resource_type enum ('URL', 'VIDEO', 'BOOK'),
    lecture_id bigint not null,
    primary key (id),
    constraint lecture_add_materials_fk foreign key (lecture_id) references lectures (id) on delete cascade
);

# create table homework
CREATE TABLE online_school.homework
(
    id bigint auto_increment,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    lecture_id bigint not null,
    primary key (id),
    constraint lecture_homework_fk foreign key (lecture_id) references lectures (id) on delete cascade
);

# create table person
CREATE TABLE online_school.persons
(
    id bigint auto_increment,
    name varchar(255),
    last_name varchar(255),
    create_count bigint,
    create_date datetime(6),
    phone varchar(255),
    email varchar(255),
    role enum ('STUDENT', 'TEACHER'),
    primary key (id)
);



# create table for relies ManyToMany  person && course
create table online_school.courses_persons
(
    course_id bigint NOT NULL,
    person_id bigint NOT NULL,
    foreign key  (course_id) references courses (id) on delete cascade,
    foreign key  (person_id) references persons (id) on delete cascade,
    UNIQUE (course_id, person_id)
);