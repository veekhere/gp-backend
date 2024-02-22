--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS array_avg(arr anyarray);
CREATE FUNCTION array_avg(arr anyarray)
    RETURNS numeric
    LANGUAGE sql AS $$

    SELECT AVG(a)::numeric(3, 2)
    FROM unnest(arr) AS a
$$;
