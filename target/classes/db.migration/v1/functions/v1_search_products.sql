--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS search_products(params json);
CREATE FUNCTION search_products(params json)
	RETURNS TABLE (
		"id" uuid,
		title varchar(255),
		description varchar(255),
		price numeric,
		available_amount integer,
		store_id uuid,
        ratings json,
        totalRatings integer,
        rating numeric
	)
	LANGUAGE plpgsql AS $$

DECLARE

    f_store uuid;
	f_title varchar(255);
	f_price_from numeric;
	f_price_to numeric;
	f_is_available boolean;

BEGIN

	f_store := JSON_EXTRACT_PATH_TEXT(params, 'store')::uuid;
	f_title := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'title')::varchar(255), '');
	f_price_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'priceFrom')::numeric, 0);
	f_price_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'priceTo')::numeric, 0);
	f_is_available := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'isAvailable')::boolean, FALSE);

	RETURN QUERY

        SELECT
            p.id,
            p.title,
            p.description,
            p.price,
            p.available_amount,
            p.store_id,
            array_to_json(array_agg(r)) AS ratings,

-- transient or used for inheritors

            COALESCE(ARRAY_LENGTH(array_agg(r) FILTER (WHERE r.rating IS NOT NULL), 1), 0) AS totalRatings,
            COALESCE(array_avg(array_agg(r.rating) FILTER (WHERE r.rating IS NOT NULL)), 0) AS rating

        FROM
            product AS p

        LEFT JOIN (
            SELECT
                pr.id rating_id,
                pr.product_id,
                pr.comment,
                pr.rating

            FROM
                product_rating AS pr,
                product AS p2

            WHERE
                pr.product_id = p2.id

            GROUP BY
                pr.id,
                pr.product_id,
                pr.comment
        ) AS r ON r.product_id = p.id

-- filters

        WHERE (

            (LENGTH(LOWER(f_title)) > 0 AND POSITION(LOWER(f_title) in LOWER(p.title)) > 0)
            OR
            (LENGTH(f_title) = 0 AND p.title = p.title)

        ) AND (

            (f_price_from > 0 AND p.price >= f_price_from)
            OR
            (f_price_from <= 0 AND p.price = p.price)

        ) AND (

            (f_price_to > 0 AND p.price <= f_price_to)
            OR
            (f_price_to <= 0 AND p.price = p.price)

        ) AND (

            (f_is_available IS TRUE AND p.available_amount > 0)
            OR
            (f_is_available IS FALSE AND p.available_amount = p.available_amount)

        ) AND (

            (f_store IS NOT NULL AND p.store_id = f_store)
            OR
            (f_store IS NULL AND p.store_id = p.store_id)

        )

		GROUP BY
            p.id
		;
END;
$$;
