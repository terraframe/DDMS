select geoentity.type, term.name, entityname, geodata, ST_asEWKT(geomultipolygon)
from geoentity
left join term on term.id =geoentity.term
where isvalid(geomultipolygon) = false
order by geoentity.type, term.name, entityname

select geoentity.type, term.name, count(*)
from geoentity 
left join term on term.id =geoentity.term
where isvalid(geomultipolygon) = false
group by geoentity.type, term.name
order by geoentity.type, term.name

select geoentity.type, term.name, count(*)
from geoentity 
left join term on term.id =geoentity.term
group by geoentity.type, term.name
order by geoentity.type, term.name

select * from geoentity where entityname ='M9'

delete from geoentity;