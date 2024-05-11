--liquibase formatted sql
--changeSet runAlways:true splitStatements:false

DROP FUNCTION IF EXISTS search_places(params json);
CREATE FUNCTION search_places(params json)

    -- PLACE_PROJECTION

	RETURNS TABLE (
		"id" uuid,
		rentType varchar(26)[],
		spaceType varchar(10),
		area real,
		floor integer,
		latitude real,
        longitude real,
        country varchar(255),
        state varchar(255),
        city varchar(255),
        road varchar(255),
        houseNumber varchar(32),
        avgPrice real,
        avgPlaceRating real,
        avgLandlordRating real,
        avgNeighborRating real
	)
	LANGUAGE plpgsql AS $$

DECLARE

    -- filter fields

    f_rent_type varchar(10);
	f_space_type varchar(10);
	f_area_from numeric;
	f_area_to numeric;
	f_floor integer;
	f_price_from numeric;
	f_price_to numeric;
	f_country varchar(255);
	f_state varchar(255);
	f_city varchar(255);
	f_road varchar(255);
	f_house_number varchar(32);
	f_place_rating_from numeric;
	f_place_rating_to numeric;
	f_landlord_rating_from numeric;
	f_landlord_rating_to numeric;
	f_neighbor_rating_from numeric;
	f_neighbor_rating_to numeric;

