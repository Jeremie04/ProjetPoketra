/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ranto Jeremy
 * Created: 14 déc. 2023
 */

create or replace view v_matiere_unite as 
SELECT 
    m.*,
    u.intitule
from matiere m 
join unite u on m.id_unite = u.id;


create or replace view v_matiere_look as
SELECT 
    ml.id, 
    ml.id_look,
    l.nom as nom_look,
    ml.id_matiere,
    m.nom as nom_matiere,
    m.id_unite,
    u.intitule as unite
from matierelook ml
join look l on ml.id_look = l.id 
join matiere m on ml.id_matiere = m.id
join unite u on m.id_unite = u.id;
