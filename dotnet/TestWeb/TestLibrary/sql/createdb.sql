drop table tcustomer;
drop table torder;
create table tcustomer (id bigint, customerid varchar(255));
create table torder (id bigint, ordernumber varchar(255), fk_customer bigint);

insert into tcustomer(id, customerid) values(1, 'CUSTOMER1');
insert into tcustomer(id, customerid) values(2, 'CUSTOMER2');

insert into torder(id, ordernumber, fk_customer) values(1, 'ORDER1', 1);
insert into torder(id, ordernumber, fk_customer) values(2, 'ORDER1', 2);