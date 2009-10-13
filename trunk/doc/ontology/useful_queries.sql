-- Nodes with multiple parents
select child.termname as child_name, child.id as child_id, parent.termname as parent_name, parent.id as parent_id from isa
join term as parent on isa.parent_id = parent.id
join term as child on isa.child_id = child.id
where child_id in (
select child_id from isa
group by child_id having count(*) > 1
);

-- Nodes with no parent
select termname from term where id not in (select child_id from isa);

select count(*) from mo;

delete from ontologydefinition;
delete from term;
delete from mo;
delete from termrelationship;
delete from isa;