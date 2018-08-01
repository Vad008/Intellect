create table sertificates (
  id integer UNIQUE,
  name varchar(255),
  phonenumber varchar(255),
  email varchar(255),
  done BOOLEAN DEFAULT FALSE,
  primary key (id)
)