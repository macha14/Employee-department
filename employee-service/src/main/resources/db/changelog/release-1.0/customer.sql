SELECT d.dept_name, count(e.emp_Id) FROM employee e
INNER JOIN department d
ON e.dept_id = d.dept_id
GROUP BY e.dept_id;


SELECT d.dept_name, avg(e.salary) from department d
inner join employee e ON e.dept_id = d.dept_id
group by e.dept_id having avg(e.salary) > 5000;

SELECT e.emp_name, ifnull(d.dept_name, 'UNKNOWN') from employee e
left join department d ON e.dept_id = d.dept_id

select * from employee e where e.dept_id = ? order by e.salary desc limit ?;

select * from employee e left join department d on e.dept_id = d.dept_id
where salary = (select max(salary) from employee);

select * from employee e where e.emp_name like 'sh%';

select * from employee e where e.emp_name like '%sh';

select * from employee e where e.emp_name like '%sh%';



