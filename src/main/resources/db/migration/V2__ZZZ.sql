alter table users
add email VARCHAR(50) not null default 'Неизвестно';

UPDATE users
set email = case
    when id = 1 then 'egor_super_krutoy1337@gmail.com'
    when id = 2 then 'Dimasik228_1337_pro@gmail.com'
    when id = 3 then 'xXx_Dan4ek_2003_xXx@yandex.ru'
    else email
end;
