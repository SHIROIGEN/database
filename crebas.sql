/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2018/12/26 ÐÇÆÚÈý 13:38:27                      */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('Filmcritics')
            and   type = 'U')
   drop table Filmcritics
go

if exists (select 1
            from  sysobjects
           where  id = object_id('actor')
            and   type = 'U')
   drop table actor
go

if exists (select 1
            from  sysobjects
           where  id = object_id('l_m')
            and   type = 'U')
   drop table l_m
go

if exists (select 1
            from  sysobjects
           where  id = object_id('label')
            and   type = 'U')
   drop table label
go

if exists (select 1
            from  sysobjects
           where  id = object_id('m_a')
            and   type = 'U')
   drop table m_a
go

if exists (select 1
            from  sysobjects
           where  id = object_id('movie')
            and   type = 'U')
   drop table movie
go

if exists (select 1
            from  sysobjects
           where  id = object_id('uname')
            and   type = 'U')
   drop table uname
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"user"')
            and   type = 'U')
   drop table "user"
go

/*==============================================================*/
/* Table: Filmcritics                                           */
/*==============================================================*/
create table Filmcritics (
   userid               varchar(256)         null,
   moviename            varchar(50)          null,
   critics              varchar(512)         null
)
go

/*==============================================================*/
/* Table: actor                                                 */
/*==============================================================*/
create table actor (
   staff                varchar(40)          null
)
go

/*==============================================================*/
/* Table: l_m                                                   */
/*==============================================================*/
create table l_m (
   label                varchar(10)          null,
   moviename            varchar(50)          null
)
go

/*==============================================================*/
/* Table: label                                                 */
/*==============================================================*/
create table label (
   label                varchar(10)          null
)
go

/*==============================================================*/
/* Table: m_a                                                   */
/*==============================================================*/
create table m_a (
   moviename            varchar(50)          null,
   actor                varchar(40)          null
)
go

/*==============================================================*/
/* Table: movie                                                 */
/*==============================================================*/
create table movie (
   moviename            varchar(50)          null,
   score                float(3)             null,
   director             varchar(20)          null
)
go

/*==============================================================*/
/* Table: uname                                                 */
/*==============================================================*/
create table uname (
   userid               char(256)            null,
   username             varchar(256)         null
)
go

/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" (
   userid               char(256)            null,
   password             char(256)            null
)
go

