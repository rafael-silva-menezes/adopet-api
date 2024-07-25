create table adoptions
(
    id            bigint       not null auto_increment,
    date          datetime     not null,
    guardian_id   bigint       not null,
    pet_id        bigint       not null,
    reason        varchar(255) not null,
    status        varchar(100) not null,
    justification varchar(255),
    primary key (id),
    constraint fk_adoptions_guardian_id foreign key (guardian_id) references shelters (id),
    constraint fk_adoptions_pet_id foreign key (pet_id) references pets (id)
);