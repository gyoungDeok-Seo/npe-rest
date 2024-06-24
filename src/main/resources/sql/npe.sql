create table tbl_member
(
    id                bigint auto_increment primary key,
    created_date      datetime default current_timestamp,
    updated_date      datetime default current_timestamp,
    kakao_profile_url varchar(255) not null,
    kakao_email       varchar(255) not null,
    member_name       varchar(255) not null,
    member_position   varchar(255) not null,
    member_intro      varchar(255),
    status            tinyint  default 1
);

create table tbl_skill
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    skill_name   varchar(255)
);

create table tbl_member_skill
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    member_id    bigint,
    skill_id     bigint,
    constraint fk_member_skill_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_member_skill_skill foreign key (skill_id)
        references tbl_skill (id)
);

create table tbl_career
(
    id              bigint auto_increment primary key,
    created_date    datetime default current_timestamp,
    updated_date    datetime default current_timestamp,
    company_name    varchar(255) not null,
    member_position varchar(255) not null,
    career_start    date         not null,
    career_end      date,
    description     varchar(1000),
    career_url      varchar(255),
    status          tinyint  default 1,
    member_id       bigint,
    constraint fk_career_member foreign key (member_id)
        references tbl_member (id)
);

create table tbl_industry
(
    id            bigint auto_increment primary key,
    created_date  datetime default current_timestamp,
    updated_date  datetime default current_timestamp,
    industry_name varchar(255) not null
);

create table tbl_career_industry
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    career_id    bigint,
    industry_id  bigint,
    constraint fk_career_industry_career foreign key (career_id)
        references tbl_career (id),
    constraint fk_career_industry_industry foreign key (industry_id)
        references tbl_industry (id)
);

create table tbl_career_skill
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    career_id    bigint,
    skill_id     bigint,
    constraint fk_career_skill_career foreign key (career_id)
        references tbl_career (id),
    constraint fk_career_skill_skill foreign key (skill_id)
        references tbl_skill (id)
);

create table tbl_education
(
    id                    bigint auto_increment primary key,
    created_date          datetime default current_timestamp,
    updated_date          datetime default current_timestamp,
    education_institution varchar(255) not null,
    education_course      varchar(255),
    education_start       date         not null,
    education_end         date,
    education_url         varchar(255),
    description           varchar(1000),
    status                tinyint  default 1,
    member_id             bigint,
    constraint fk_education_member foreign key (member_id)
        references tbl_member (id)
);

create table tbl_social_link
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    social_url   varchar(255) not null,
    social_name  varchar(255) not null,
    member_id    bigint,
    constraint fk_social_link_member foreign key (member_id)
        references tbl_member (id)
);

create table tbl_category
(
    id             bigint auto_increment primary key,
    created_date   datetime default current_timestamp,
    updated_date   datetime default current_timestamp,
    category_name  varchar(255) not null,
    category_value varchar(255) not null
);

create table tbl_question
(
    id               bigint auto_increment primary key,
    created_date     datetime default current_timestamp,
    updated_date     datetime default current_timestamp,
    question_title   varchar(255) not null,
    question_content varchar(255) not null,
    status           tinyint  default 1,
    member_id        bigint,
    category_id      bigint,
    constraint fk_question_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_question_category foreign key (category_id)
        references tbl_category (id)
);

create table tbl_file
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    file_name    varchar(255) not null,
    file_path    varchar(255) not null,
    question_id  bigint,
    constraint fk_file_question foreign key (question_id)
        references tbl_question (id)
);

create table tbl_tag
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    tag_name     varchar(255) not null,
    status       tinyint  default 1,
    question_id  bigint,
    constraint fk_tag_question foreign key (question_id)
        references tbl_question (id)
);

create table tbl_answer
(
    id             bigint auto_increment primary key,
    created_date   datetime default current_timestamp,
    updated_date   datetime default current_timestamp,
    answer_content varchar(255) not null,
    status         tinyint  default 1,
    member_id      bigint,
    question_id    bigint,
    constraint fk_answer_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_answer_question foreign key (question_id)
        references tbl_question (id)
);

create table tbl_answer_like
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    status       tinyint  default 1,
    member_id    bigint,
    answer_id    bigint,
    constraint fk_answer_like_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_answer_like_answer foreign key (answer_id)
        references tbl_answer (id)
);

create table tbl_answer_reply
(
    id             bigint auto_increment primary key,
    created_date   datetime default current_timestamp,
    updated_date   datetime default current_timestamp,
    replay_content varchar(255) not null,
    status         tinyint  default 1,
    member_id      bigint,
    answer_id      bigint,
    constraint fk_answer_reply_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_answer_reply_answer foreign key (answer_id)
        references tbl_answer (id)
);

create table tbl_answer_reply_like
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    status       tinyint  default 1,
    member_id    bigint,
    reply_id     bigint,
    constraint fk_answer_reply_like_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_answer_reply_like_reply foreign key (reply_id)
        references tbl_answer_reply (id)
);

