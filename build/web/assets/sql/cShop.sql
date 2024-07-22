create database cshop;
use cshop;

create table dboAccount(
Id bigint identity(1,1),
username varchar(50) not null,
password varchar(20) not null,
role varchar(10) not null,
IsActive bit,
IsDelete bit,
primary key (Id)
);

create table dboUser(
Id bigInt identity(1,1),
FullName nvarchar(50) not null,
Email varchar(50) not null,
Sex nvarchar(10) not null,
Address nvarchar(200),
PictureUrl varchar(100),
DOB date,
AccountId bigint not null,
primary key (Id),
foreign key (AccountId) references dboAccount(Id)
);

create table dboCategory(
Id bigint identity(1,1),
Name nvarchar(100) not null,
PictureUrl varchar(100),
NumberProduct int,
Description nvarchar(2000),
IsDelete bit,
primary key (Id)
);

create table dboProduct(
Id bigint identity(1,1),
Name nvarchar(100) not null,
PictureUrl varchar(100),
QuantitySold int,
QuantiyStock int,
CategoryId bigint not null,
IsDelete bit,
Description nvarchar(1000),
primary key(Id),
foreign key (CategoryId) references dboCategory(Id)
);

create table dboProductDetail(
Id bigint identity(1,1),
Size varchar(10) not null,
Color nvarchar(100) not null,
Price decimal(19,2) not null,
QuantitySold int not null,
QuantityStock int not null,
Description nvarchar (2000),
ProductId bigint not null,
PictureUrl varchar(100),
primary key (Id),
foreign key (ProductId) references dboProduct(Id)
);

create table dboOrder(
Id bigint identity(1,1),
OrderAt datetime not null,
UserId bigint not null,
TotalPrice decimal(19,2),
Description varchar(2000),
IsDelete bit,
Status varchar(50),
primary key(Id),
foreign key (UserId) references dboUser(Id)
);

create table dboOrderDetail(
Id bigint identity(1,1),
OrderId bigint not null,
ProductDetailId bigint not null,
Quantity int not null,
TotalPrice decimal(19,2),
Description varchar(2000),
primary key (Id),
foreign key (OrderId) references dboOrder(Id),
foreign key (ProductDetailId) references dboProductDetail(Id)
);

create table dboAddToCart(
Id bigint identity(1,1),
Username varchar(50) not null,
ProductDetailId bigint not null,
Quantity int not null,
primary key(Id),
foreign key (ProductDetailId) references dboProductDetail(Id)
);

insert into dboAccount(username, password, role, IsActive, IsDelete)
values ('rootadmin', 'Abc@123456', 'ADMIN', 1, 0);
insert into dboUser(FullName, Email, Sex, Address, PictureUrl, DOB, AccountId)
values ('RootAdmin','admin@gmail.com', 'NAM', 'HaNoi', '','2001/10/09',1);

insert into dboAccount(username, password, role, IsActive, IsDelete)
values ('member2', 'Abc@123456', 'MEMBER', 1, 0);
insert into dboUser(FullName, Email, Sex, Address, PictureUrl, DOB, AccountId)
values ('Member2','member2@gmail.com', 'NAM', 'HaNoi', '','2001/10/09',2);


select * from dboAccount;
select * from dboUser;

