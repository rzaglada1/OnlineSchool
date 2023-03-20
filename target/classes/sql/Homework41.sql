# 1
use online_school;
SELECT l.name, l.lecture_date, p.name, p.last_name
from lectures l
         JOIN persons p  ON l.person_id = p.id
WHERE p.role = 'TEACHER'
ORDER BY l.lecture_date
;

# 2
SELECT p.name, p.last_name, COUNT(l.id) AS numb_lectures
from persons p
         JOIN lectures l ON l.person_id = p.id
WHERE p.role = 'TEACHER'
GROUP BY p.name, p.last_name
;

#3
SELECT l.name, l.lecture_date, p.name, p.last_name
FROM lectures l
         JOIN persons p ON l.person_id = p.id
WHERE p.role = 'TEACHER' AND p.id = 5
ORDER BY l.lecture_date
;

#4
SELECT c.name,
       COUNT(DISTINCT l.id) AS numb_lectures,
       COUNT(DISTINCT h.id) AS numb_homeworks,
       COUNT(DISTINCT am.id) AS numb_addMaterials,
       COUNT(DISTINCT p.id) AS numb_teacher,
       COUNT(DISTINCT persons.id) AS numb_student

FROM courses c
         JOIN lectures l ON c.id = l.course_id
         LEFT JOIN homework h ON l.id = h.lecture_id
         LEFT JOIN add_materials am ON l.id = am.lecture_id
         LEFT JOIN persons p ON l.person_id = p.id
         LEFT JOIN courses_persons cp ON c.id = cp.course_id
         LEFT JOIN persons  ON cp.person_id = persons.id

GROUP BY c.name
;

#5
SELECT monthname(l.lecture_date), COUNT(l.id) AS numb_lectures
FROM lectures l
GROUP BY monthname(l.lecture_date)
;

#6
SELECT 'homework' AS objects, COUNT(id) AS count
FROM homework
UNION
SELECT 'materials' AS objects, COUNT(add_materials.id) AS count
FROM add_materials

ORDER BY count DESC
LIMIT 1
;