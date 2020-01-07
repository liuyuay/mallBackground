select UUID();
SELECT replace(uuid(), '-', '');
# 向权限表中插入数据
insert into myblog.permission values (replace(uuid(), '-', ''),'/admin',sysdate(),sysdate());
insert into myblog.permission values (replace(uuid(), '-', ''),'/test',sysdate(),sysdate());
insert into myblog.permission values (replace(uuid(), '-', ''),'/',sysdate(),sysdate());
select * from myblog.permission;

# 向角色表中插入数据
insert into myblog.role values (replace(uuid(), '-', ''),'admin','管理员','管理员',sysdate(),sysdate());
insert into myblog.role values (replace(uuid(), '-', ''),'visitor','游客','游客',sysdate(),sysdate());
select * from myblog.role;

# 向角色-权限表中插入数据
# 管理员
insert into myblog.rolePermission values (replace(uuid(), '-', ''),'8bbc10932ebd11eaa24152540046b33c','0f5738fa2ebd11eaa24152540046b33c',sysdate(),sysdate());
insert into myblog.rolePermission values (replace(uuid(), '-', ''),'8bbc10932ebd11eaa24152540046b33c','0f8e51892ebd11eaa24152540046b33c',sysdate(),sysdate());
insert into myblog.rolePermission values (replace(uuid(), '-', ''),'8bbc10932ebd11eaa24152540046b33c','0fbf2ddb2ebd11eaa24152540046b33c',sysdate(),sysdate());
# 游客
insert into myblog.rolePermission values (replace(uuid(), '-', ''),'8bf596fc2ebd11eaa24152540046b33c','0f8e51892ebd11eaa24152540046b33c',sysdate(),sysdate());
insert into myblog.rolePermission values (replace(uuid(), '-', ''),'8bf596fc2ebd11eaa24152540046b33c','0fbf2ddb2ebd11eaa24152540046b33c',sysdate(),sysdate());
select * from myblog.rolePermission;

# 查询权限
select
       rolePermission.id,
       role.name,
       permission.url
from rolePermission
inner join role on role.id = rolePermission.roleid
inner join permission on permission.id = rolePermission.permissionid;

# 向用户表中插入数据(密码：123456)
-- username:liuyuay password:liuyuay0113
insert into myblog.user values ('00000000000000000000000000000000','admin','管理员','97ba1ef7f148b2aec1c61303a7d88d0967825495','liuyuay','token',sysdate(),'00000000000000000000000000000000',sysdate(),'00000000000000000000000000000000');
insert into myblog.user values (replace(uuid(), '-', ''),'liuyu','刘雨','97ba1ef7f148b2aec1c61303a7d88d0967825495','salt','liuyuay',sysdate(),'00000000000000000000000000000000',sysdate(),'00000000000000000000000000000000');

select * from myblog.user;

# 向用户-角色表中插入数据
# 管理员
insert into userRole values (replace(uuid(), '-', ''),'8bbc10932ebd11eaa24152540046b33c','00000000000000000000000000000000',sysdate(),sysdate());
insert into userRole values (replace(uuid(), '-', ''),'8bbc10932ebd11eaa24152540046b33c','00000000000000000000000000000000',sysdate(),sysdate());
# liuyu
insert into userRole values (replace(uuid(), '-', ''),'8bf596fc2ebd11eaa24152540046b33c','a2724d6e2ec011eaa24152540046b33c',sysdate(),sysdate());

select * from userRole;

# 查询用户具体的角色
select
       userRole.id,
       user.username,
       user.showname,
       role.name
from myblog.userRole
inner join user on user.id = userRole.userid
inner join role on role.id = userRole.roleid;