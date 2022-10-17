create table key_pix (
                    id varchar(36) not null,
                    account_holder_last_name varchar(45),
                    account_holder_first_name varchar(255) not null,
                    account_number varchar(8) not null,
                    account_type varchar(10) not null,
                    agency_number varchar(4) not null,
                    name varchar(255) not null,
                    type varchar(9) not null,
                    value varchar(77) not null,
                    primary key (id)
) engine=MyISAM;