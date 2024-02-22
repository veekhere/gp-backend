--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS search_stores(params json);
CREATE FUNCTION search_stores(params json)
    RETURNS TABLE (
        "id" uuid,
        name varchar(255),
        description varchar(255),
        products json
    )
    LANGUAGE plpgsql AS $$

DECLARE

    f_name varchar(255);

BEGIN

    f_name := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'name')::varchar(255), '');

    RETURN QUERY

        SELECT
            s.id,
            s.name,
            s.description,
            array_to_json(array_agg(p)) AS products

        FROM
            store AS s,
            search_products(NULL) AS p

        WHERE (

            (LENGTH(LOWER(f_name)) > 0 AND POSITION(LOWER(f_name) in LOWER(s.name)) > 0)
            OR
            (LENGTH(f_name) = 0 AND s.name = s.name)

        ) AND
            p.store_id = s.id

        GROUP BY
            s.id
        ;
END;
$$;
