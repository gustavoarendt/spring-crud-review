alter table medic add column active tinyint;
update medic set active = 1;