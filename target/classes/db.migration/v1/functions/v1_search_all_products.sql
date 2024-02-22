--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS search_all_products(params json);
CREATE FUNCTION search_all_products(params json)
	RETURNS TABLE (
		"id" uuid,
		title varchar(255),
		price numeric,
		availableAmount integer,
		storeName varchar(255),
		totalRatings integer,
		rating numeric
	)
	LANGUAGE plpgsql AS $$

BEGIN

	RETURN QUERY

        SELECT
            p.id,
            p.title,
            p.price,
            p.available_amount AS availableAmount,
            s.name AS storeName,
            p.totalRatings,
            p.rating

        FROM
            search_products(params) AS p,
            store AS s

        WHERE
            p.store_id = s.id;
END;
$$;