create table tbl_alarm
(
    id           bigint auto_increment primary key,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    alarm_type   varchar(255) not null,
    target_id    bigint       not null,
    status       tinyint  default 1,
    sender_id    bigint,
    receiver_id  bigint,
    constraint fk_alarm_sender foreign key (sender_id)
        references tbl_member (id),
    constraint fk_alarm_receiver foreign key (receiver_id)
        references tbl_member (id)
);

select *
from tbl_member;
select *
from tbl_skill;
select *
from tbl_member_skill;

select count(al.id) from tbl_answer a
join tbl_answer_like al on a.id = al.answer_id
where a.id = 74 and a.member_id = 2 and a.status = 1 and al.status = 1;

select a.id, a.created_date, a.updated_date, question_title, question_content, answer_content, a.status, a.member_id, question_id from tbl_question q
join tbl_answer a on q.id = a.question_id and q.status =1
where a.member_id = 2 and a.status = 1
order by id desc
limit 20
offset 0;

select surbquery.id,
       surbquery.question_title,
       surbquery.question_content,
       surbquery.status,
       surbquery.category_id,
       surbquery.member_id,
       surbquery.created_date,
       surbquery.member_name,
       surbquery.member_position,
       surbquery.hits,
       answerCnt
from
(
SELECT q.id,
       q.question_title,
       q.question_content,
       q.status,
       q.category_id,
       q.member_id,
       q.created_date,
       m.member_name,
       m.member_position,
       q.hits,
       COUNT(DISTINCT a.id) AS answerCnt
FROM tbl_question q
JOIN tbl_member m ON q.member_id = m.id AND m.status = 1
LEFT JOIN tbl_answer a ON q.id = a.question_id AND a.status = 1
# JOIN tbl_tag t ON t.id = t.question_id AND t.tag_name in ('react')
WHERE q.status = 1
and q.category_id = (
    select id from tbl_category
    where category_value = 'java'
)
GROUP BY q.id, q.question_title, q.question_content, q.status, q.category_id, q.member_id, q.created_date, m.member_name, m.member_position, q.hits
) as surbquery
JOIN tbl_tag t ON surbquery.id = t.question_id AND t.tag_name like concat('%','','%')
GROUP BY surbquery.id
ORDER BY surbquery.id DESC;

SELECT q.id,
       q.question_title,
       q.question_content,
       q.status,
       q.category_id,
       q.member_id,
       q.created_date,
       m.member_name,
       m.member_position,
       q.hits,
       COUNT(DISTINCT a.id) AS answerCnt
FROM tbl_question q
JOIN tbl_member m ON q.member_id = m.id AND m.status = 1
LEFT JOIN tbl_answer a ON q.id = a.question_id AND a.status = 1
JOIN tbl_tag t ON t.id = t.question_id AND t.tag_name like concat('%','','%')
WHERE q.status = 1
and q.category_id = (
    select id from tbl_category
    where category_value = 'c'
)
GROUP BY q.id, q.question_title, q.question_content, q.status, q.category_id, q.member_id, q.created_date, m.member_name, m.member_position, q.hits;

<select id="selectQnaList" resultType="QnaDTO">
SELECT q.id,
       q.question_title,
       q.question_content,
       q.status,
       q.category_id,
       q.member_id,
       q.created_date,
       m.member_name,
       m.member_position,
       q.hits,
       COUNT(DISTINCT a.id) AS answerCnt
FROM tbl_question q
         LEFT JOIN tbl_member m ON q.member_id = m.id AND m.status = 1
         LEFT JOIN tbl_answer a ON q.id = a.question_id AND a.status = 1
         LEFT JOIN tbl_tag t ON q.id = t.question_id
WHERE q.status = 1
  AND m.status = 1
    <if test="tags != null and tags.size() > 0">
        AND EXISTS (
            SELECT 1
            FROM tbl_tag t2
            WHERE t2.question_id = q.id
            AND t2.tag_name IN
            <foreach item="tagName" index="index" collection="tags" open="(" separator="," close=")">
                #{tagName}
            </foreach>
        )
    </if>
    <if test="categoryValue != null">
        AND q.category_id = (
            SELECT id
            FROM tbl_category
            WHERE category_value = #{categoryValue}
        )
    </if>
GROUP BY q.id, q.question_title, q.question_content, q.status, q.category_id, q.member_id, q.created_date,
    m.member_name, m.member_position, q.hits
ORDER BY q.id DESC
    LIMIT #{pagination.rowCount}
    OFFSET #{pagination.startRow}
    </select>

select * from tbl_question
where category_id = 1;

select id, created_date, question_title, question_content, hits
from tbl_question
where (question_title like concat('%', '아니', '%') or question_content like concat('%', '아니', '%')) and status = 1
order by id desc;

select q.id, member_name, member_position, question_title from tbl_member m
join tbl_question q on m.id = q.member_id and m.status = 1 and q.status = 1
where q.created_date >= date_sub(curdate(), interval weekday(curdate()) + 7 day)
and q.created_date < date_sub(curdate(), interval weekday(curdate()) day)
order by q.hits desc
limit 10
offset 0;