create table booking (
  id integer UNIQUE,
  name varchar(255),
  phonenumber varchar(255),
  email varchar(255),
  datequest varchar(255),
  quest varchar(255),
  messageuser varchar(255),
  done BOOLEAN DEFAULT FALSE,
  primary key (id)
)