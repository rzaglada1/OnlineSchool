#  homework 39

create database if not exists online_school;

# create table  courses
CREATE TABLE online_school.courses
(
    id bigint,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    primary key auto_increment(id)
);

# create table lectures
CREATE TABLE online_school.lectures
(
    id bigint,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    course_id bigint not null,
    primary key auto_increment(id),
    constraint lecture_fk foreign key (course_id) references courses (id) on delete cascade
);

# create table add_materials
CREATE TABLE online_school.add_materials
(
    id bigint,
    name varchar(255),
    create_count bigint,
    create_date datetime(6),
    resource_type enum ('URL', 'VIDEO', 'BOOK'),
    lecture_id bigint not null,
    primary key auto_increment(id),
    constraint lecture_add_materials_fk foreign key (lecture_id) references lectures (id) on delete cascade
);






