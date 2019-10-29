create database "odk";
create user "odk_user" with unencrypted password 'noReply';
grant all privileges on database "odk" to "odk_user";
alter database "odk" owner to "odk_user";
\c "odk";
create schema "odk";
grant all privileges on schema "odk" to "odk_user";
alter schema "odk" owner to "odk_user";