BEGIN

    -- filter fields

    f_rent_type := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'rentType')::varchar(10), '');
    f_space_type := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'spaceType')::varchar(10), '');
    f_area_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'areaFrom')::numeric, 0);
    f_area_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'areaTo')::numeric, 0);
    f_floor := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'floor')::integer, 0);
    f_price_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'priceFrom')::numeric, 0);
    f_price_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'priceTo')::numeric, 0);
    f_country := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'country')::varchar(255), '');
    f_state := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'state')::varchar(255), '');
    f_city := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'city')::varchar(255), '');
    f_road := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'road')::varchar(255), '');
    f_house_number := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'houseNumber')::varchar(32), '');
    f_place_rating_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'placeRatingFrom')::numeric, 0);
    f_place_rating_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'placeRatingTo')::numeric, 0);
    f_landlord_rating_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'landlordRatingFrom')::numeric, 0);
    f_landlord_rating_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'landlordRatingTo')::numeric, 0);
    f_neighbor_rating_from := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'neighborRatingFrom')::numeric, 0);
    f_neighbor_rating_to := COALESCE(JSON_EXTRACT_PATH_TEXT(params, 'neighborRatingTo')::numeric, 0);

    RETURN QUERY

    SELECT
        inner_table.id,
        inner_table.rent_type AS rentType,
        inner_table.space_type AS spaceType,
        inner_table.area,
        inner_table.floor,
        inner_table.latitude,
        inner_table.longitude,
        inner_table.country,
        inner_table.state,
        inner_table.city,
        inner_table.road,
        inner_table.house_number AS houseNumber,
        inner_table.avg_price AS avgPrice,
        inner_table.avg_place_rating AS avgPlaceRating,
        inner_table.avg_landlord_rating AS avgLandlordRating,
        inner_table.avg_neighbor_rating AS avgNeighborRating

    FROM (
        SELECT
            p.id,
            p.rent_type,
            p.space_type,
            p.area,
            p.floor,
            p.latitude,
            p.longitude,
            p.country,
            p.state,
            p.city,
            p.road,
            p.house_number,

            -- transient columns

            COALESCE(array_avg(array_agg(r.price) FILTER (WHERE r.price IS NOT NULL)), 0)::real AS avg_price,
            COALESCE(array_avg(array_agg(r.place_rating) FILTER (WHERE r.place_rating IS NOT NULL)), 0)::real AS avg_place_rating,
            COALESCE(array_avg(array_agg(r.landlord_rating) FILTER (WHERE r.landlord_rating IS NOT NULL)), 0)::real AS avg_landlord_rating,
            COALESCE(array_avg(array_agg(r.neighbor_rating) FILTER (WHERE r.neighbor_rating IS NOT NULL)), 0)::real AS avg_neighbor_rating

        FROM
            place AS p

        LEFT JOIN (
            SELECT
                pr.id rating_id,
                pr.place_id,
                pr.place_rating,
                pr.landlord_rating,
                pr.neighbor_rating,
                pr.price

            FROM
                place_rating AS pr,
                place AS p2

            WHERE
                pr.place_id = p2.id

            GROUP BY
                pr.id,
                pr.place_id
        ) AS r ON r.place_id = p.id

        -- column filters

        WHERE (

            -- RENT_TYPE

            (LENGTH(UPPER(f_rent_type)) > 0 AND UPPER(f_rent_type) = ANY(p.rent_type))
            OR
            (LENGTH(f_rent_type) = 0 AND p.id = p.id)

        ) AND (

            -- SPACE_TYPE

            (LENGTH(UPPER(f_space_type)) > 0 AND UPPER(f_space_type) = UPPER(p.space_type))
            OR
            (LENGTH(f_space_type) = 0 AND p.id = p.id)

        ) AND (

            -- AREA_FROM

            (f_area_from > 0 AND p.area >= f_area_from)
            OR
            (f_area_from <= 0 AND p.id = p.id)

        ) AND (

            -- AREA_TO

            (f_area_to > 0 AND p.area <= f_area_to)
            OR
            (f_area_to <= 0 AND p.id = p.id)

        ) AND (

            -- FLOOR

            (f_floor > 0 AND p.floor = f_floor)
            OR
            (f_area_to <= 0 AND p.id = p.id)

        ) AND (

            -- COUNTRY

            (LENGTH(LOWER(f_country)) > 0 AND POSITION(LOWER(f_country) in LOWER(p.country)) > 0)
            OR
            (LENGTH(f_country) = 0 AND p.id = p.id)

        ) AND (

            -- STATE

            (LENGTH(LOWER(f_state)) > 0 AND POSITION(LOWER(f_state) in LOWER(p.state)) > 0)
            OR
            (LENGTH(f_state) = 0 AND p.id = p.id)

        ) AND (

            -- CITY

            (LENGTH(LOWER(f_city)) > 0 AND POSITION(LOWER(f_city) in LOWER(p.city)) > 0)
            OR
            (LENGTH(f_city) = 0 AND p.id = p.id)

        ) AND (

            -- ROAD

            (LENGTH(LOWER(f_road)) > 0 AND POSITION(LOWER(f_road) in LOWER(p.road)) > 0)
            OR
            (LENGTH(f_road) = 0 AND p.id = p.id)

        ) AND (

            -- HOUSE NUMBER

            (LENGTH(LOWER(f_house_number)) > 0 AND POSITION(LOWER(f_house_number) in LOWER(p.house_number)) > 0)
            OR
            (LENGTH(f_house_number) = 0 AND p.id = p.id)

        )

        GROUP BY
            p.id
    ) AS inner_table

    -- transient columns filters

    WHERE (

        -- PRICE_FROM

        (f_price_from > 0 AND inner_table.avg_price >= f_price_from)
        OR
        (f_price_from <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- PRICE_TO

        (f_price_to > 0 AND inner_table.avg_price <= f_price_to)
        OR
        (f_price_to <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- PLACE_RATING_FROM

        (f_place_rating_from > 0 AND inner_table.avg_place_rating >= f_place_rating_from)
        OR
        (f_place_rating_from <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- PLACE_RATING_TO

        (f_place_rating_to > 0 AND inner_table.avg_place_rating <= f_place_rating_to)
        OR
        (f_place_rating_to <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- LANDLORD_RATING_FROM

        (f_landlord_rating_from > 0 AND inner_table.avg_landlord_rating >= f_landlord_rating_from)
        OR
        (f_landlord_rating_from <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- LANDLORD_RATING_TO

        (f_landlord_rating_to > 0 AND inner_table.avg_landlord_rating <= f_landlord_rating_to)
        OR
        (f_landlord_rating_to <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- NEIGHBOR_RATING_FROM

        (f_neighbor_rating_from > 0 AND inner_table.avg_neighbor_rating >= f_neighbor_rating_from)
        OR
        (f_neighbor_rating_from <= 0 AND inner_table.id = inner_table.id)

    ) AND (

        -- NEIGHBOR_RATING_TO

        (f_neighbor_rating_to > 0 AND inner_table.avg_neighbor_rating <= f_neighbor_rating_to)
        OR
        (f_neighbor_rating_to <= 0 AND inner_table.id = inner_table.id)

    );

END;
$$;
