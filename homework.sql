create database School
on primary(

name=school,
filename='c:\temp\school.mdf',
size=10mb,
maxsize=50mb,
FILEGROWTH= 20 %) 
log on(
name =schoolLog,
filename='c:\temp\schoolLog.ldf',
size=10mb,
maxsize=30MB,
filegrowth=10MB
)
go
use School
go
create table COURSE(
Courseid int not null identity(1,1) primary key,
CourseName nvarchar(50) not null,
Address nvarchar(50) not null
,price int not null)
create table STUDENT
(
StudentId int not null identity(1,1) primary key,
StuName nvarchar(50) not null,
StuAddress nvarchar(50),
StuPhone  nvarchar(20)

)
create table GRADERESULT
(
GradeId int Not NULL identity(1,1) primary key,
studentId int  REFERENCES STUDENT(studentId) not null,
CourseId int REFERENCES COURSE(CourseId)  Not NULL  ,
Grade int check( grade>0 and grade<100 ),
Remark  nvarchar(50)

)

insert STUDENT(Stuname,StuAddress,StuPhone) 
values
('jack','tapei','0912345678'),
('mary','tapei','0912345578'),
('peter','NewTapei','0915645678'),
('tom','taichung','0912345678'),
('mark','tainan','0912345648')
insert into COURSE(CourseName,Address,price)
values
('Mandarin','201',50),
('english','202',65),
('Math','203',70),
('Social','204',60),
('Nature','205',80)
insert into GRADERESULT(studentId,CourseId,Grade) 
values(1,1,70),(1,2,70),(1,3,60),(1,4,68),(1,5,90),
(2,1,59),(2,2,75),(2,3,80),(2,4,69),(2,5,45),
(3,1,45),(3,2,67),(3,3,83),(3,4,51),(3,5,55),
(4,1,98),(4,2,64),(4,3,32),(4,4,65),(4,5,72),
(5,1,55),(5,2,47),(5,3,59),(5,4,72),(5,5,32)
select * from GRADERESULT
go

create view STATSCLASS
as
select top(5) stu.StudentId, stu.StuName,sum(gra.Grade)as'總分'  from STUDENT as stu
join GRADERESULT as gra
on stu.StudentId=gra.studentId
group by StuName,stu.StudentId 
order by '總分' desc
go
select * from STATSCLASS
go
create trigger updataID
on STATSCLASS
instead of update
as
begin
begin transaction
try 
print @@ERROR
if (@@ERROR!=0)
begin
raiserror ('error data',11,1)
Rollback transaction
end
else
begin
commit transaction
end
end
go

create view insertcheck
as select * from GRADERESULT
go
create trigger insertID
on insertcheck
instead of insert
as
begin transaction
if exists(select * from insertcheck where studentId=(select studentId from inserted)and exists(select * from insertcheck where CourseId=(select CourseId from inserted) ))
begin
update insertcheck
set insertcheck.grade=inserted.grade 
from insertcheck join inserted on insertcheck.studentId=inserted.studentId
where insertcheck.CourseId=inserted.CourseId
commit transaction
end
else
rollback transaction
go
