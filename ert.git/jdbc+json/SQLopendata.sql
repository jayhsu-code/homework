create DATABASE opendata
go
use opendata
go
create table weatheropendate
(
    stationId nvarchar(50) ,
    locationName nvarchar(50)not null,
    city nvarchar(50)not null,
    Town nvarchar(50)not null,
    hour6 float not null,
    hour12 float not null,
    hour24 float not null,
    datatime datetime
)
go
 create trigger repeatdata
 on weatheropendate  
 INSTEAD OF INSERT
 as 

 if exists(select * from weatheropendate where stationId=(select stationId from inserted))
 BEGIN
 UPDATE weatheropendate 
    set 
    weatheropendate.hour6=inserted.hour6,
    weatheropendate.hour12=inserted.hour12,
    weatheropendate.hour24=inserted.hour24,
    weatheropendate.datatime=inserted.datatime
    FROM weatheropendate join inserted on weatheropendate.stationId=inserted.stationId
 END
 ELSE
 BEGIN
   INSERT into weatheropendate(stationId,locationName,city,Town,hour6,hour12,hour24,datatime) select stationId,locationName,city,Town,hour6,hour12,hour24,datatime from inserted
 END
