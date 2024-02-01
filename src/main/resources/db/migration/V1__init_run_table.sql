CREATE TABLE run(
    run_id serial PRIMARY KEY,
    character_name VARCHAR(12),
    key INT,
    dungeon VARCHAR(100),
    drop VARCHAR(10),
    gearscore INT,
    finished INT,
    cleared VARCHAR(10),
    date VARCHAR(30),
    user_name VARCHAR(100)
);