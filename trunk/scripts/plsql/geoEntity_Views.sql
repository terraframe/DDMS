/*

CREATE  INDEX fqtn_md_type_hash
  ON md_type 
  USING hash
  ((packagename || '.' || typename));
  
CREATE  INDEX fqtn_md_type_btree
  ON md_type 
  USING btree
  ((packagename || '.' || typename));
  
CREATE INDEX display_label_btree
  ON md_type
  (displaylabel);

CREATE INDEX geoentityclass_btree
  ON geohierarchy
  USING btree
  (geoentityclass);

CREATE INDEX geoentityclass_hash
  ON geohierarchy
  USING hash
  (geoentityclass);

*/