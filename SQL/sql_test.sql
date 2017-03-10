--user sql test

insert 
	into users
values ('gorud11231', '박해경', '1234', sysdate);

select * 
	from users;
	
	
commit;

--blog sql test
insert
	into blog
values ('gorud1123', '하이', '로고.jpg');

update blog
   set title='title', logo='logo.jpg'
 where users_id='b';
  
select users_id, title, logo from blog;

select users_id as usersId, 
				   title, 
				   logo 
		      from blog
		      where users_id='b';
			  
select * from blog;



commit;

--category sql test
insert 
	into category
values ( seq_category.nextval,
		 '박해경', 
		 '얄랄라', 
		 sysDate, 
		 'gorud1123');
		 
select cno,
	   name,
	   description,
	   reg_date,
	   blog_id
  from category
 order by cno;
 
delete 
  from category
 where cno=1;
 
select * from category;


--sql test post
insert
		into post
	values (1,'gg','ddd',sysdate, 47);
	
delete from post where pno=1;

select max(pno),
	   c.BLOG_ID
  from post a, CATEGORY c
 where c.BLOG_ID ='gorud1123'  and c.CNO = a.CNO
 group by c.blog_id;
			
select a.PNO,
	   a.TITLE, 
	   a.CONTENT, 
	   a.REG_DATE  
  from post a, CATEGORY c
 where c.CNO = a.CNO and c.BLOG_ID ='gorud1123';


commit;