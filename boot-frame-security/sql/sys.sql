/*==============================================================*/
/* Table: sys_param                                           */
/*==============================================================*/
create table sys_param
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `TYPE_CODE`             varchar(128) comment '所属类别标识',
   `NAME`                 varchar(512) comment '名称',
   `CODE`                  varchar(128) comment '标识',
   `VALUE`                varchar(512) comment '值',
   `ORDERS`              int comment '排序值',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_param comment '系统属性表';

/*==============================================================*/
/* Table: sys_type                                            */
/*==============================================================*/
create table sys_type
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `PARENT_CODE`           varchar(128) comment '所属类别标识',
   `NAME`                 varchar(512) comment '名称',
   `CODE`                  varchar(128) comment '标识',
   `ORDERS`              int comment '排序值',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_type comment '类别表';

/*==============================================================*/
/* Table: sys_function                                          */
/*==============================================================*/
create table sys_function
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `MODULE_CODE`             varchar(32) comment '模块标识',
   `VALIDATE`             varchar(32) comment '是否验证',
   `USEABLE`              varchar(32) comment '是否可用',
   `NAME`                 varchar(512) comment '名称',
   `CODE`                  varchar(128) comment '标识',
   `ORDERS`                int comment '排序值',
   `URL`                  varchar(1024) comment 'URL',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
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
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `TYPE_CODE`             varchar(32) comment '类别标识',
   `PARENT_CODE`           varchar(128) comment '所属模块标识',
   `LEVEL`                      int comment '级别',
   `CODE_PATH`         varchar(2048) comment '菜单路径',
   `VALIDATE`             varchar(32) comment '是否验证',
   `USEABLE`              varchar(32) comment '是否可用',
   `NAME`                 varchar(512) comment '名称',
   `CODE`                  varchar(128) comment '标识',
   `ICON`                  varchar(1024) comment '图标',
   `ORDERS`              int comment '排序值',
   `URL`                  varchar(1024) comment 'URL',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
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
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `TYPE_CODE`             varchar(32) comment '类别标识',
   `NAME`                 varchar(512) comment '名称',
   `CODE`                  varchar(32) comment '标识',
   `ORDERS`                int comment '排序值',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
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
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `ROLE_ID`              bigint comment '角色ID',
   `MODULE_ID`            bigint comment '模块ID',
   `STATUS`               varchar(32) comment '数据状态',
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
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `USERNAME`             varchar(512) comment '用户名',
   `PASSWORD`             varchar(512) comment '密码',
   `REALNAME`             varchar(512) comment '真实名称',
   `EMAIL`                varchar(512) comment '电子邮件',
   `TELEPHONE`            varchar(512) comment '电话',
   `CELLPHONE`            varchar(512) comment '手机',
   `SEX`                  varchar(32) comment '性别',
   `AGE`                  int comment '年龄',
   `ADDRESS`              varchar(512) comment '地址',
   `PHOTO`                varchar(1024) comment '照片',
   `ORDERS`              int comment '排序值',
   `LAST_LOGIN_TIME`      datetime comment '上次登录时间',
   `LAST_LOGIN_IP`        varchar(128) comment '上次登录IP',
   `LAST_PASSWORD`        varchar(128) comment '上次密码',
   `USER_STATUS`          varchar(32) comment '用户状态',
   `USER_TYPE_CODE`         varchar(32) comment '用户类别标志（来自分类）',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `TYPE_CODE`             varchar(32) comment '类别标识',
   `STATUS`               varchar(32) comment '数据状态',
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
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `ROLE_ID`              bigint comment '角色ID',
   `USER_ID`              bigint comment '用户ID',
   `STATUS`               varchar(32) comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_role_user comment '角色用户关联';


/*==============================================================*/
/* Table: sys_upload_file                                       */
/*==============================================================*/
create table sys_upload_file
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `UPLOAD_TIME`          datetime comment '上传时间',
   `IP`                   varchar(100) comment '上传IP',
   `NAME`                 varchar(512) comment '文件名称',
   `SIZE`                 bigint comment '文件大小',
   `PATH`                 varchar(1024) comment '文件路径',
   `TYPE`                 varchar(100) comment '文件类别',
   `FILE_STATUS`          varchar(32) comment '文件状态',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_upload_file comment '上传文件';

/*==============================================================*/
/* Table: sys_area                                              */
/*==============================================================*/
create table sys_area
(
   `ID`                   bigint auto_increment not null comment '主键',
   `OPTIMISTIC`           bigint default 0 comment '乐观锁',
   `PARENT_CODE`          varchar(128) comment '父标识',
   `CODE`                 varchar(128) comment '标识',
   `NAME`                 varchar(512) comment '名称',
   `ORDERS`              int comment '排序值',
   `DESCRIPTION`          varchar(2048) comment '描述',
   `STATUS`               varchar(32) comment '数据状态',
   `CREATE_USER`          varchar(32) comment '创建人',
   `CREATE_TIME`          datetime comment '创建时间',
   `UPDATE_USER`          varchar(32) comment '修改人',
   `UPDATE_TIME`          datetime comment '修改时间',
   primary key (ID)
);

alter table sys_area comment '系统地区';
