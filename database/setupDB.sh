#!/bin/bash
dbuser=$(cat ~/.collectService/application-dev.properties | grep "spring.datasource.username" | cut -f2- -d=
);
dbase=forextimeseriesdb;
passw=$(cat ~/.collectService/application-dev.properties | grep "spring.datasource.password" | cut -f2- -d=
);

touch $HOME/.pgpass;
chmod 0600 $HOME/.pgpass;
echo "alter user postgres password '$passw';" | sudo -u postgres psql
echo "localhost:*:*:postgres:$passw" >> $HOME/.pgpass
echo "create user $dbuser;" | psql
echo "alter user $dbuser password '$passw';" | psql
echo "localhost:*:$dbase:$dbuser:$passw" >> $HOME/.pgpass
echo "create database $dbuser owner $dbuser;" | psql
echo "create database $dbase owner $dbuser;" | psql
echo "create extension if not exists timescaledb cascade;" | psql -d $dbase

cat ./createTables | psql -U $dbuser -d $dbase -h localhost
