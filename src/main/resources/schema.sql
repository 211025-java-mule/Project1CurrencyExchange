create table if not exists currency(
                                       id serial primary key,
                                       success boolean,
                                       timestamp timestamp,
                                       base text,
                                       date text
);

create table if not exists rate(
                                   id serial primary key,
                                   currency int,
                                   name text,
                                   value decimal,
                                   constraint fk_currency foreign key(currency) references currency(id)
    );
drop table currency;
select * from rate where rate.name='PLN';
select * from currency join rate on currency.id=rate.currency where rate.name='AED';