create DATABASE lettery
go
use lettery
GO
create table userlist
(
    userID int primary key IDENTITY(1,1),
    userName varchar(50),
    userPwd varchar(50)
)
go
create table letterynum
(
    letteryID int primary key IDENTITY(1,1),
    letteryNumber int,
    letterycount int
)
insert into userlist(userName,userPwd) values('tommy','abc123')
select * from userlist
select * from letterynum