create table admin (
                       id bigint not null,
                       login varchar(255),
                       password varchar(255),
                       primary key (id)
) engine=InnoDB
create table group_stud (
                            id bigint not null,
                            group_number varchar(255),
                            primary key (id)
) engine=InnoDB
create table group_stud_lessons (
                                    group_id bigint not null,
                                    lessons_id bigint not null,
                                    primary key (group_id, lessons_id)
) engine=InnoDB
create table group_stud_student_list (
                                         group_id bigint not null,
                                         student_list_id bigint not null
) engine=InnoDB

create table hibernate_sequence (
    next_val bigint
) engine=InnoDB
    insert into hibernate_sequence values ( 1 )
create table lesson (
                        id bigint not null,
                        amount_of_hours varchar(255),
                        lesson_name varchar(255),
                        teacher_name varchar(255),
                        type_of_lesson varchar(255),
                        type_on_session varchar(255),
                        group_to_lesson_id bigint,
                        primary key (id)
) engine=InnoDB
create table student (
                         id bigint not null,
                         address varchar(255),
                         credit_book varchar(255),
                         date_of_birth datetime(6),
                         name varchar(255),
                         phone_number varchar(255),
                         student_leader bit not null,
                         surname varchar(255),
                         group_id bigint,
                         primary key (id)
) engine=InnoDB
alter table group_stud_lessons
drop index UK_3k9hwt28mkgsm0n4vpdukfpqp
    Hibernate:

alter table group_stud_lessons
    add constraint UK_3k9hwt28mkgsm0n4vpdukfpqp unique (lessons_id)
    Hibernate:

alter table group_stud_student_list
drop index UK_jvsarmq42w5k1v486x2nbumw1
    Hibernate:

alter table group_stud_student_list
    add constraint UK_jvsarmq42w5k1v486x2nbumw1 unique (student_list_id)
    Hibernate:

alter table group_stud_lessons
    add constraint FK1he6tww0vlx9717cs5bc6gwuu
        foreign key (lessons_id)
            references lesson (id)
    Hibernate:

alter table group_stud_lessons
    add constraint FKtroxras8mj6iy9ddy04kv5sif
        foreign key (group_id)
            references group_stud (id)
    Hibernate:

alter table group_stud_student_list
    add constraint FKcawa424pgjicqckdtj12ony84
        foreign key (student_list_id)
            references student (id)
    Hibernate:

alter table group_stud_student_list
    add constraint FKp4srlwsbu0ouiopq9ftrs2yue
        foreign key (group_id)
            references group_stud (id)
    Hibernate:

alter table lesson
    add constraint FK3b181hjfkq9sp0r9vyjfnv3ap
        foreign key (group_to_lesson_id)
            references group_stud (id)
    Hibernate:

alter table student
    add constraint FKmgs5ayn3tqccx2qb5q5q1w801
        foreign key (group_id)
            references group_stud (id)