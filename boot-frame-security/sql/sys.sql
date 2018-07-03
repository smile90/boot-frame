/*==============================================================*/
/* Table: sys_function                                          */
/*==============================================================*/
create table sys_function
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 not null comment '乐观锁',
   `MODULE_CODE`             varchar(32) not null comment '模块标识',
   `VALIDATE`             varchar(32) not null comment '是否验证',
   `USEABLE`              varchar(32) not null comment '是否可用',
   `NAME`                 varchar(512) not null comment '名称',
   `CODE`                  varchar(128) not null unique comment '标识',
   `ORDERS`                int comment '排序值',
   `URL`                  varchar(1024) not null comment 'URL',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) not null comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_function comment '功能表';

/*==============================================================*/
/* Table: sys_module                                            */
/*==============================================================*/
create table sys_module
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 not null comment '乐观锁',
   `TYPE_CODE`             varchar(32) comment '类别标识',
   `PARENT_CODE`           varchar(128) not null comment '所属模块标识',
   `VALIDATE`             varchar(32) not null comment '是否验证',
   `USEABLE`              varchar(32) not null comment '是否可用',
   `NAME`                 varchar(512) not null comment '名称',
   `CODE`                  varchar(128) not null unique comment '标识',
   `ICON`                  varchar(1024) comment '图标',
   `ORDERS`              int comment '排序值',
   `URL`                  varchar(1024) comment 'URL',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) not null comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_module comment '模块表';


/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 not null comment '乐观锁',
   `TYPE_CODE`             varchar(32) comment '类别标识',
   `NAME`                 varchar(512) not null comment '名称',
   `CODE`                  varchar(32) not null unique comment '标识',
   `ORDERS`                int comment '排序值',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) not null comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_role comment '角色表';

/*==============================================================*/
/* Table: sys_role_module                                       */
/*==============================================================*/
create table sys_role_module
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 not null comment '乐观锁',
   `ROLE_CODE`              varchar(128) not null comment '角色标识',
   `MODULE_CODE`            varchar(128) not null comment '模块标识',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_role_module comment '角色模块关联表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 not null comment '乐观锁',
   `USERNAME`             varchar(512) not null unique comment '用户名',
   `PASSWORD`             varchar(512) not null comment '密码',
   `REALNAME`             varchar(512) not null comment '真实名称',
   `EMAIL`                varchar(512) comment '电子邮件',
   `TELEPHONE`            varchar(512) comment '电话',
   `CELLPHONE`            varchar(512) comment '手机',
   `SEX`                  varchar(32) comment '性别',
   `AGE`                  int comment '年龄',
   `ADDRESS`              varchar(512) comment '地址',
   `PHOTO`                varchar(1024) comment '照片',
   `ORDERS`              int comment '排序值',
   `USER_STATUS`          varchar(32) not null comment '用户状态',
   `USER_TYPE_CODE`         varchar(32) comment '用户类别标志（来自分类）',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) not null comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_user comment '用户';

/*==============================================================*/
/* Table: sys_role_user                                         */
/*==============================================================*/
create table sys_role_user
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 not null comment '乐观锁',
   `ROLE_CODE`              varchar(128) not null comment '角色标识',
   `USERNAME`              varchar(128) not null comment '用户名',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_role_user comment '角色用户关联';