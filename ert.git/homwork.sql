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
    letteryNumber1st int,
    letteryNumber2rd int,
    letteryNumber3rd int,
    letteryNumber4th int,
    letteryNumber5th int,
    letteryNumber6th int,
)
insert into userlist(userName,userPwd) values('tommy','abc123')
go
