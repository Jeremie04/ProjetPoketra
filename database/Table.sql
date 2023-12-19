/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ranto Jeremy
 * Created: 13 déc. 2023
 */

create database poketra;
\c poketra

create table taille(
    id serial primary key,
    nom varchar(100)
);

create table look(
    id serial primary key,
    nom varchar(100)
);

create table unite (
    id serial primary key,
    intitule varchar(100)
);

create table matiere(
    id serial primary key,
    nom varchar(100),
    id_unite int references unite(id)
);

create table type(
    id serial primary key,
    nom varchar(100)
);

create table matiereLook(
    id serial primary key,
    id_look int references look(id),
    id_matiere int references matiere(id)
); 

