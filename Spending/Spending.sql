drop table if exists `spending`.`storeday`;
create table `spending`.`storeday`(
`id` int not null auto_increment,
`year` smallint not null,
`month` tinyint not null,
`day` tinyint not null,
`spend` int ,
primary key(`id`)
) auto_increment=1;

drop table if exists `spending`.`statistic`;
create table `spending`.`statistic`(
`id` int not null auto_increment,
`year` smallint not null,
`month` tinyint not null,
`total` float ,
`average` float,
primary key(`id`)
) auto_increment=1;
use spending;
create table history(
`id` int(11) auto_increment,
`spending_id` int not null,
`action` varchar(150),
`action_date_time` timestamp,
primary key(`id`),
foreign key(`spending_id`) references storeday(id) 
)auto_increment=1;

