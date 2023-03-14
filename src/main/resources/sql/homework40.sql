use online_school;

# 1 sorted STUDENT from persons by name
select * from persons where role='STUDENT' order by name;

# 2 name lecture and amount of add materials where create date < 2023
select  lectures.name, lectures.create_date, count(add_materials.lecture_id) as 'numb add materials'
from lectures, add_materials
where  lectures.id = add_materials.lecture_id
  and year(lectures.create_date) < '2023'
group by lectures.name, lectures.create_date
order by lectures.create_date;

#3
select    lectures.name, count(homework.lecture_id) as 'numb_homework'
from lectures, homework
where lectures.id = homework.lecture_id
  and lectures.create_date = (select min(lectures.create_date) from lectures)
group by lectures.name
order by numb_homework desc
limit 1;

#4
select resource_type, count(resource_type) as 'numb_type'
from add_materials
group by  resource_type;

#5
select *
from persons
where role = 'TEACHER'
  and name regexp  '^[А-Н[A-N]]';

#6
select persons.name, persons.last_name, count(*) as 'numb_course'
from persons, courses_persons
where persons.role = 'student'
  and persons.id = courses_persons.person_id
group by persons.name, persons.id
order by persons.name;